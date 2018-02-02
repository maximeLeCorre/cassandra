import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


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

        session.execute("DROP TABLE IF EXISTS school.lesson;");
    }
}
