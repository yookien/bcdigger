#!/bin/bash
set -o errexit

BASE_PATH=`pwd`
#BASE_PATH="/home/lhdocker/build-application/java"

JAVA_OPTS="-server -Dfile.encoding=UTF-8 -Xms1024m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=512m "

cd $BASE_PATH
echo "更新服务代码 \n"
git pull --progress -v
echo "maven编译打包服务\n"
mvn clean package -P prod

echo "启动服务"
nohup java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar $BASE_PATH/target/bcdigger-0.0.1-SNAPSHOT.jar &
