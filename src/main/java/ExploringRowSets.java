import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.SQLException;


public class ExploringRowSets {

    public static void displayRow(String label, RowSet rowSet) throws SQLException {

        String fName = rowSet.getString("first_name");
        String lName = rowSet.getString("last_name");
        double hourlyRate = rowSet.getDouble("hourly_rate");
        boolean isFT = rowSet.getBoolean("is_fulltime");

        String stdData = "\n%s: %s | %s | %.2f | %s \n";
        System.out.format(stdData, label, fName, lName, hourlyRate, isFT);
    }

/*    public static void main(String [] args){

        try(JdbcRowSet jdbcRS = DBUtils.getJdbcRowSet("DeliveryService")) {

            jdbcRS.setCommand("select * from delpartners where is_fulltime=false");
            jdbcRS.execute();

            jdbcRS.last();
            int numPT = jdbcRS.getRow();
            System.out.println("Number of part-time partners: " + numPT);


            if (numPT < 5) {

                jdbcRS.moveToInsertRow();

                jdbcRS.updateString("first_name", "Kylie");
                jdbcRS.updateString("last_name", "Kass");
                jdbcRS.updateDouble("hourly_rate", 22.0);
                jdbcRS.updateBoolean("is_fulltime", false);

                jdbcRS.insertRow();

                jdbcRS.last();
                displayRow("Added part-time partner: " , jdbcRS);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }


            *//*System.out.println("The properties of JdbcRowSet are: \n");

            System.out.println("Read-only?" + jdbcRS.isReadOnly());
            System.out.println("Auto-commit?" + jdbcRS.getAutoCommit());
            System.out.println("Fetch direction?" + jdbcRS.getFetchDirection());
            System.out.println("Code for FETCH_FORWARD?" + jdbcRS.FETCH_FORWARD);
            System.out.println("Command: " + jdbcRS.getCommand());

            System.out.println("\n------------\n");
            System.out.println("Metadata: \n" + jdbcRS.getMetaData());*//*

     *//*System.out.println(jdbcRS.getString("first_name")
                +"\t" + jdbcRS.getString("last_name")
                +"\t" + jdbcRS.getDouble("hourly_rate")
                +"\t" + jdbcRS.getBoolean("is_fulltime"));*//*


     *//*
            jdbcRS.relative(2);
            displayRow("relative(2): ", jdbcRS);

            jdbcRS.absolute(4);
            displayRow("absolute(4): ", jdbcRS);

            System.out.println("\nSleeping for a minute...");
            Thread.sleep(30000);

            jdbcRS.last();
            jdbcRS.refreshRow();
            displayRow("last: ",jdbcRS);

            jdbcRS.previous();
            displayRow("previous: ",jdbcRS);

            jdbcRS.relative(-1);
            displayRow("relative(-1): ",jdbcRS);*//*

        }

    }*/

/*    public static void main(String[] args) {

        try (JdbcRowSet jdbcRS = DBUtils.getJdbcRowSet("DeliveryService")) {

            jdbcRS.setCommand("select * from delpartners");
            jdbcRS.execute();

            int removedRows = 0;

           while(jdbcRS.next()){

               if(jdbcRS.getBoolean("is_fulltime")==true
                        && jdbcRS.getDouble("hourly_rate")>20){

                   displayRow("Removing row: ",jdbcRS);
                   jdbcRS.deleteRow();
                   removedRows++;
               }
           }

            System.out.println("Number of deleted rows: " + removedRows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

       /* public static void main(String[] args) {

        try (CachedRowSet cacheRS = DBUtils.getCacheRowSet("DeliveryService")) {

            System.out.println("\n########\n");
            System.out.println("Retrieving all Delivery Partner details...\n");

            cacheRS.setCommand("select * from delpartners");
            cacheRS.execute();

            int rowNum=1;

            while(cacheRS.next()){

                displayRow("Row #" + rowNum,cacheRS);
                rowNum++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    //INNER JOIN
   /* public static void main(String[] args) {

        try (Connection conn = DBUtils.getMysqlConnection("DeliveryService")) {

            CachedRowSet vehiclesRS = DBUtils.getCacheRowSet("");
            vehiclesRS.setCommand("select * from delvehicles");
            vehiclesRS.execute(conn);

            CachedRowSet deliveriesRS = DBUtils.getCacheRowSet("");
            deliveriesRS.setCommand("select * from deliveries");
            deliveriesRS.execute(conn);

            CachedRowSet partnersRS = DBUtils.getCacheRowSet("");
            partnersRS.setCommand("select * from delpartners");
            partnersRS.execute(conn);

            JoinRowSet joinRSPartnerDelivery = RowSetProvider.newFactory().createJoinRowSet();
            JoinRowSet joinRSVehicleDelivery = RowSetProvider.newFactory().createJoinRowSet();
            JoinRowSet joinRS = RowSetProvider.newFactory().createJoinRowSet();


            joinRSVehicleDelivery.addRowSet(vehiclesRS,"vid");
            joinRSVehicleDelivery.addRowSet(deliveriesRS,"vid");

            joinRSPartnerDelivery.addRowSet(partnersRS,"pid");
            joinRSPartnerDelivery.addRowSet(deliveriesRS.createCopyNoConstraints(),"pid");

            joinRS.addRowSet(joinRSVehicleDelivery,"did");
            joinRS.addRowSet(joinRSPartnerDelivery,"did");

            int rowNum = 1;

            while (joinRS.next()) {
                String fName = joinRS.getString("first_name");
                String color = joinRS.getString("color");
                String vType = joinRS.getString("vehicle_type");
                String destination = joinRS.getString("destination");

                String stdData = "\nRow #%d: %s | %s | %s | %s";
                System.out.format(stdData, rowNum, fName, color, vType, destination);
                rowNum++;
            }

            joinRS.close();
            joinRSPartnerDelivery.close();
            joinRSVehicleDelivery.close();
            vehiclesRS.close();
            deliveriesRS.close();
            partnersRS.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {

        try (Connection conn = DBUtils.getMysqlConnection("DeliveryService")) {

            FilteredRowSet partnersRS = RowSetProvider.newFactory().createFilteredRowSet();

            partnersRS.setCommand("select first_name, last_name, hourly_rate, is_fulltime "
                    + "from delpartners");
            partnersRS.execute(conn);

            DeliveryPartnerFilter customFilter=
                    new DeliveryPartnerFilter(19,21,false,3,4);

            partnersRS.setFilter(customFilter);

            int rowNum = 1;

            while(partnersRS.next()){

                displayRow("Row #" + rowNum,partnersRS);
                rowNum++;
            }
            partnersRS.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

