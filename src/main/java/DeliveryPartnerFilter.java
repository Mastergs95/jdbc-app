
import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;

public class DeliveryPartnerFilter implements Predicate {

    private int lowRate;
    private int highRate;
    private boolean isFT;
    private String colNameRate = null;
    private String colNameIsFT = null;
    private int colNumberRate = -1;
    private int colNumberIsFT = -1;

    public DeliveryPartnerFilter(int lo, int hi, boolean isFT, int colNumberRate, int colNumberIsFT) {
        this.lowRate = lo;
        this.highRate = hi;
        this.isFT = isFT;
        this.colNumberRate = colNumberRate;
        this.colNumberIsFT = colNumberIsFT;
    }

    public DeliveryPartnerFilter(int lo, int hi, boolean isFT, String colNameRate, String colNameIsFT) {
        this.lowRate = lo;
        this.highRate = hi;
        this.isFT = isFT;
        this.colNameRate = colNameRate;
        this.colNameIsFT = colNameIsFT;
    }

    @Override
    public boolean evaluate(RowSet rs) {

        if (rs == null) {

            return false;

        }

        FilteredRowSet frs = (FilteredRowSet) rs;
        boolean evaluation = false;

        try {

            double columnValueRate = frs.getDouble(this.colNumberRate);
            boolean columnValueIsFT = frs.getBoolean(this.colNumberIsFT);

            if ((columnValueRate >= this.lowRate)
                    && (columnValueRate <= this.highRate)
                    && (columnValueIsFT == this.isFT)) {

                evaluation = true;

            }

        } catch (SQLException e) {

            return false;

        }

        return evaluation;
    }



    @Override
    public boolean evaluate(Object value, int columnNumber) throws SQLException {

        boolean evaluation = true;

        if (colNumberRate == columnNumber) {

            double columnValue = ((Double) value).doubleValue();

            if ((columnValue >= this.lowRate) && (columnValue <= this.highRate)) {

                evaluation = true;

            } else {

                evaluation = false;

            }
        }
        return evaluation;
    }


    @Override
    public boolean evaluate(Object value, String columnName) throws SQLException {

        boolean evaluation = true;

        if (columnName.equalsIgnoreCase(this.colNameRate)) {

            double columnValue = ((Double) value).doubleValue();

            if ((columnValue >= this.lowRate) && (columnValue <= this.highRate)) {

                evaluation = true;

            } else {

                evaluation = false;
            }
        }
        return evaluation;
    }

}