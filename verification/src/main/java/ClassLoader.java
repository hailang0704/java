import sun.misc.Launcher;

import java.net.URL;

/**
 * 打印bootstrap加载器加载的路径
 */
public class ClassLoader {
    public static void main(String[] args){
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls)
            System.out.println(url.toExternalForm());
    }
}
