import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hzwangqiqing
 * on 2017/8/9.
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:application-context.xml"});
        System.out.println(FastJsonUtil.toJSONString(ctx.getBeanDefinitionNames()));
    }
}
