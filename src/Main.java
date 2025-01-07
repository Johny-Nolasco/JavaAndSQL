package src;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SampleDB;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "reallyStrongPwd123";
        
        DBManager sampleDB = new DBManager(url, user, password);

        sampleDB.connectToDB();

        // Select example
        String selectQuery = "SELECT * FROM Persons;";
        ResultSet rs = sampleDB.executeSelect(selectQuery);
        try {
            while (rs != null && rs.next()) {
                System.out.println("PersonID: " + rs.getInt("PersonID"));
                System.out.println("LastName: " + rs.getString("LastName"));
                System.out.println("FirstName: " + rs.getString("FirstName"));
                System.out.println("Age: " + rs.getInt("Age"));
                System.out.println("Address: " + rs.getString("Address"));
                System.out.println("City: " + rs.getString("City"));
                System.out.println("Country: " + rs.getString("Country"));
                System.out.println("ZipCode: " + rs.getString("ZipCode"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sampleDB.disconnectFromDB();
        }
    }
}
