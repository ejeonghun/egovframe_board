INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (19, NULL, 13, 'test1', '새로운 댓글 수정합니다.!!!', TIMESTAMP'2024-07-23 16:58:50', TIMESTAMP'2024-07-23 17:02:19');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (20, 19, 13, 'test1', '[답글] 그렇군요!!', TIMESTAMP'2024-07-23 17:00:26', TIMESTAMP'2024-07-23 17:00:26');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (21, NULL, 13, 'ex', '잘되는거 같아요', TIMESTAMP'2024-07-23 17:09:20', TIMESTAMP'2024-07-23 17:09:20');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (22, 19, 13, 'ex', '답글도 잘되는거 같습니다.', TIMESTAMP'2024-07-23 17:09:53', TIMESTAMP'2024-07-23 17:10:18');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (10, NULL, 7, 'user1', '123123admin', TIMESTAMP'2024-07-23 14:27:43', TIMESTAMP'2024-07-23 15:59:34');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (11, 10, 7, 'user1', '123123123', TIMESTAMP'2024-07-23 14:27:46', TIMESTAMP'2024-07-23 14:27:46');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (12, NULL, 11, 'user1', '123123', TIMESTAMP'2024-07-23 15:16:55', TIMESTAMP'2024-07-23 15:16:58');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (14, NULL, 7, 'test1', '댓글 테스트 123123123123', TIMESTAMP'2024-07-23 16:02:54', TIMESTAMP'2024-07-23 16:02:54');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (15, 10, 7, 'test1', '답글 테스트321312', TIMESTAMP'2024-07-23 16:03:07', TIMESTAMP'2024-07-23 16:03:07');
INSERT INTO [TEST.replies] (id, parent_id, post_id, author, content, created_at, updated_at) VALUES (32, 19, 13, 'user1', '답글 테스트123', TIMESTAMP'2024-07-24 15:59:10', TIMESTAMP'2024-07-24 15:59:16');
