DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
   user_id varchar(250) PRIMARY KEY,
   password varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
   nickname varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci  default '',
   comment varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci default '',
   created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at datetime on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (
    user_id,
    password,
    nickname,
    comment
) VALUES ('tamizuma','hashedpass', 'たた', '僕は元気です');
