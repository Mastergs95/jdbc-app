import java.sql.*;
import java.util.Arrays;

public class JBDCAdvanced {

    public static void main(String[] args) throws SQLException {

        int[]count={};
        Connection conn = null;
        Savepoint sp = null;

        try {

            conn=DBUtils.getMysqlConnection("DeliveryService");
            conn.setAutoCommit(false);

            PreparedStatement insertPS=DBUtils.getInsertVehiclePS(conn);

           DBUtils.addToInsertVehiclePS(insertPS,16,"Red","Truck","LOONY16");
           DBUtils.addToInsertVehiclePS(insertPS,17,"Orange","Van","LOONY17");
           DBUtils.addToInsertVehiclePS(insertPS,18,"Blue","Van","LOONY18");
           DBUtils.addToInsertVehiclePS(insertPS,19,"Grey","Truck","LOONY19");

           count= insertPS.executeBatch();
           sp= conn.setSavepoint("BatchOne");
           System.out.println("First batch executed: " + Arrays.toString(count));

            DBUtils.addToInsertVehiclePS(insertPS,20,"Yellow","Truck","LOONY20");

            count= insertPS.executeBatch();
            System.out.println("Second batch executed: " + Arrays.toString(count));

            conn.commit();


        } catch (SQLException e) {

            if(sp!=null){
                System.out.println("An exception as thrown. Rolling back to " +sp.getSavepointName());
                conn.rollback(sp);
                conn.commit();
            }
            else{
                System.err.println("Errors detected. Rolling back everything...");
                conn.rollback();
            }
            e.printStackTrace();

        }
        finally {
            conn.close();
        }
    }
}
