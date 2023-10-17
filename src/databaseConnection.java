import java.sql.*;

public class databaseConnection {
    public static final String CREATETABLEQUERY = "DROP TABLE Employee IF EXISTS; create table Employee(empId INTEGER, empName varchar2(50), empSal NUMBER )";
    public static final String INSERTQUERY1 ="INSERT INTO EMPLOYEE VALUES (1,'AK',1000)";
    public static final String INSERTQUERY2 ="INSERT INTO EMPLOYEE VALUES (2,'CHUTKI',100)";
    public static final String INSERTQUERY3 ="INSERT INTO EMPLOYEE VALUES (1,'GUVI',10)";
    public static final String SELECTQUERY ="SELECT * FROM Employee";
    public static final String UPDATEQUERY = "update EMPLOYEE set empName='Ak2' where empId=1";
    public static final String DELETEQUERY = "delete from EMPLOYEE where empSal=10";


    public static void main(String[] args) {
        try {

            int i=1;

            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:./test", "sa", "");


            if (conn != null) {
                System.out.println("Connected to the h2 database");
            }


            Statement stmt = conn.createStatement();

            stmt.execute(CREATETABLEQUERY);

            stmt.executeUpdate(INSERTQUERY1);
            stmt.executeUpdate(INSERTQUERY2);
            stmt.executeUpdate(INSERTQUERY3);



            ResultSet resultSet = stmt.executeQuery(SELECTQUERY);

            while (resultSet.next()) {
                System.out.println("My Emp " + i++ + " ID:-" + resultSet.getInt("empId") + "\t" + "NAME:-"
                        + resultSet.getString(2)
                        + " salary:-" + resultSet.getFloat(3));

            }
            int count = stmt.executeUpdate(UPDATEQUERY);

            System.out.println(count+"rows affected");
            ResultSet resultSet2=stmt.executeQuery(SELECTQUERY);
            int j=1;
            while (resultSet2.next())
            {
                System.out.println("My Emp "+j++ +" ID:-"+resultSet2.getInt("empId")+"\t"+"NAME:-"
                        +resultSet2.getString(2)
                        +" salary:-"+ resultSet2.getFloat(3));

            }
            count = stmt.executeUpdate(DELETEQUERY);

            System.out.println(count+"rows affected");

            ResultSet resultSet3=stmt.executeQuery(SELECTQUERY);
            int k=1;
            while (resultSet3.next())
            {
                System.out.println("My Emp "+k++ +" ID:-"+resultSet3.getInt("empId")+"\t"+"NAME:-"
                        +resultSet3.getString(2)
                        +" salary:-"+ resultSet3.getFloat(3));

            }



            ResultSetMetaData resultSetMetaData =stmt.getResultSet().getMetaData();
            System.out.println("My table name:"+resultSetMetaData.getTableName(1));
            System.out.println("Count of columns:"+resultSetMetaData.getColumnCount());

            for (int x=1;x<=resultSetMetaData.getColumnCount();x++)
            {
                System.out.println("Column "+x+" "+resultSetMetaData.getColumnLabel(x));
                System.out.println("Column "+x+" is of type:-"+resultSetMetaData.getColumnClassName(x));

            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException handled");
        }
        catch (SQLException ex) {
            System.out.println("SQLException handled"+ex.getMessage());
        }
        }
    }
