package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbretrieval
{

    /**
     * this class is only for testing purpose
     * insert json data into database (which is received from server response)
     * @return json data retrieved from database<String>result
     * @throws Exception
     */
    public String getdata() throws Exception
    {
        Maven1234 mv = new Maven1234();
        String receivedData = mv.calculated();

        Connection con =
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/metadata", "postgres", "ajchauhan");
        System.out.println("connection success");
        Statement st = con.createStatement();

        st.executeUpdate("INSERT INTO data(id,jsondata) VALUES (1,'" + receivedData + "')");
        String sql3 = "select jsondata from Data";
        ResultSet rs2 = st.executeQuery(sql3);
        rs2.next();
        String result = rs2.getString("jsondata");
        con.close();

        return result;
    }

}
