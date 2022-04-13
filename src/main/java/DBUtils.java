import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {

    private static final String dbURL = "jdbc:mysql://localhost:3306/";
    private static final String username = "root";
    private static final String password = "12345";

    public static Connection getMysqlConnection (String schemaName){

        MysqlDataSource mysqlDS = null;
        Connection conn = null;

        try{
            mysqlDS=new MysqlDataSource();

            mysqlDS.setURL(dbURL+schemaName);
            mysqlDS.setUser(username);
            mysqlDS.setPassword(password);

            conn = mysqlDS.getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static JdbcRowSet getJdbcRowSet(String schemaName){

        JdbcRowSet jdbcRS = null;

        try{
            jdbcRS= RowSetProvider.newFactory().createJdbcRowSet();

            jdbcRS.setUrl(dbURL+schemaName);
            jdbcRS.setUsername(username);
            jdbcRS.setPassword(password);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return jdbcRS;
    }

    public static CachedRowSet getCacheRowSet(String schemaName){

        CachedRowSet cacheRS=null;

        try{
            cacheRS = RowSetProvider.newFactory().createCachedRowSet();

            cacheRS.setUrl(dbURL+schemaName);
            cacheRS.setUsername(username);
            cacheRS.setPassword(password);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return cacheRS;
    }

    public static PreparedStatement getInsertVehiclePS(Connection conn){

        try{
            return conn.prepareStatement("insert into delvehicles values(?,?,?,?)");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addToInsertVehiclePS(PreparedStatement ps, int id,
                                               String color, String vehicleType,
                                               String lPlate){
        boolean addedQuery=false;

        try{
            ps.setInt(1,id);
            ps.setString(2,color);
            ps.setString(3,vehicleType);
            ps.setString(4,lPlate);
            ps.addBatch();

            addedQuery=true;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return addedQuery;
    }
}


