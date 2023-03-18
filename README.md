# For examination

## 提出前にやること

- .git消す

## デプロイ

```bash
export AWS_REGION=ap-northeast-1
export AWS_DEFAULT_PROFILE=personal 

```

### Usage

```bash
docker build -t myexam .
docker run -p 8080:8080 myexam
docker stop


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
