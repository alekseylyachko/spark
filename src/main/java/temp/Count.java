package temp;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.lang.String;
import java.util.Arrays;

public class Count {

    public static void count(String appname, String master, SparkSession session, long nodeCount, String filepath) {
        SparkConf sparkConf = new SparkConf()
                .setAppName(appname)
                .setMaster(master);

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(filepath);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(l -> Arrays.asList(l.split(",")).iterator());

        JavaPairRDD countData = wordsFromFile.mapToPair(t -> new Tuple2(t, 1)).reduceByKey((x, y) -> (int) x + (int) y);

        countData.saveAsTextFile("CountData");
    }
}