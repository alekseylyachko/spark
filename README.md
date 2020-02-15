# INSTALL

## HADOOP

1. **Adding user:**

		$ adduser hadoop

2. **Install and add to PATH jdk v8**

3. **Configure ssh**

		$ sudo apt-get install openssh-server openssh-client

		$ ssh-keygen -t rsa

		$ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

		$ ssh localhost

4. **Download Hadoop**

	Login as hadoop:

		$ su - hadoop 

	In /home/hadoop:

		$ wget https://archive.apache.org/dist/hadoop/common/hadoop-2.8.5/hadoop-2.8.5.tar.gz

		$ tar xf hadoop-2.8.5.tar.gz

		$ mkdir hadoop

		$ mv hadoop-2.8.5 hadoop/
		

5. **Also create 'temp/hadooptmpdata', 'hdfs/datanode' and 'hdfs/namenode' in hadoop folder**

	In /home/hadoop/hadoop:

		$ mkdir -p hdfs/namenode
		$ mkdir -p hdfs/datanode
		$ mkdir -p temp/hadooptmpdata

6. **Alter PATH**

	Add to the end of '.bashrc' file in /home/hadoop:

		export HADOOP_HOME=/home/hadoop/hadoop/hadoop-2.8.5
		export HADOOP_INSTALL=$HADOOP_HOME
		export HADOOP_MAPRED_HOME=$HADOOP_HOME
		export HADOOP_COMMON_HOME=$HADOOP_HOME
		export HADOOP_HDFS_HOME=$HADOOP_HOME
		export YARN_HOME=$HADOOP_HOME
		export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
		export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin
		export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib/native"

7. **Configure 'hadoop/etc/hadoop/hadoop-env.sh'**

	Make the following changes:

		export JAVA_HOME= path_to_jdk_8
		export HADOOP_CONF_DIR=${HADOOP_CONF_DIR:-"/home/hadoop/hadoop/hadoop-2.8.5/etc/hadoop"}

8. **Configure 'hadoop/etc/hadoop/core-site.xml'**

	Make the following changes:

		<configuration>
		<property>
		<name>fs.defaultFS</name>
		<value>hdfs://localhost:9000</value>
		</property>
		<property>
		<name>hadoop.tmp.dir</name>
		<value>/home/hadoop/hadoop/temp/hadooptmpdata</value>
		</property>
		</configuration>

9. **Configure 'hadoop/etc/hadoop/hdfs-site.xml'**

	Make the following changes:

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

10. **Configure 'hadoop/etc/hadoop/mapred-site.xml'**

	Create:

		$ cp mapred-site.xml.template mapred-site.xml

	Make the following changes:

		<configuration>
		<property>
		<name>mapreduce.framework.name</name>
		<value>yarn</value>
		</property>
		</configuration>

11. **Configure 'hadoop/etc/hadoop/yarn-site.xml'**

		<configuration>
		<property>
		<name>mapreduceyarn.nodemanager.aux-services</name>
		<value>mapreduce_shuffle</value>
		</property>
		</configuration>

12. **Start hadoop**
	
	Format namenode:

		$ hdfs namenode -format

	Run filesystem:

		$ start-dfs.sh

	Run yarn:

		$ start-yarn.sh

	Check if running:

		$ jps

13. **HDFS Command Line Interface**

	Create test folders

		$ hdfs dfs -mkdir /test
		$ hdfs dfs -ls /


14. **Access from browser**
	
	Open [Yarn](0.0.0.0:50070)

	Open [Hadoop](0.0.0.0:8088)
	
## HIVE

1. **Make sure hadoop is installed**

		$ hadoop version

2. **Download Hive**

	In /home/hadoop/ run:

		$ wget http://apache-mirror.rbc.ru/pub/apache/hive/stable-2/apache-hive-2.3.6-bin.tar.gz

		$ tar xf apache-hive-2.3.6-bin.tar.gz

		$ mkdir hive

		$ mv apache-hive-2.3.6-bin hive/

3. **Alter PATH**

	Add to the end of .bashrc:

		export HIVE_HOME=/home/hadoop/hive/apache-hive-2.3.6-bin/
		export PATH=$PATH:$HIVE_HOME/bin
		export CLASSPATH=$CLASSPATH:/usr/local/Hadoop/lib/*:.
		export CLASSPATH=$CLASSPATH:/usr/local/hive/lib/*:.

4. **Configure 'apache-hive-2.3.6-bin/conf/hive-env.sh'**

	Create:

		$ cp hive-env.sh.template hive-env.sh

	Make the following changes:

		export HADOOP_HOME=/home/hadoop/hadoop/hadoop-2.8.5

5. **Verify**

	Run Hadoop:

		$ start-dfs.sh

	Run Yarn:

		$ start-yarn.sh

	Run Hive:

		$ hive

	In shell:

		\> show tables; 

<!-- 5. **Install Derby**

	Just as IN 1:

		$ wget http://archive.apache.org/dist/db/derby/db-derby-10.4.2.0/db-derby-10.4.2.0-bin.tar.gz


	The rest is as follows. Target folder structure is /home/hadoop/derby/db-derby-10.4.2.0-bin

6. **Alter PATH**

	Add to the end of .bashrc:

		export DERBY_HOME=/home/hadoop/derbry/db-derby-10.4.2.0-bin/
		export PATH=$PATH:$DERBY_HOME/bin
		export CLASSPATH=$CLASSPATH:$DERBY_HOME/lib/derby.jar:$DERBY_HOME/lib/derbytools.jar

7. **Create folder 'data'**

		$ mkdir $DERBY_HOME/data

8. **Configure Hive**

	Create:

		$ cd $HIVE_HOME/conf
		$ cp hive-default.xml.template hive-site.xml

	Edit 'hive-site.xml' (add between conf):

		<property>
		<name>javax.jdo.option.ConnectionURL</name>
		<value>jdbc:derby://localhost:1527/metastore_db;create=true </value>
		<description>JDBC connect string for a JDBC metastore </description>
		</property>

	Create a file named 'jpox.properties and add to it':

		javax.jdo.PersistenceManagerFactoryClass =

		org.jpox.PersistenceManagerFactoryImpl
		org.jpox.autoCreateSchema = false
		org.jpox.validateTables = false
		org.jpox.validateColumns = false
		org.jpox.validateConstraints = false
		org.jpox.storeManagerType = rdbms
		org.jpox.autoCreateSchema = true
		org.jpox.autoStartMechanismMode = checked
		org.jpox.transactionIsolation = read_committed
		javax.jdo.option.DetachAllOnCommit = true
		javax.jdo.option.NontransactionalRead = true
		javax.jdo.option.ConnectionDriverName = org.apache.derby.jdbc.ClientDriver
		javax.jdo.option.ConnectionURL = jdbc:derby://hadoop1:1527/metastore_db;create = true
		javax.jdo.option.ConnectionUserName = APP
		javax.jdo.option.ConnectionPassword = mine -->

# RUN WITH GRADLE



# TEST 

