package com.tcg.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

/**
 * @author Administrator
 * @Title: GoodsDAO
 * @ProjectName redisLearning
 * @Description: implete data buffer by redis
 * @date 2019/4/6 000623:43
 */
@Repository
public class GoodsDAO {

    @Autowired
    private RedisTemplate<String,Object> template;

    /**
     * @param key
     * @param value
     * @Desc  wrtite String data to redis
     */
    public void add(String key,Object value){
        if(value instanceof String){
            template.opsForValue().set(key,value);
        }
        else if(value instanceof Map){
            Map<String,Object> map = (Map<String, Object>) value;
            //templete.opsForHash().pullAll(key,map);
            for(String hashKey:map.keySet()){
                template.opsForHash().put(key,hashKey,map.get(hashKey));
            }
        }
        else if(value instanceof List){
            List<Object> list = (List<Object>)value;
            template.opsForList().leftPushAll(key,list);
        }
        else if(value instanceof SortedSet){
            SortedSet<Object> zset = (SortedSet<Object>) value;
            for (Object object:zset){
                template.opsForSet().add(key,object);
            }
        }
    }

    public String getString(String key){
        return (String)template.opsForValue().get(key);
    }

    public <HK,HV>BoundHashOperations<String,HK,HV> getHash(String key){
        return template.boundHashOps(key);
    }

    public BoundListOperations<String,Object> getList(String key){
        return template.boundListOps(key);
    }

    public BoundSetOperations<String,Object> getSet(String key){
        return template.boundSetOps(key);
    }

    public BoundZSetOperations<String,Object> getSortedSet(String key){
        return template.boundZSetOps(key);
    }
}
