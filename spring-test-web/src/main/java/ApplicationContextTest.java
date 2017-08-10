import com.netease.kaola.generic.provider.HelloCompose;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hzwangqiqing
 * on 2017/8/9.
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(ApplicationContextTest.class);
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:application-context.xml"});
        ctx.start();
        HelloCompose helloCompose = ctx.getBean(HelloCompose.class);
        if (helloCompose == null) {
            log.error("get bean error!");
            return;
        }
        log.warn(helloCompose.sayHello());
    }
}
