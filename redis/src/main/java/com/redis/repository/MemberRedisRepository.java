package com.redis.repository;

import com.redis.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRedisRepository extends CrudRepository<Member, String> {
}
