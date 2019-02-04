package cn.lcdiao.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 测试事务
 */
public class TestTransaction {
    /**
     * 通俗点讲，watch命令就是标记一个键，如果标记了一个键，在提交事务前如果该键被别人修改过，那事务就会失败，
     * 这种情况通常可以在程序中重新在尝试一次。
     * 首先标记了键balance，然后检测余额是否足够，不足就取消标记，并不做扣减，足够的话，就启动事务进行更新操作，
     * 如果在此期间键balance被其他人修改，那在提交事务(执行exec)时就会报错，程序中通常可以捕获这类错误再重新执行一次，直到成功。
     */
    public static void main(String[] args) throws InterruptedException {
        TestTransaction test = new TestTransaction();
        boolean retValue = test.transMethod();
        System.out.println("main retValue------"+retValue);
    }

    public void testTransaction(){
        Jedis jedis = new Jedis("134.175.116.100",6379);
        //System.out.println(jedis.ping());

        //开启事务
        Transaction transaction = jedis.multi();

        transaction.set("k4","v44");
        transaction.set("k5","v55");

        //提交事务
        //transaction.exec();
        //取消事务
        transaction.discard();
    }

    public boolean transMethod() throws InterruptedException {
        Jedis jedis = new Jedis("134.175.116.100",6379);
        int balance;//可用余额
        int debt;   //欠额
        int amtToSubtract = 10; //实刷额度

        jedis.watch("balance");//监控，防止修改过程中被别人修改

        //jedis.set("balance","5"); //自己修改，模拟其他程序已经修改了该条目
        Thread.sleep(7000L);//模拟网络延迟，期间自己修改redis上的balance

        balance = Integer.parseInt(jedis.get("balance"));
        if(balance<amtToSubtract){
            jedis.unwatch();
            System.out.println("modify");
            return false;
        }else{
            System.out.println("****************transaction");
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance",amtToSubtract);
            transaction.incrBy("debt",amtToSubtract);
            transaction.exec();
            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));

            System.out.println("**********"+balance);
            System.out.println("**********"+debt);
            return true;
        }
    }
}
