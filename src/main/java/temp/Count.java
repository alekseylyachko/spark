package temp;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.Serializable;

import java.lang.String;
import java.util.Arrays;

public class Count {

    public class Record implements Serializable {
      String name;
      String region;
      String date;
      String value;
      // constructor , getters and setters
    }

    public static void count(String appname, String master, SparkSession session, long nodeCount, String filepath) {
        try(JavaSparkContext sparkContext = new JavaSparkContext(session.sparkContext())) {
            
            JavaRDD<String> data = sparkContext.textFile(filepath);

            // JavaRDD<Record> rdd_records = sparkContext.textFile(data).map(
            //     new Function<String, Record>() {
            //         public Record call(String line) throws Exception {
            //             String[] fields = line.split(",");
            //             Record sd = new Record(fields[0], fields[1], fields[2], fields[3]);
            //             return sd;
            //         }
            //     }
            // );

            // JavaSchemaRDD table = sqlContext.applySchema(rdd_records, Record.class);
            // table.registerAsTable("record_table");
            // table.printSchema();    
        }
    }
}