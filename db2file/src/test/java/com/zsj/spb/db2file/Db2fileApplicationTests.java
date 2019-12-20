package com.zsj.spb.db2file;

import com.zsj.spb.db2file.entity.User;
import com.zsj.spb.db2file.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.batch.operations.JobRestartException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

@SpringBootTest
class Db2fileApplicationTests {
    @Autowired
    private UserMapper  userMapper;
    @Autowired
    private Job         db2FileJob;
    @Autowired
    private JobLauncher jobLauncher;

    @Test
    void testGetUserList() {
        User user = new User();
//        user.setId(1);
//        user.setUsername("李四");
        List<User> usersFromDB = userMapper.getUserList(user);
        usersFromDB.forEach(item -> System.out.println(item));
    }

    @Test
    void testSaveUserBatch() {
        User       user1 = new User(3, "wj", "", "");
        User       user2 = new User(4, "wj", "", "");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        int i = userMapper.saveUserBatch(users);
        System.out.println(i);
    }

    @Test
    void testSaveUser() {
        User user1 = new User(3, "wj", "", "");

        int i = userMapper.saveUser(user1);
        System.out.println(i);
    }

    @Test
    void test() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, org.springframework.batch.core.repository.JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParameters();
        JobExecution  run           = jobLauncher.run(db2FileJob, jobParameters);
    }
}
