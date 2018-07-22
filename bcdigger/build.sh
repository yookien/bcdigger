#!/bin/bash
set -o errexit

BASE_PATH=`pwd`
#BASE_PATH="/home/lhdocker/build-application/java"
JAVA_OPTS="-server -Dfile.encoding=UTF-8 -Xms1024m -Xmx2048m "

cd $BASE_PATH

    
#更新代码方法
pull(){
	echo "更新服务代码 \n"
	git pull --progress -v
}
#构建方法
build(){
	echo "maven编译打包服务\n"
	mvn clean package -P prod
}
#启动方法
start(){ 
	echo "启动服务"
	now=`date "+%Y%m%d%H%M%S"`  
	exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar $BASE_PATH/target/bcdigger-0.0.1-SNAPSHOT.jar 5 >/data/logs/start_"$now".log &   
}  
#停止方法  
stop(){  
	ps -ef|grep java|awk '{print $2}'|while read pid  
	do  
		kill -9 $pid  
	done  
}  

case "$1" in 
	build)
		pull
		build
		stop
		start
	;;
	start)  
		start  
	;;  
	stop)  
		stop  
	;;    
	restart)  
		stop  
		start  
	;;  
	*)
	printf '请输入正确的参数，Usage: %s {build|start|stop|restart}\n' "$prog"  
	exit 1  
	;;  
esac
