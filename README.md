# For examination

### Usage

```bash

# OpenAPI UIの表示
open http://localhost:8080/swagger-ui/index.html

```

```bash

export HOST=ec2-18-183-172-134.ap-northeast-1.compute.amazonaws.com
mysql -h $HOST -u admin -pPassW0rd! 

```

### ToDo

- EC2インスタンス作成
- MySQLセットアップ
```mysql
# https://qiita.com/Hide-Zaemon/items/caa7ff79b3322b6af14a
# https://qiita.com/miriwo/items/457d6dbf02864f3bf296
GRANT CREATE, SELECT, INSERT, DELETE, UPDATE ON *.* TO admin@"%";

CREATE USER admin@"%" IDENTIFIED BY 'PassW0rd!';

CREATE DATABASE exam DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```
- ローカルからMySQLにアクセスする
  mysql -u admin -h ec2-54-249-146-98.ap-northeast-1.compute.amazonaws.com -pPassW0rd! -D exam

- Copilotを使ってデプロイ

全てのAPI実装
バリデーション
Exception
エラーメッセージ

ported for this channel.
2023-03-18T19:16:37.328865Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Bind-address: '::' port: 33060, socket: /var/run/mysqld/mysqlx.sock
2023-03-18T19:16:37.328938Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.32'  socket: '/var/lib/mysql/mysql.sock'  port: 3306  MySQL Community Server - GPL.
2023-03-18T19:16:49.567281Z 8 [Warning] [MY-013360] [Server] Plugin sha256_password reported: ''sha256_password' is deprecated and will beremoved in a future release. Please use caching_sha2_password instead'