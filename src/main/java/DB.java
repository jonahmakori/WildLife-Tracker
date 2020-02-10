import org.sql2o.*;
public class DB {
    public static sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker","String","String");

}
