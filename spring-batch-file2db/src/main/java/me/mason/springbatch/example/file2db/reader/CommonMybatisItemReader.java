package me.mason.springbatch.example.file2db.reader;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;

/**
 * @Description TODO
 * @Date 2019/12/24 16:28
 * @Author zsj
 */
public class CommonMybatisItemReader<T> extends MyBatisPagingItemReader<T> {

    public CommonMybatisItemReader(SqlSessionFactory sqlSessionFactory, String QueryId) {
        setSqlSessionFactory(sqlSessionFactory);
        setQueryId(QueryId);
        setPageSize(100);
    }
}

