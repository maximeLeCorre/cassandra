import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    private static Cluster cluster;
    private static Session session;
    static Integer port = 9042;
    static String node = "127.0.0.1";

    public static void main(String[] args) {

        Cluster.Builder b = Cluster.builder().addContactPoint(node);

        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        session = cluster.connect();

        JSONParser parser = new JSONParser();


        try{
            Object obj = parser.parse(new FileReader("./stocks.json"));

            JSONArray jsonArray = (JSONArray) obj;

            //On boucle sur le json
            for(int i = 0; jsonArray.size() > i; i++){
                JSONObject toInsert = (JSONObject) jsonArray.get(i);

                String query = "INSERT INTO stockexchange.stock (" +
                        "\"20-day\"," +
                        "\"200-day\"," +
                        "\"50-day\"," +
                        "\"52-week\"," +
                        "analystrecom," +
                        "averagetruerange," +
                        "averagevolume," +
                        "beta," +
                        "change," +
                        "company," +
                        "epsttm," +
                        "earningdate," +
                        "price," +
                        "roi," +
                        "id," +
                        "description," +
                        "performance," +
                        "ratio)" +
                        "VALUES (";

                        String value = toInsert.get("20-Day Simple Moving Average") + ",";
                        value += toInsert.get("200-Day Simple Moving Average") + ",";
                        value += toInsert.get("50-Day") + ",";
                        value += toInsert.get("52-Week") + ",";
                        value +=toInsert.get("Analyst Recom") + ",";
                        value +=toInsert.get("Average True Range") + ",";
                        value +=toInsert.get("Average Volume") + "," ;
                        value +=toInsert.get("Beta") + ",";
                        value +=toInsert.get("Change") + ",";
                        value = value.replace("null", "0");

                        String company = (String) toInsert.get("Company");
                        value +="'" + company.replace("'", "-") + "',";

                        value = value.replace("null" , "'NULL'");
                        value +=toInsert.get("EPS ttm") + ",";
                        value = value.replace("null", "0");
                        value +=toInsert.get("Earnings Date") + ",";
                        value = value.replace("null", "{'$date': 'null'}");
                        value +=toInsert.get("Price") + ",";
                        value +=toInsert.get("ROI") + ",";
                        value = value.replaceAll("null" , "0");
                        value +=toInsert.get("_id") + ",";
                        value = value.replaceAll("null" , "'NULL'");
                        value +=toInsert.get("description") + ",";
                        value = value.replaceAll("null" , "'NULL'");
                        value +=toInsert.get("performance") + ",";
                        value +=toInsert.get("ratio") + ")";
                        value = value.replaceAll("null" , "0");


                value = value.replace('\"', '\'');

                query += value;
                System.out.println(query);
                session.execute(query);
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //session.execute("DROP TABLE IF EXISTS school.lesson;");
        session.close();

    }
}
