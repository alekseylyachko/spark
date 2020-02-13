package temp;

import org.apache.spark.sql.SparkSession;

import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.lang.String;

import java.util.ArrayList;

public class Load {
    public static void load(SparkSession session, long nodeCount, String filepath) {
        StructType schema = new StructType()
                .add("name", "string")
                .add("region", "string")
                .add("date", "string")
                .add("value", "string");

        Dataset<Row> dataset = session.read()
                .option("mode", "DROPMALFORMED")
                .schema(schema)
                .csv(filepath);

        long rowCount = dataset.count();
        long singeBatchCount = rowCount / nodeCount;
        ArrayList<Dataset> batches = new ArrayList<Dataset>();

        dataset.createOrReplaceTempView("sber");

        Dataset<Row> processedDataset = session.sql(
            "SELECT row_number() " + 
            " OVER (order by name) AS rnk, " +
            " name FROM sber WHERE name != 'name'");

        processedDataset.createOrReplaceTempView("psber");
        
        for (int i = 0; i < nodeCount; i++ ) {
            Dataset<Row> tempDataset = session.sql(
            "SELECT rnk, name " +
            " FROM psber WHERE " +
            " rnk between " + i * singeBatchCount + 
            " and " + (i+1) * singeBatchCount +
            " GROUP BY rnk, name ");
            batches.add(tempDataset);
        }
    }
}