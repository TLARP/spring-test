import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hzwangqiqing
 * on 2017/8/9.
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(ApplicationContextTest.class);
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:application-context.xml"});
    }
}
