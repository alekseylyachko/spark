package temp;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;

import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {


        SparkSession session = SparkSession
                .builder()
                .appName("SparkJavaExample")
                .master("local[3]")
                .getOrCreate();

        StructType schema = new StructType()
                .add("name", "string")
                .add("region", "string")
                .add("date", "string")
                .add("value", "string");

        Dataset<Row> dataset = session.read()
                .option("mode", "DROPMALFORMED")
                .schema(schema)
                .csv("./dataset.csv");

        dataset.createOrReplaceTempView("sber");

        Dataset<Row> sqlResult = session.sql(
            "SELECT DISTINCT region " 
        + " FROM sber GROUP BY region ");

        sqlResult.show(); //for testing



        try (JavaSparkContext context = new JavaSparkContext(session.sparkContext())) {
        //     JavaRDD<Integer> javaRDD = context.parallelize(integers, 3);

        //     javaRDD
        //             .foreach((VoidFunction<Integer>) integer -> {

        //          System.out.println("Java RDD:" + integer);
        //          Thread.sleep(3000);
        //             });

        //     Thread.sleep(1000000);
        //     context.stop();
        }
    }
}
