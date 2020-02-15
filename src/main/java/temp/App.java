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

        // Count.count(appname, master, session, 5, filepath);
    }
}
