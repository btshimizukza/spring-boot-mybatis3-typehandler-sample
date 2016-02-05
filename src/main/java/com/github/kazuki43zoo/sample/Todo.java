package com.github.kazuki43zoo.sample;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Todo {
    private String todoId;

    private String todoTitle;
    private InputStream attachmentFile;

    private boolean finished;

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

//    public byte[] getTodoTitleBytes() {
//        return todoTitle.getBytes(StandardCharsets.UTF_8);
//    }
//    public void setTodoTitleBytes(byte[] todoTitle) {
//       this.todoTitle = new String(todoTitle,StandardCharsets.UTF_8);
//    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public InputStream getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(InputStream attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}