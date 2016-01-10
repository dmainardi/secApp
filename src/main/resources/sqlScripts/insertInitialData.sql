INSERT INTO group_app(group_name) VALUES('user')
INSERT INTO group_app(group_name) VALUES('admin')

INSERT INTO user_app(user_name, pass_word) VALUES('maina', 'a45090e56b7639cae365ee553d321fe8ef76300adcab875a064bb3f0abbc08d0')
INSERT INTO user_app(user_name, pass_word) VALUES('guest', 'a45090e56b7639cae365ee553d321fe8ef76300adcab875a064bb3f0abbc08d0')

INSERT INTO users_groups_app(user_name, groups_group_name) VALUES('maina', 'user')
INSERT INTO users_groups_app(user_name, groups_group_name) VALUES('maina', 'admin')
INSERT INTO users_groups_app(user_name, groups_group_name) VALUES('guest', 'user')
