1. concurrect：并发编程Demo
   
2. interrupt：用两种方式实现interrupt中断标志

3. queue：两种队列LinkedBlockingQueue、ConcurrentLinkedQueue，并使用线程池+CountDownLatch对队列进行测试，所有线程完成后一起退出。
```
add方法在添加元素的时候，若超出了度列的长度会直接抛出异常
put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素
offer方法在添加元素时，如果发现队列已满无法添加的话，会直接返回false

poll: 若队列为空，返回null。
remove:若队列为空，抛出NoSuchElementException异常
take:若队列为空，发生阻塞，等待有元素
```   

4. CountDownLatch和CyclicBarrier
```
CountDownLatch和CyclicBarrier区别：
 1.countDownLatch是一个计数器，线程完成一个记录一个，计数器递减，只能只用一次
 2.CyclicBarrier的计数器更像一个阀门，需要所有线程都到达，然后继续执行，计数器递增，提供reset功能，可以多次使用
```

5. Atomic

6. ForkJoinPool

7. ReentrantLock，读写锁

8. Semaphore

9. Synchronized