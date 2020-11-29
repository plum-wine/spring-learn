package com.github.dao.user;

import com.github.entity.UserDO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hangs.zhang
 * @date 2018/11/21
 * *****************
 * function:
 * 运行结果在test文件夹中存放
 */
public interface UserDAO {

    /**
     * 查询结果自动映射为map
     *
     * @return java.util.Map<java.lang.Integer, com.github.entity.User>
     */
    @MapKey("uid")
    Map<Integer, UserDO> selectAllUser();

    List<UserDO> selectUsersByIds(@Param("userIds") Set<Integer> userIds);
    List<UserDO> selectUsersByIds2(@Param("userIds") Set<Integer> userIds);

    List<UserDO> selectUsers(@Param("query") UserDO query);

    UserDO selectUserByUid(Integer uid);

    UserDO selectUserByUsername(String username);

    int insert(@Param("user") UserDO user);

    int update(@Param("user") UserDO user);

    int delete(int uid);

    int deleteAll();

}
