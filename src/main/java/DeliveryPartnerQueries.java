import java.sql.*;

public class DeliveryPartnerQueries {

    public ResultSet getAllDelPartners(Connection conn) {


        try{

            Statement statement = conn.createStatement();

            String query = "select first_name, last_name, hourly_rate, is_fulltime " +
                    "from delpartners";

            return statement.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getDelPartnerById(Connection conn, int id){

        try{

            Statement statement = conn.createStatement();

            String query = "select first_name, last_name, hourly_rate, is_fulltime " +
                    "from delpartners where id=?";

            PreparedStatement ps= conn.prepareStatement(query);

            ps.setInt(1,id);

            return ps.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public int addNewDelPartner(Connection conn, String fname, String lname,double hourlyRate, boolean isFT){

        int status=0;

        try{
            String query="insert into delpartners"
                    + "(first_name, last_name, hourly_rate, is_fulltime)"
                    + "values(?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setDouble(3, hourlyRate);
            ps.setBoolean(4, isFT);

            status=ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public int applyPayAdjustment(Connection conn, double adjAmount, boolean isFT){

        int status=0;

        try{
            String query="update delpartners "
                    + "set hourly_rate = hourly_rate + ?"
                    +"where is_fulltime=?";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDouble(1,adjAmount);
            ps.setBoolean(2,isFT);

            status=ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public int deleteDeliveryPartner(Connection conn, int id){

        int status=0;
        try{

            Statement statement = conn.createStatement();

            String query = "delete from delpartners where id = ?";

            PreparedStatement ps= conn.prepareStatement(query);

            ps.setDouble(1,id);

            status = ps.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();

        }
        return status;
    }


}
