package com.bruceliu.lock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @BelongsProject: RedisLock
 * @BelongsPackage: com.bruceliu.lock
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-05-07 09:23
 * @Description: TODO
 */
public class SkillService {

    private static JedisPool pool = null;
    private DistributedLock lock = new DistributedLock(pool);

    int n = 500;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "127.0.0.1", 6379, 3000);
    }

    public void seckill() {
        // 返回锁的value值，供释放锁时候进行判断
        //String identifier = lock.lockWithTimeout("resource", 5000, 1000);
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        System.out.println(--n);
        //lock.releaseLock("resource", identifier);
    }
}
