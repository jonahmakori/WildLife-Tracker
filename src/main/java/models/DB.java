package models;
import org.sql2o.Sql2o;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker","jonah","jonah1ma");

}


//import org.sql2o.*;
//        import java.net.URI;
//        import java.net.URISyntaxException;
//
//public class DB {
//    private static URI dbUri;
//    public static Sql2o sql2o;
//
//    static {
//
//        try {
//            if (System.getenv("DATABASE_URL") == null) {
//                dbUri = new URI("postgres://localhost:5432/wildlife_tracker");
//            } else {
//                dbUri = new URI(System.getenv("DATABASE_URL"));
//            }
//
//            int port = dbUri.getPort();
//            String host = dbUri.getHost();
//            String path = dbUri.getPath();
//            String username = (dbUri.getUserInfo() == null) ? "jonah" : dbUri.getUserInfo().split(":")[0];
//            String password = (dbUri.getUserInfo() == null) ? "jonah1ma" : dbUri.getUserInfo().split(":")[1];
//
//            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, "jonah", "jonah1ma");
//        } catch (URISyntaxException e ) {
//        }
//    }
//}