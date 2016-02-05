
create table if not exists todo (
    todo_id varchar(36) primary key,
    todo_title blob,
    attachment_file blob,
    finished boolean
);
