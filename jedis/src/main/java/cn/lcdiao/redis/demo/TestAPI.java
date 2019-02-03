package cn.lcdiao.redis.demo;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestAPI {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("134.175.116.100",6379);
        System.out.println("============String类型===============");
        //String类型
        jedis.set("k1","v1");
        System.out.println(jedis.get("k1"));
        System.out.println("=============list类型==============");

        //list类型
        jedis.lpush("l1",new String[]{"1","2","3","4"});
        List<String> l = jedis.lrange("l1",0,-1);
        System.out.println(l);
        System.out.println("============set类型===============");

        //set类型
        jedis.sadd("set01",new String[]{"1","1","1","2","2","3","3"});
        Set<String> set = jedis.smembers("set01");
        System.out.println(set);
        System.out.println("=============hash类型==============");


        //hash类型
        jedis.hset("hs1","userName","liss");
        Map<String,String> map = new HashMap<String, String>();
        map.put("age","11");
        map.put("address","asd");
        jedis.hset("hs1",map);
        System.out.println(jedis.hget("hs1","address"));
        System.out.println("============zset类型===============");

        //zset类型
        jedis.zadd("zset1",600,"v1");
        Map<String,Double> map2 = new HashMap();
        map2.put("v2",70d);
        map2.put("v3",80d);
        map2.put("v4",90d);map2.put("v5",100d);
        jedis.zadd("zset1",map2);
        Set<String> set2 = jedis.zrange("zset1",0,-1);
        System.out.println(set2);


        System.out.println("\n=========keys *==================");

        Set<String> keys = jedis.keys("*");
        for(String s:keys){
            System.out.println(s);
        }

    }
}
