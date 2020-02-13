package temp;

import org.apache.spark.sql.SparkSession;

import java.lang.String;

import temp.Load;
import temp.Count;


/**
 * Hello world!
 */
public class App {

    public static long nodeCount = 5;
    public static String filepath = "./dataset.csv";
    public static String master = "local[" + nodeCount + "]";
    public static String appname = "SparkJavaExample";

    public static void main(String[] args) throws InterruptedException {


        SparkSession session = SparkSession
                .builder()
                .appName(appname)
                .master(master)
                .getOrCreate();


        // Load.load(session, 5, filepath);

        Count.count(appname, master, session, 5, filepath);
        
    

    



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
