# For examination

## 提出前にやること

- .git 消す

## デプロイ

```bash

```

### Usage

```bash
./gradlew clean build -x test
docker build -t myspringboot .
docker run -p 80:80 myspringboot
export AWS_ACCOUNT_ID=449307728240
export AWS_REGION=ap-northeast-1
aws ecr get-login-password | docker login --username AWS --password-stdin https://${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
docker tag myspringboot 449307728240.dkr.ecr.ap-northeast-1.amazonaws.com/exam:latest
docker push 449307728240.dkr.ecr.ap-northeast-1.amazonaws.com/exam:latest

# OpenAPI UIの表示
open http://localhost:80/swagger-ui/index.html

```

```bash

export HOST=ec2-18-183-172-134.ap-northeast-1.compute.amazonaws.com
mysql -h $HOST -u admin -pPassW0rd!

```

### ToDo

- EC2 インスタンス作成
- MySQL セットアップ

```mysql
# https://qiita.com/Hide-Zaemon/items/caa7ff79b3322b6af14a
# https://qiita.com/miriwo/items/457d6dbf02864f3bf296
GRANT CREATE, SELECT, INSERT, DELETE, UPDATE ON *.* TO admin@"%";

CREATE USER admin@"%" IDENTIFIED BY 'PassW0rd!';

CREATE DATABASE exam DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

- ローカルから MySQL にアクセスする
  mysql -u admin -h ec2-54-249-146-98.ap-northeast-1.compute.amazonaws.com -pPassW0rd! -D exam

- Copilot を使ってデプロイ

全ての API 実装
バリデーション
Exception
エラーメッセージ