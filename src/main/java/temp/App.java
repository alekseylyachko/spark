package temp;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;

import java.lang.String;

import temp.Load;


/**
 * Hello world!
 */
public class App {

    public static long nodeCount = 5;

    public static void main(String[] args) throws InterruptedException {


        SparkSession session = SparkSession
                .builder()
                .appName("SparkJavaExample")
                .master("local[" + nodeCount + "]")
                .getOrCreate();

        // JavaSparkContext context = new JavaSparkContext(session.sparkContext());
        // spark.


        Load.load(session, 5, "./dataset.csv");


        // try (JavaSparkContext context = new JavaSparkContext(session.sparkContext())) {
        //     JavaRDD<Integer> javaRDD = context.parallelize(integers, 3);

        //     javaRDD
        //             .foreach((VoidFunction<Integer>) integer -> {

        //          System.out.println("Java RDD:" + integer);
        //          Thread.sleep(3000);

        //     Thread.sleep(1000000);
        //     context.stop();
    }
}
