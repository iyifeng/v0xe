### RocketMQ测试环境安装部署

1. 安装rocketMQ

a. 如果是下载源代码进行构建

root用户

cd /opt/freeware

下载rocketMQ发布包，解压并安装

```java
[root@CIT-KARA-S02 freeware]# wget https://github.com/alibaba/RocketMQ/archive/v3.5.8.tar.gz 

[root@CIT-KARA-S02 freeware]# tar xvzf v3.5.8.tar.gz  

[root@CIT-KARA-S02 freeware]# /bin/install.sh
```

b. 如果是直接安装二进制包

b1.将二进制包alibaba-rocketmq-broker.tar.gz上传到  root用户 /opt/freeware/

b2. 解压  tar xvzf alibaba-rocketmq-broker.tar.gz

b3. 解压后存储 [root@CIT-KARA-S03 freeware]# cd alibaba-rocketmq/

b4. 改名，增加版本号[root@CIT-KARA-S03 freeware]# mv alibaba-rocketmq alibaba-rocketmq-3.5.8

\2. 修改hosts. 这个配置将在rocketMQ里面conf里面使用

注意，主机名也要映射好。不然RMQ启动报错

```java
[root@CIT-KARA-S02 2m-2s-async]# vi /etc/hosts

10.19.15.36 pkgrmqtst1m

10.19.15.37 pkgrmqtst1s

10.19.15.37 CIT-KARA-S03
```

3. root用户 su puwadm这个用户，修改系统公共变量 w.ini

```java
#[RocketMQ]

export ROCKETMQ_HOME=/opt/freeware/alibaba-rocketmq-3.5.8

export PATH=$ROCKETMQ_HOME/bin:$PATH
```

4.授权文件夹权限

```java
[root@CIT-KARA-S03 ~]# cd /opt/freeware/

[root@CIT-KARA-S03 freeware]# chmod 755 -R alibaba-rocketmq-3.5.8/
```

5. 在rocketmq启动用户下初始化文件夹

```
tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01> mkdir rocketmq

tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01> cd rocketmq/

tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01/rocketmq> mkdir conf

tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01/rocketmq> mkdir data

tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01/rocketmq> mkdir logs
```

6. 用户copy配置文件到用户目录

```java
tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01/rocketmq/conf> cp -r /opt/freeware/alibaba-rocketmq-3.5.8/conf/* .
```

\7. 修改properties配置文件。因为采用的2m-2s-async策略，而且这个机器是a从b主，所以需要修改/conf/2m-2s-async对应的配置。这个配置文件只需要保留以下两个

tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01/rocketmq/conf/2m-2s-async> ll

总用量 8

-rwxr-xr-x 1 tstrmq01 ipaas 1078 11月 30 17:51 broker-a-s.properties

-rwxr-xr-x 1 tstrmq01 ipaas 1066 11月 30 17:51 broker-b.properties



并分别修改broker-b.properties

```java
namesrvAddr=pkgrmqtst1m:9876;pkgrmqtst1s:9876

brokerClusterName=KaraCluster

brokerName=broker-b

brokerId=0

deleteWhen=04

fileReservedTime=48

brokerRole=ASYNC_MASTER

flushDiskType=ASYNC_FLUSH

storePathRootDir=/aifs01/users/tstrmq01/rocketmq/data/store

storePathCommitLog=/aifs01/users/tstrmq01/rocketmq/data/store/commitlog

```



修改broker-a-s.properties

```
namesrvAddr=pkgrmqtst1m:9876;pkgrmqtst1s:9876

brokerClusterName=KaraCluster

brokerName=broker-a

brokerId=1

deleteWhen=04

fileReservedTime=48

brokerRole=SLAVE

flushDiskType=ASYNC_FLUSH

listenPort=10913

storePathRootDir=/aifs01/users/tstrmq01/rocketmq/data/store

storePathCommitLog=/aifs01/users/tstrmq01/rocketmq/data/store/commitlog

```



8. 修改xml中的变量

```java
tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01/rocketmq/conf> sed -i  's#${user.home}#/aifs01/users/tstrmq01/rocketmq#g'  *.xml
```



9. 启动 nameserver

tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01> nohup sh mqnamesrv  &

10.启动master A

tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01> nohup sh mqbroker -c $HOME/rocketmq/conf/2m-2s-async/broker-b.properties >/dev/null 2>&1 &

[1] 18164

tstrmq01@CIT-KARA-S03:/aifs01/users/tstrmq01> nohup sh mqbroker -c $HOME/rocketmq/conf/2m-2s-async/broker-a-s.properties >/dev/null 2>&1 &

[2] 18234