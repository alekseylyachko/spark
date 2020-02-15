# INSTALL

## HADOOP

1. Adding user:

		$ adduser hadoop

2. Install and add to PATH jdk v8

3. Configure ssh

		$ sudo apt-get install openssh-server openssh-client

		$ ssh-keygen -t rsa

		$ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

		$ ssh localhost

4. Download 'hadoop-2.8.5.tar.gz' from [link](https://archive.apache.org/dist/hadoop/common/hadoop-2.8.5/) and exopt it to hadoop folder

	**In /home/hadoop:**

		$ mkdir hadoop

	**In /home/hadoop/Downloads:**

		$ tar xf hadoop-2.8.5.tar.gz -d ../hadoop

5. Also create 'temp/hadooptmpdata', 'hdfs/datanode' and 'hdfs/namenode' in hadoop folder

	**In /home/hadoop/hadoop:**

		$ mkdir -p hdfs/namenode
		$ mkdir -p hdfs/datanode
		$ mkdir -p temp/hadooptmpdata

6. Alter PATH

	**Add to the end of '.bashrc' file in /home/hadoop:**

		export HADOOP_HOME=/home/hadoop/hadoop/hadoop-2.8.5
		export HADOOP_INSTALL=$HADOOP_HOME
		export HADOOP_MAPRED_HOME=$HADOOP_HOME
		export HADOOP_COMMON_HOME=$HADOOP_HOME
		export HADOOP_HDFS_HOME=$HADOOP_HOME
		export YARN_HOME=$HADOOP_HOME
		export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
		export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin
		export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib/native"

7. Configure hadoop/etc/hadoop/hadoop-env.sh

	**Make the following changes:**

		export JAVA_HOME= path_to_jdk_8
		export HADOOP_CONF_DIR=${HADOOP_CONF_DIR:-"/home/hadoop/hadoop/hadoop-2.8.5/etc/hadoop"}

8. Configure hadoop/etc/hadoop/core-site.xml

	**Make the following changes:**

		<configuration>
		<property>
		<name>fs.defaultFS</name>
		<value>hdfs://localhost:9000</value>
		</property>
		<property>
		<name>hadoop.tmp.dir</name>
		<value>/home/hadoop/hadoop/hadooptmpdata</value>
		</property>
		</configuration>

9. Configure hadoop/etc/hadoop/hdfs-site.xml

	**Make the following changes:**

		<configuration>
		<property>
		<name>dfs.replication</name>
		<value>1</value>
		<name>dfs.name.dir</name>
		<value>file:///home/hadoop/hadoop/hdfs/namenode</value>
		<name>dfs.data.dir</name>
		<value>file:///home/hadoop/hadoop/hdfs/datanode</value>
		</property>
		</configuration>

10. Configure hadoop/etc/hadoop/mapred-site.xml

	**Create:**

		$ cp mapred-site.xml.template mapred-site.xml

	**Make the following changes:**

		<configuration>
		<property>
		<name>mapreduce.framework.name</name>
		<value>yarn</value>
		</property>
		</configuration>

11. Configure hadoop/etc/hadoop/yarn-site.xml

		<configuration>
		<property>
		<name>mapreduceyarn.nodemanager.aux-services</name>
		<value>mapreduce_shuffle</value>
		</property>
		</configuration>

12. Start hadoop
	
	**Format namenode:**

		$ hdfs namenode -format

	**Run filesystem:**

		$ start-dfs.sh

	**Run yarn:**

		$ start-yarn.sh

	**Check if running:**

		$ jps

13. HDFS Command Line Interface

	**Create test folders**

		$ hdfs dfs -mkdir /test
		$ hdfs dfs -ls /



14. Access from browser
	
	**Open [Yarn](0.0.0.0:50070)**

	**Open [Hadoop](0.0.0.0:8088)**
	
## HIVE


Download from [link](http://apache-mirror.rbc.ru/pub/apache/hive/stable-2/)

# RUN WITH GRADLE 



# TEST 

