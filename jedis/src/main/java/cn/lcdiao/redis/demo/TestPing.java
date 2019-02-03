package cn.lcdiao.redis.demo;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        /*
        #bind 127.0.0.1         将绑定的ip地址端口号给注释
        由于Linux上的redis处于安全保护模式，这就让你无法从虚拟机外部去轻松建立连接，这里就有两种解决方法
        一种是在redis.conf中设置保护模式为no        protected-mode no
        另外一种方式是加上安全认证，即redis默认是没有密码的可以直接登录，这里加上密码           requirepass "password"

        注意修改配置文件后要指定配置文件启动服务
         */
        Jedis jedis = new Jedis("134.175.116.100",6379);
        //jedis.auth("password");       //如果设置了密码就加上
        System.out.println(jedis.ping());
    }
}
