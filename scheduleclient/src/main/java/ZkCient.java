/**
 * Created by hzwangqiqing
 * on 2017/10/23.
 */
public interface ZkCient {
    //创建节点
    void createZode(String nodePath);

    //监听节点
    void addWathch(String nodePath);

    //watch时间回调
    void watchEventCallback();

    //
}
