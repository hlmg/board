Insert into post(title, content, is_deleted)
values ('post1', 'content1', false);

Insert into post(title, content, is_deleted)
values ('removedPost', 'content2', true);

Insert into post(title, content, is_deleted)
values ('post3', 'content3', false);

Insert into comment(content, post_id, is_deleted)
values ('removedComment', 1, true);

Insert into comment(content, post_id, is_deleted)
values ('comment2', 1, false);

Insert into comment(content, post_id, is_deleted)
values ('comment3', 1, false);
