package test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

/**
 * Created by hzwangqiqing
 * on 2017/10/23.
 */
public class Main {
    public static void main(String[] args) {
        String zookeeperConnectionString = "39.106.38.16:2181,39.106.38.16:2182,39.106.38.16:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();
        try {
            client.blockUntilConnected(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("client connient failure");
            e.printStackTrace();
        }
        Stat stat;
        //创建路径节点
        try {
            stat = client.checkExists().forPath("/kschedule/element-compose");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("checkExists Exeption");
        }
        try {
            client.create().forPath("/kschedule1/element-compose");
        } catch (Exception e) {
            System.out.println("exception happen");
            e.printStackTrace();
        }
    }
}
