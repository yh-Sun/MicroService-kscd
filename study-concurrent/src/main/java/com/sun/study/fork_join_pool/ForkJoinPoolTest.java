package com.sun.study.fork_join_pool;

import cn.hutool.core.collection.CollUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 场景：现在有2000条数据需要重新插入一次数据库，并返回每个id的执行结果
 *      普通的方式new Thread()，设定几个线程；现在使用ForkJoinPool实现任务执行
 *
 * @author  Sun
 * @date    2021/7/19 14:43
 * @since   1.0
 */
public class ForkJoinPoolTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<RequestData> dataList = CollUtil.newArrayList();
        for (int i = 0; i < 2000; i++) {
            RequestData data = new RequestData();
            data.setId("" + i);
            dataList.add(data);
        }

        // 大任务分发成小任务
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<Result>> submit = pool.submit(new CallableTask(dataList, 0, dataList.size() - 1));
        System.out.println(submit.get());
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }


}


@EqualsAndHashCode(callSuper = true)
@Data
class CallableTask extends RecursiveTask<List<Result>> {

    private static final int MAX = 100;

    private List<RequestData> dataList;

    private int start;

    private int end;

    public CallableTask(List<RequestData> dataList, int start, int end) {
        this.dataList = dataList;
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<Result> compute() {
        if(end - start <= MAX) {
            List<Result> results = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                RequestData requestData = dataList.get(i);
                // 存库，组装返参
                Result build = Result.builder().id(requestData.getId()).success(true).build();
                results.add(build);
            }
            return results;
        } else {
            int middle = (start + end) / 2;
            ForkJoinTask<List<Result>> fork1 = new CallableTask(dataList, start, middle).fork();
            ForkJoinTask<List<Result>> fork2 = new CallableTask(dataList, middle + 1, end).fork();
            List<Result> join = fork2.join();
            CollUtil.addAll(join, fork1.join());
            return join;
        }
    }
}


@Data
@Builder
class Result {
    private String id;
    private Boolean success;
}

@Data
class RequestData {
    private String id;
    private String name;
}