#!/bin/sh
SHORT_SERVER_IP=$1
UPDATE_SNAPSHOT=$2

SERVICE_NAME=cloud-monikafkamsg

# default value
ENV=test
SERVER_USER=jindi
SERVER_PWD=jindi
SERVER_PORT=22
SERVICE_BASEDIR=/data/services/cloud-monikafkamsg
SERVER_IP=10.2.2.127;

case "$SHORT_SERVER_IP" in
"test")
	SERVER_IP=10.2.2.127;
	ENV=test
	SERVER_USER=jindi
	SERVER_PWD=jindi
	SERVICE_BASEDIR=/data/services/cloud-monikafkamsg
	;;
"test63")
	SERVER_IP=10.2.2.63;
	ENV=test
	SERVER_USER=jindi
	SERVER_PWD=jindi
	SERVICE_BASEDIR=/data/services/cloud-monikafkamsg
	;;
"test199")
	SERVER_IP=10.2.2.199;
	ENV=test
	SERVER_USER=jindi
	SERVER_PWD=jindi
	SERVICE_BASEDIR=/data/services/cloud-monikafkamsg
	;;
"yf238")
	SERVER_IP=101.201.254.143;
	ENV=yufa
	SERVER_USER=work
	SERVER_PWD=yjyqhyxxcyshys
	SERVICE_BASEDIR=/data/cloud-monikafkamsg
	;;

esac

basedir=`pwd`
rm -rf  $basedir/target/* >/dev/null 2>&1
if [ -n "$UPDATE_SNAPSHOT" ];then
echo "mvn clean package -P$ENV -DskipTests "
mvn clean package -P$ENV -DskipTests 
else
echo "mvn -U clean package -P$ENV -DskipTests "
mvn -U clean package -P$ENV -DskipTests 
fi

cd target

if [ -e  $SERVICE_NAME*.war ]
then mv  $SERVICE_NAME*.war $SERVICE_NAME.war
else
exit 0
fi

# spawn  scp  -P $SERVER_PORT $SERVICE_NAME.war $SERVER_USER@$SERVER_IP:$SERVICE_BASEDIR;

suffix=`date '+%Y%m%d%H%M%S'`

expect -c "\
     set timeout 1200; \
	   spawn rsync -avz --delete --exclude=node_modules --exclude=bower_components --exclude=.tmp ./$SERVICE_NAME/ $SERVER_USER@$SERVER_IP:$SERVICE_BASEDIR/workspace/$SERVICE_NAME-$suffix/
     expect \"password:\"; \
     send \"$SERVER_PWD\r\"; \
     expect \"total size\"; \
     spawn ssh -P$SERVER_PORT $SERVER_USER@$SERVER_IP;\
     expect \"password:\"; \
     send \"$SERVER_PWD\r\"; \
     expect \"@\"; \
     send \"rm -f $SERVICE_BASEDIR/workspace/$SERVICE_NAME \r \";\
     send \"ln -s $SERVICE_BASEDIR/workspace/$SERVICE_NAME-$suffix $SERVICE_BASEDIR/workspace/$SERVICE_NAME \r \"; \
     send \"$SERVICE_BASEDIR/restart.sh\r\"; \
     expect \"successfully\"; \
     send \"exit\r\"; \
         "
echo "exec done at time `date`"