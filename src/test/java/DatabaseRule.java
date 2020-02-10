import org.junit.rules.ExternalResource;
import org.sql2o.*;

import java.sql.Connection;

public class DatabaseRule extends ExternalResource{

    @Override
    protected void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test","String","String");

    }
    @Override
    protected void after(){
        try(Connection con = DB.sql2o.open()){
            String deleteAnimalsQuery = "DELETE FROM persons *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
        }
    }


}
