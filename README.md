# redis

﻿**redis-5.0.2	安装目录：/opt/**

cd /usr/local/bin					**进入redis服务所在的目录**

redis-benchmark					**测试数据库的性能**

redis-server /myredis/redis.conf	**用/myredis/目录下的redis.conf配置文件启动redis服务**

redis-cli -p 6379					**进入redis命令模式**

ping							**如果输出PONG则说明开启成功**

ps -ef|grep redis 					**显示有关redis有关的进程**
lsof -i : 6379						**查看redis进程6379是否启动**

-A 　**显示所有程序。** 

-e 　**此参数的效果和指定"A"参数相同。**

-f 　**显示UID,PPIP,C与STIME栏位。** 

kill -9 [进程号]					**结束进程,-9发送的信号是SIGKILL，即exit**

## redis命令

shutdown	**停止所有客户端，关闭 redis 服务器(server)，退出当前服务**

exit			**退出redis**

flushdb		**删除当前数据库的键值对**

flushall		**删除所有库中的键值对**

select 0		**换到第一个数据库**

select 15   	**换到最后一个数据库**

### String类型

set k1 v1   	**设置key为k1,value为v1的键值对**

get k1		**得到k1的值**

dbsize		**查询当前数据库有几个键值对**

keys *		**查询当前数据库所有的key**

keys k?		**查询当前数据库以k开头后一个任意字符的key**

exists k1		**查询当前数据库是否存在key为k1**

move k1 1	**将k1的键值对移到数据库1**

ttl k1		**查看当前数据库key为k1的生命时间(s) -1：无期限  -2:过期**

type k1		**查看k1的类型**



append k1 abc	**在key为k1的value值后面添加abc**

strlen k1		**查看k1的value长度**



set k2 2

incr k2		使k2的**值+1（不能有字母等）**

decr k2		使k2的**值-1**

incrby k2 22	使k2的**值+22**

decrby k2 22	使k2的**值-22**

set k3 v3

setrange k3 1 \*\**	**会将k3的value变为:v\*\****

getrange k3 0 2	**输出:v\****

setrange k3 6 \*\**	**会将k3的value变为v\*\**\x00\x00\*\*\***

setex k4 10 v4	**设置一个string类型，过去时间为10秒（set with expire）键秒值**

setnx k3 v32123	**如果k3没有value则赋值，如果有则不赋值（set if not exist）** 

mset k1 v1 k2 v2 k3 v3	**将k1、k2、k3的value分别赋值为v1、v2、v3**

mget k1 k2 k3			**输出k1、k2、k3的值**

msetnx k4 v4 k5 v5		**如果k4、k5都没有value则分别赋值为v4，v5，返回1，否则返回0** 

### list类型

lpush l1 1 2 3 4 5	**添加一个list**

rpush l2 1 2 3 4 5	**添加一个list**

lrange l1 0 -1 	**输出l1的全部值(lpush会输出5 4 3 2 1，rpush会输出1 2 3 4 5)**

#type l1		输出值为list

del l1		**删除l1**

lpop l1		**输出list里左边的元素，lpush第一个会输出5，rpush会输出1**

​			**输出后list里面不会再有这个元素（出栈）**
rpop l1		**输出list里右边的元素，lpush第一个会输出1，rpush会输出5**

​			**输出后list里面不会再有这个元素（出栈）**

lindex list02 2	**输出list02第三个元素**
​	
llen list02		**输出list02的长度**

rpush list03 1 1 1 2 2 2 3 3 3 4 4 4 5 6 7

lren list03 2 3	**输出list03里面的两个3**

ltrim list01 0 4	**截取list01里面0到4的元素为新的list01**

rpoplpush list01 list02	**将list01最后一个元素出栈，压栈为list02第一个元素**

lset list01 1 x		**将list01里面下标为1的元素赋值为x**

LINSERT list01 before x java	**在list01里面x元素前添加一个值为java的元素**

LINSERT list01 after x oracle	**在list01里面x元素后添加一个值为oracle的元素**

### set类型

sadd set01 1 1 2 2 3 3 	**创建一个key为set01，值为1、2、3的set（一键多值，单值多value ）**

smembers set01			**输出set01全部数据**

sismember set01 1		**判断set01是否有1这个值**

scard set01				**获取set01集合里面元素的个数**

srem set01 3				**移除值为3的数据** 

srandmember set01 3	**从set01里面随机抽3个数字**

spop key				**随机出栈**

smove key1 key2 3		**将key1里的3赋值给key2**

##### 数学集合类

​	**差集**：sdiff
​	**交集**：sinter
​	**并集**：sunion
sadd set01 1 2 3 4 5
sadd set02 1 2 3 a b c

sdiff set01 set02 			输出差集：4 5
sinter set01 set02 		输出交集：1 2 3
sunion set01 set02 		输出并集：1 2 3 4 5 a b c（都是无顺序）

del set01				**删除set01**

### hash类型

hset user id 11		**设置hash，键为user，键值对为id 11**
hset user name zs	**在user的hash中继续添加键为name，值为zs的数据**
hget user id			**获取hash键位user中键为id的值**

hmset customer id 1 name ls age 22	**在customer的hash中添加3个键值对**
hmget customer id name age			**获得customer的hash中对应的值**

hgetall customer		**获得customer的所有键值对**

hdel user name		**删除user的hash中键为name的键值对**

hlen user			**输出user的hash中有几个键值对(hash的长度)**

hexists customer id	**判断customer的id是否有id键(有输出1)**

hkeys customer		**输出customer的hash所有的键**

hvals customer		**输出customer的hash所有的值**

hincrby customer age 2	**customer的hash的age值加2**

hset customer score 91.5	
hincrbyfloat customer score 0.5		**customer的hash的score值加0.5**

hsetnx customer email abc@123.com	**如何custome的hash没有email的键值对则添加，否则添加失败**

### zset类型（在set基础上，加一个score值）

**之前set是k1 v1 v2 v3**
**现在zset是k1 score1 v1 score2 v2**

zadd zset01 60 v1 70 v2 80 v3 90 v4 100 v5	**添加一个zset**

zrange zset01 0 -1 						**遍历zset**

zrange zset01 0 -1 withscores				**遍历zset,附上score**

zrangebyscore zset01 60 90				**遍历zset中score在60到90的数据**

zrangebyscore zset01 60 (90				**遍历zset中score大于等于60小于90的数据**

zrangebyscore zset01 (60 (90				**遍历zset中score大于60小于90的数据**

zrangebyscore zset01 60 90 limit 2 2		**输出第三、四条数据**	

zrem zset01 v5		**删除v5（score 100同时被删除）**

zcard zset01			**统计zset01里面元素个数**

zcount zset01 60 80	**统计score从60到80的个数**	

zrank zset01 v4		**输出zset01里面v4值的下标（从0开始）**

zscore zset01 v4		**输出zset01里面v4值的score值**		

zrevrank zset01 v4	**输出zset01里面v4值的逆序下标值**

zrevrange zset01 0 -1 	**逆向输出zset01的全部数据**

zrevrangebyscore zset01 90 60	**输出zset01里面90到60的全部数据**



config get dir						**输出当前路径（启动redis服务时的路径）**

config get requirepass			**输出redis的密码，默认为""**

config set requirepass “123456”	**设置redis的密码为123456**	

`设置后ping会失败`

auth 123456						**输入redis的密码，之后ping才会成功**





### 持久化之RDB

拷贝rdb文件	cp dump.rdp dump_bk.rdp(真实情况下需要把rdp文件拷贝到另一台机)

如果想**禁用rdb持久化**的策略，只有不设置任何的save指令，或者给sava传入一个空字符串参数也可以(**进redis.conf文件修改SNAPSHOTTING 下的配置代码**)23:04 2019/1/6

```c'f
save 900 1		：表示900 秒内如果至少有 1 个 key 的值变化，则保存
save 300 10		：表示300 秒内如果至少有 10 个 key 的值变化，则保存
save 60 10000	：表示60 秒内如果至少有 10000 个 key 的值变化，则保存
```

save或者bgsave	立即生成dump.rdb文件

**save时只管保存，其他不管，全部阻塞**

**bgsave时，Redi会在后台异步进行快照操作，快照同时还可以响应客户端请求。**可以通过lastsave命令获取最后一次成功执行快照的时间

**执行flushall命令也会长生dump.rdb文件，但里面是空的，无意义**

**注意：shudown--》exit	后也会立即产生dump.rdp文件**

**redis会启动时自动读取rdb文件**

appendonly.aof文件和dump.rdb文件默认保存在当前路径下（config get dir查看当前路径）

### 持久化之aof

拷贝/myredis目录下的redis.conf文件为redis_aof.conf文件

修改redis_aof.conf文件下APPEND ONLY MODE的

**appendonly no	---->	appendonly yes	开启aof**

会将redis保存为appendonly.aof文件（可以使用vim手动修改里面的数据，redis启动时会自动读取该文件）

当appendonly.aof文件和dump.rdb文件同时存在，**redis优先加载appendonly.aof**

redis-check-aof --fix appendonly.aof	**修复appendonly.aof文件**

**其他aof的配置**

appendfsync		always	 **同步持久化，每次发生数据变更会被立即记录到磁盘，性能较差但数据完整性比较好**
​				everysec **出厂默认推荐，异步操作，每秒记录，如果一秒内宕机，有数据丢失**
​				no	 	**不同步**

no-appendfsync-on-rewrite no		**重写时是否运用Appendfsync，用默认no即可， 保证数据安全性**

auto-aof-rewrite-percentage 100	**设置重写的基准值（重写后aof文件大小为上次的一倍）**

auto-aof-rewrite-min-size 64mb	**设置重写的基准值（当aof文件大于64m触发重写机制,如果大型的话，一般3g起步）**

bgrewriteaof	**重写aof文件（aof采用文件追加方式，文件会越来越大，为避免出现该情况，新增了重写机制，当aof文件的大小超过所设定的阙值时，redis就会启动aof的文件的内容压缩，只保留可以恢复数据的最小指令集，可以使用命令bgrewriteaof）**





**aof优势**：每秒同步：appendfsync always	同步持久化，每次发生数据变更会被立即记录到磁盘  性能较差但数据完整性比较好
​	 每修改同步：appendfsync everysec  异步操作，每秒记录   如果一秒内宕机，有数据丢失
​	 不同步：appendfsync no  从不同步
​	 

**aof劣势**：相同数据集的数据而言aof文件要远大于rdb文件，恢复速度慢于rdb
​	 	aof运行效率要慢于rdb，每秒同步策略效率较好，不同步效率和rdb相同

同时开启两种持久化方式：RDB的数据不实时，同时使用两者时服务器重启也只会找AOF文件。那要不要只使用AOF呢？

​			作者建议不要，因为RDB更适合用于备份数据库（AOF在不断变化不好备份）

​			快速重启，而且不会有AOF可能潜在的bug，留着作为一个万一的手段

**因为rdb文件只用作后备用途，建议只在slave上持久化rdb文件，而且只要15分钟备份一次就够了，只保留save 900 1这条规则**











### Redis事务命令

discard		**取消事务，放弃 执行 事务块内 的 所有命令 **

exec		**执行所有事务块内的命令，一旦执行，exec之前加的监控锁都会被取消掉**

multi		**标记一个事务块的 开始**

unwatch		**取消watch命令对所有key的监视**

watch key [key ...]	**监视一个(或多个)key，如果在事务执行之前这个(或这些)key被其他命令所改动，那么事务将被打断**

> 先使用multi开启事务，执行redis语句后都会返回QUEUED（加入队列）
> 直到输入exec后一次性执行语句（批处理）
> 要取消就输入discard
> 如果队列中有语句错误（如getset k3这种，会返回error，正常的会返回QUEUED），则全部不执行
> 如果是例如incr k1报错，不影响其余的语句	(error) ERR value is not an integer or out of range

**watch监控**

悲观锁/乐观锁/CAS（Check And Set）

悲观锁:为避免出事，每次拿数据时都把整张表锁了，并发性极差，一致性极好

乐观锁：在表上添加一个新字段Version，提交版本必须大于记录当前版本才能执行更新，如果重复则重新提交直到不冲突

watch指令，类似乐观锁 ，事务提示时，如果key的值已被别的客户端改变，比如某个list已被别的客户端push/pop过了，整个事务队列都不会被执行

如：
set k1 100

watch k1			其他客户端：set k1 222

multi

set k1  111

exec	会返回(nil)

输入get k1会返回222

通过watch命令在事务执行之前监控了多个keys，倘若在watch之后有任何key的值发生了变化，exec命令执行的事务都将被放弃，同时返回Nullmulti-bulk应答以通知调用者事务执行失败

**3阶段**

开启：以MULTI开始一个事务

入队：将多个命令入队到事务中，接到这些命令并不会立即执行，而是放到等待执行的事务队列里面

执行：由exec命令触发事务

**3特性**

单独的隔离操作：事务中的所有命令都会序列化、按顺序地执行。事务在执行过程中，不会被其他客户端发送来的命令请求所打断。

没有隔离级别的概念：队列中的命令没有提交之前都不会实际的被执行，以为事务提交前任何指令都不会被实际执行，也就不存在“事务内的查询要看到事务里的更新，在事务外查询不能看到”这个问题。

不保证原子性：redis同一个事务中如果有一条命令执行失败，其后的命令仍让会被执行，没有回滚。









### Redis的发布订阅（一般消息中间件都不用这个，了解一下即可）

是什么

**进程间的一种消息通信模式，发送者(pub)发送消息，订阅者(sub)接收消息。**

案例（先订阅后发布才能接收到消息）

1、可以一次性订阅多个，subscribe c1 c2 c3

2、消息发布   publish c2 hello-redis

3、订阅多个，通配符*，psubscribe new*

4、收取消息，publish new1 redis2019



### redis主从复制

能干嘛--------------**读写分离（从机不能写）、容灾恢复**

从库配置：slaveof 主库ip 主库端口

每次与master断开之后，都需要重新连接，除非你配置进redis.conf文件

info replication	查看信息（role默认都位master）

slaveof 127.0.0.1 6379	备份当前机器6379端口下的redis数据，再次查看信息，role会变为slave

如果6379端口（主机）继续添加数据，从机的数据会默认备份到

主机shutdown后，从机会原地待命，主机启动后自动连接

从机shutdown后，自动断开连接，需要重新连接

常用3招：一主二从、薪火相传、反客为主

一主二从：一台主机后2台从机

薪火相传：一个主机，一条从机链，上一个slave可以是下一个slave的master，slave同样可以接收其他	slaves的连接和同步请求，那么该slave作为链条中下一个的master，有效减轻master的写压力

​	会清除之前的数据，重新建立拷贝最新的

反客为主：使当前数据库停止与其他数据库的同步，转为主数据库

​	slaveof no one

复制原理：slave启动成功连接到master后会发送一个sync命令


​	Master接到命令启动后台的存盘进程，同时收集所有接收到的用于修改数据集命令，在后台进程执行完毕之后，master将传送整个数据文件到slave，以完成一次完全同步

​	全量复制：slave服务在接收到数据库文件数据后，将其存盘并加载到内存中。

​	增量复制：master继续将新的所有收集到的修改命令依次传给slave，完成同步

​	但是只要是重新连接master，一次完成同步(全量复制)将被自动执行

哨兵模式：反客为主的自动版，能够后台监控主机是否故障，如果故障了根据投票数自动将从库转换为主库

在myredis目录下创建sentinel.conf文件

touch sentinel.conf

修改sentinel.conf文件	vim sentinel.conf

sentinel monitor 被监控数据库名字(自己起名字) 127.0.0.1 6379 1(1代表谁的票多于1票则为新的主机)

如：sentinel monitor diaohost6379 127.0.0.1 6379 1

启动哨兵：redis-sentinel /myredis/sentinel.conf		目录依照各自的实际情况配置

如果原来的master重启后，会自动转为新选出的主机的从机

复制缺点：复制延时
