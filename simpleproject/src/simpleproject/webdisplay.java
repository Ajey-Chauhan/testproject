package simpleproject;

import project.Maven1234;
import project.dbretrieval;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class webdisplay extends HttpServlet
{
    /**
     * {@inheritDoc}
     * retrieve json data from database and display on webpage 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
    {

        PrintWriter out = res.getWriter();
        try {

            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("error class not found exception");
            e.printStackTrace();
        }
        try {
            Connection con =
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/metadata", "postgres", "ajchauhan");
            System.out.println("connection success");
            Statement st = con.createStatement();
            String sql3 = "select jsondata from Data";
            ResultSet rs2 = st.executeQuery(sql3);
            rs2.next();
            String result = rs2.getString("jsondata");
            out.print(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
