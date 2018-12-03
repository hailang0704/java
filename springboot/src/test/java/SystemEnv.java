import java.util.Iterator;
import java.util.Map;

public class SystemEnv {
    public static void main(String[] args) {
        Map map = System.getenv();
        String appEnv = System.getenv("JAVA_APP_ENV");
        System.out.println(appEnv);
        Iterator it = map.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry entry = (Map.Entry)it.next();
//            System.out.print(entry.getKey()+"=");
//            System.out.println(entry.getValue());
        }
    }
}
