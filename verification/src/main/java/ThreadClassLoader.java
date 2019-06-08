import sun.misc.Launcher;

import java.lang.*;
import java.lang.ClassLoader;
import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class ThreadClassLoader {
    public static void main(String[] args) {
        ClassLoader loader = ThreadClassLoader.class.getClassLoader();
        System.out.println(loader);

//        DriverManager.
        ClassLoader loader1 = Thread.currentThread().getContextClassLoader();
//        System.out.println(loader1);

//        Thread.currentThread().setContextClassLoader(n("D://myjava"));
        System.out.println(Thread.currentThread().getContextClassLoader());

        try {
            Class<?> c = Thread.currentThread().getContextClassLoader().loadClass("java.sql.Driver");
            System.out.println(c);
            System.out.println(c.getClassLoader());
            Class<?> c2 = Thread.currentThread().getContextClassLoader().loadClass("java.sql.PreparedStatement");
            System.out.println(c2);
            System.out.println(c2.getClassLoader());
            testClassLoader();
            System.out.println("---------------------------------");
            testClassLoader2();
            Thread.sleep(80000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加mysql和pg驱动依赖后
     * driver:class com.mysql.jdbc.Driver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
     * driver:class org.postgresql.Driver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
     * system loader:sun.misc.Launcher$AppClassLoader@18b4aac2
     * system loader's parent:sun.misc.Launcher$ExtClassLoader@27c170f0
     */
    public static void testClassLoader() throws ClassNotFoundException {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class, ThreadClassLoader.class.getClassLoader());
        Iterator<Driver> iterator = loader.iterator();

        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        Class<?> c = Thread.currentThread().getContextClassLoader().loadClass("com.mysql.jdbc.Driver");
        System.out.println(c);
        System.out.println(c.getClassLoader());

        System.out.println("system loader:" + ClassLoader.getSystemClassLoader());
        System.out.println("system loader's parent:" + ClassLoader.getSystemClassLoader().getParent());
    }

    /**
     * 默认加载器调高后就无法加载数据库
     * system loader:sun.misc.Launcher$AppClassLoader@18b4aac2
     * system loader's parent:sun.misc.Launcher$ExtClassLoader@27c170f0
     */
    public static void testClassLoader2() {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class, ClassLoader.getSystemClassLoader().getParent());
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("system loader:" + ClassLoader.getSystemClassLoader());
        System.out.println("system loader's parent:" + ClassLoader.getSystemClassLoader().getParent());
    }
}
