package com.zsj.spb.db2file.mapper;


import com.zsj.spb.db2file.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * 获取user列表
     * @param user
     * @return
     */
    List<User> getUserList(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteUserById(int id);

    /**
     * 批量插入用户
     * @param users
     * @return
     */
    int saveUserBatch(List<User> users);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int saveUser(User user);

}
