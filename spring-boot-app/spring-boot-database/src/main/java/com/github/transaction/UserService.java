package com.github.transaction;

import com.github.dao.UserDAO;
import com.github.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * Transactional注解可以作用在接口, 类方法, 类上面
     * 不回滚的原因
     * 1. 方法不是public
     * 2. rollbackFor设置错误, 默认是RuntimeException
     * 3. 同一个类中方法调用(aop的缘故)
     * 4. 异常被自己catch
     * 5. 数据库引擎不支持
     * 6. 事务的传播行为设置错误
     *
     * @return UserDo
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserDO biz() {
        return null;
    }

}
