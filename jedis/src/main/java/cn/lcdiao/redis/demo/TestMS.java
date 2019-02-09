package cn.lcdiao.redis.demo;

import redis.clients.jedis.Jedis;

/**
 * 主从复制
 */
public class TestMS {
    public static void main(String[] args) {
        //注意开始前两台机都为master
        Jedis jedis_M = new Jedis("134.175.116.100",6379);//主
        Jedis jedis_S = new Jedis("134.175.116.100",6380);//从

        jedis_S.slaveof("134.175.116.100",6379);//配从不配主

        jedis_M.set("class","1122");

        System.out.println(jedis_S.get("class"));//如果为null则多执行一次（速度太快，可以数据还没有存好）
    }
}
