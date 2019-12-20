package com.zsj.spb.db2file.config;

import com.zsj.spb.db2file.common.CommonFileItemWriter;
import com.zsj.spb.db2file.common.CommonMybatisItemReader;
import com.zsj.spb.db2file.entity.User;
import com.zsj.spb.db2file.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class Db2FileBatchConfig {
    @Autowired
    public JobBuilderFactory              jobBuilderFactory;
    @Autowired
    public StepBuilderFactory             stepBuilderFactory;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    public Job db2FileStepJob(Step db2FileStep,JobExecutionListener file2DbListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(file2DbListener)
                .flow(db2FileStep)
                .end().build();
    }


    @Bean
    public Step db2FileStep() {
        return stepBuilderFactory
                .get("db2FileStep")
                .<User, User>chunk(10)
                .reader(db2FileReader())
                .processor(db2FileProcessor())
                .writer(db2FileWriter())
                .build();
    }


    @Bean
    public Db2FileProcessor db2FileProcessor() {
        return new Db2FileProcessor();
    }
    @Bean
    public CommonMybatisItemReader<User> db2FileReader() {
        return new CommonMybatisItemReader<User>(sqlSessionFactory,"UserMapper");
    }

    @Bean
    public CommonFileItemWriter<User> db2FileWriter() {
        return new CommonFileItemWriter<>(User.class);
    }

}
