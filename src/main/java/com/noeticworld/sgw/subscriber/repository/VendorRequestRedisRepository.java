package com.noeticworld.sgw.subscriber.repository;

import com.noeticworld.sgw.subscriber.model.VendorRequestsStateEntity;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendorRequestRedisRepository {

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public VendorRequestRedisRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(String key,String entity){
        hashOperations.put("Reqeust_Status", key, entity);
    }

    public List findAll(){
        return hashOperations.values("Reqeust_Status");
    }

    public String findByCorrelationId(String correlationId){
        return (String) hashOperations.get("Reqeust_Status", correlationId);
    }

    public void deleteByCorrelationId(String token){
        hashOperations.delete("Reqeust_Status", token);
    }
}
