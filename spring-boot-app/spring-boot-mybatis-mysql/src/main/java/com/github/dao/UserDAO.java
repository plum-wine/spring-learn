package com.github.dao;

import com.github.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2018/11/21
 * *****************
 * function:
 * 运行结果在test文件夹中存放
 */
@Repository
public interface UserDAO {

    @MapKey("uid")
    Map<Integer, User> selectAllUser();

    User selectUserByUid(Integer uid);

    User selectUserByUsername(String username);

    int insert(@Param("user") User user);

    int delete(int uid);

}
