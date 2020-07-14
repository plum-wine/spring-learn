package com.github.repo;

import com.github.pojo.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hangs.zhang
 * @date 2019/3/21
 * *****************
 * function:
 */
@Repository
public interface UserRepo extends ReactiveMongoRepository<User, String> {

}
