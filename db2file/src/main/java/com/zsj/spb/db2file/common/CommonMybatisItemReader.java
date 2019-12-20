package com.zsj.spb.db2file.common;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;

public class CommonMybatisItemReader<T> extends MyBatisPagingItemReader<T> {

    public CommonMybatisItemReader(SqlSessionFactory sqlSessionFactory,String name) {
        setSqlSessionFactory(sqlSessionFactory);
        setQueryId("com.sl.entity."+name+".getUserList");
        setPageSize(100);
    }
}
