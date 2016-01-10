./asadmin create-auth-realm \
--classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm \
--property \
jaas-context=jdbcRealm:\
datasource-jndi=jdbc/__default:\
user-table=user_app:\
user-name-column=users_user_name:\
password-column=pass_word:\
group-table=users_groups_app:\
group-name-column=groups_group_name:\
digest-algorithm=SHA-512:\
digestrealm-password-enc-algorithm=AES:\
encoding=Base64:\
charset=UTF-8 \
JDBCRealm
