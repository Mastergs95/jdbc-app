
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCIntro {
    public static void main(String[]args){

        //SEARCH
       /* DeliveryPartnerQueries dpq= new DeliveryPartnerQueries();

       try(Connection conn = DBUtils.getMysqlConnection("DeliveryService")){

           System.out.println("\n#############\n");
           System.out.println("Retrieving all Delivery Partner details...\n");

           ResultSet rs=dpq.getAllDelPartners(conn);

           while(rs.next()){
               System.out.println(rs.getString("first_name")
               + "\t" + rs.getString("last_name")
               + "\t" + rs.getDouble("hourly_rate")
               + "\t" + rs.getBoolean("is_fulltime"));
           }

           System.out.println("\n#############\n");
           System.out.println("Retrieving the details of one partner...\n");

           rs= dpq.getDelPartnerById(conn,103);

           rs.next();

           System.out.println(rs.getString("first_name")
                   + "\t" + rs.getString("last_name")
                   + "\t" + rs.getDouble("hourly_rate")
                   + "\t" + rs.getBoolean("is_fulltime"));

       }
       catch (SQLException e){
           e.printStackTrace();
       }*/

        //INSERT
      /*  DeliveryPartnerQueries dpq= new DeliveryPartnerQueries();

       try(Connection conn = DBUtils.getMysqlConnection("DeliveryService")){

           System.out.println("\n#############\n");
           System.out.println("Adding a new partner...\n");

           int status = dpq.addNewDelPartner(conn,
                   "Gav","Comey",17,true);

           System.out.println("Status returned: " + status);

           System.out.println("\n#############\n");
           System.out.println("Retrieving all Delivery Partner details...\n");

           ResultSet rs= dpq.getAllDelPartners(conn);


           while(rs.next()){
               System.out.println(rs.getString("first_name")
                       + "\t" + rs.getString("last_name")
                       + "\t" + rs.getDouble("hourly_rate")
                       + "\t" + rs.getBoolean("is_fulltime"));
           }

       }
       catch (SQLException e){
           e.printStackTrace();
       }*/

        //UPDATE
        /*DeliveryPartnerQueries dpq= new DeliveryPartnerQueries();

        try(Connection conn = DBUtils.getMysqlConnection("DeliveryService")){

            System.out.println("\n#############\n");
            System.out.println("Updating hourly rates for FT partners...\n");

            int status = dpq.applyPayAdjustment(conn,2,true);

            System.out.println("Status returned: " + status);

            System.out.println("\n#############\n");
            System.out.println("Retrieving all Delivery Partner details...\n");

            ResultSet rs= dpq.getAllDelPartners(conn);


            while(rs.next()){
                System.out.println(rs.getString("first_name")
                        + "\t" + rs.getString("last_name")
                        + "\t" + rs.getDouble("hourly_rate")
                        + "\t" + rs.getBoolean("is_fulltime"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }*/

        //DELETE
        DeliveryPartnerQueries dpq= new DeliveryPartnerQueries();

        try(Connection conn = DBUtils.getMysqlConnection("DeliveryService")){

            System.out.println("\n#############\n");
            System.out.println("Deleting the user with id 104...\n");

            int status = dpq.deleteDeliveryPartner(conn,104);

            System.out.println("Status returned: " + status);

            System.out.println("\n#############\n");
            System.out.println("Retrieving all Delivery Partner details...\n");

            ResultSet rs= dpq.getAllDelPartners(conn);


            while(rs.next()){
                System.out.println(rs.getString("first_name")
                        + "\t" + rs.getString("last_name")
                        + "\t" + rs.getDouble("hourly_rate")
                        + "\t" + rs.getBoolean("is_fulltime"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
