INSERT INTO group_app(group_name) VALUES('user')
INSERT INTO group_app(group_name) VALUES('admin')

INSERT INTO user_app(user_name, pass_word, version) VALUES('maina', 'pFCQ5Wt2OcrjZe5VPTIf6O92MArcq4daBkuz8Ku8CNA=', 0)
INSERT INTO user_app(user_name, pass_word, version) VALUES('guest', 'pFCQ5Wt2OcrjZe5VPTIf6O92MArcq4daBkuz8Ku8CNA=', 0)

INSERT INTO users_groups_app(user_name, groups_group_name) VALUES('maina', 'user')
INSERT INTO users_groups_app(user_name, groups_group_name) VALUES('maina', 'admin')
INSERT INTO users_groups_app(user_name, groups_group_name) VALUES('guest', 'user')
