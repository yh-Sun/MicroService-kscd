package com.kscd.decorator.sun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class TestInputStream {

    public static void main(String[] args) throws Exception {
        String txt = "1Jtxt";
        File file = new File("design-pattern/src/main/resources/decorator.txt");
        FileOutputStream out = new FileOutputStream(file);
        out.write(txt.getBytes());
        out.close();

        {
            int c = 0;
            InputStream in = new ElogInputStream(new FileInputStream(file));
            while ((c = in.read()) != -1) {
                System.out.println((char)c);
            }
            in.close();
        }

        System.out.println("-----------------------");

        {
            int c = 0;
            InputStream in = new ClogInputStream(new FileInputStream(file));
            while ((c = in.read()) != -1) {
                System.out.println((char)c);
            }
            in.close();
        }
    }

    /**
     * 1.this.getClass().getResource（""）
     * 得到的是当前类class文件的URI目录。不包括自己！
     * 如：file：/D：/workspace/jbpmtest3/bin/com/test/
     *
     * 2.this.getClass().getResource（"/"）
     * 得到的是当前的classpath的绝对URI路径 。
     * 如：file：/D：/workspace/jbpmtest3/bin/
     *
     * 3.this.getClass() .getClassLoader().getResource（""）
     * 得到的也是当前ClassPath的绝对URI路径 。
     * 如：file：/D：/workspace/jbpmtest3/bin/
     *
     * 4.ClassLoader.getSystemResource（""）
     * 得到的也是当前ClassPath的绝对URI路径 。
     * 如：file：/D：/workspace/jbpmtest3/bin/
     *
     * 5.Thread.currentThread().getContextClassLoader ().getResource（""）
     * 得到的也是当前ClassPath的绝对URI路径 。
     * 如：file：/D：/workspace/jbpmtest3/bin/
     *
     * 6.ServletActionContext.getServletContext().getRealPath(“/”)
     * Web应用程序 中，得到Web应用程序的根目录的绝对路径。这样，我们只需要提供相对于Web应用程序根目录的路径，就可以构建出定位资源的绝对路径。
     * 如：file：/D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WebProject
     */

}
