INSERT INTO group_app(group_name) VALUES('user')
INSERT INTO group_app(group_name) VALUES('admin')

INSERT INTO user_app(user_name, pass_word) VALUES('maina', 'sAdj8QfgC4t/wlED465dOifrPgiQuHS4OiZbWz3SdVVSAI+gf/yV1/foanAR3Ynp0Uaxobpi/rrflzHaUyGjNQ==')
INSERT INTO user_app(user_name, pass_word) VALUES('guest', 'sAdj8QfgC4t/wlED465dOifrPgiQuHS4OiZbWz3SdVVSAI+gf/yV1/foanAR3Ynp0Uaxobpi/rrflzHaUyGjNQ==')

INSERT INTO users_groups_app(users_user_name, groups_group_name) VALUES('maina', 'user')
INSERT INTO users_groups_app(users_user_name, groups_group_name) VALUES('maina', 'admin')
INSERT INTO users_groups_app(users_user_name, groups_group_name) VALUES('guest', 'user')
