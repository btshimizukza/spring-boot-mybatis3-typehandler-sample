package com.github.kazuki43zoo.sample;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface TodoRepository {

    //    @Select("SELECT todo_id, todo_title as todo_title_bytes, attachment_file, finished FROM todo WHERE todo_id = #{todoId}")
    @Results({
            @Result(column = "todo_title", property = "todoTitle", jdbcType = JdbcType.BLOB)
    })
    @Select({
            "SELECT todo_id, todo_title as todo_title, attachment_file, finished ",
            "FROM todo",
            "WHERE todo_id = #{todoId}"
    })
    Todo findOne(String todoId);

    @Insert({"INSERT INTO todo",
            "(todo_id, todo_title, attachment_file, finished)",
            "VALUES",
            "(#{todoId}, #{todoTitle,jdbcType=BLOB}, #{attachmentFile}, #{finished})"})
    void create(Todo todo);

}
