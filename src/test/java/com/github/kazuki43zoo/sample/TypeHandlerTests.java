package com.github.kazuki43zoo.sample;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StreamUtils;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TypeHandlerTests.LocalContext.class)
public class TypeHandlerTests {

    @Autowired
    TodoRepository todoRepository;

    @Configuration
    @EnableAutoConfiguration
    @ComponentScan
    @MapperScan(basePackageClasses = TodoRepository.class)
    public static class LocalContext {

        @Bean
        SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
            return factoryBean;
        }

    }

    @Test
    public void test() throws IOException {

        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTodoTitle("title");
        todo.setAttachmentFile(new ByteArrayInputStream("image!".getBytes(StandardCharsets.UTF_8)));
        todo.setFinished(true);

        todoRepository.create(todo);

        Todo loadedTodo = todoRepository.findOne(todo.getTodoId());

        Assert.assertThat(loadedTodo.getTodoTitle(), Is.is("title"));
        Assert.assertThat(StreamUtils.copyToString(loadedTodo.getAttachmentFile(), StandardCharsets.UTF_8), Is.is("image!"));

    }

}
