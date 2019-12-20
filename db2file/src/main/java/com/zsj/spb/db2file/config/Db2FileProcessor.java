package com.zsj.spb.db2file.config;

import com.zsj.spb.db2file.entity.User;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

@StepScope
public class Db2FileProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User userFromDb) throws Exception {
        User userToFile = new User();
        BeanUtils.copyProperties(userFromDb,userToFile);
        return userToFile;
    }
}
