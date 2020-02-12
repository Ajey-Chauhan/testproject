package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Startproject
{
    /**
     * main class 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        dbretrieval db = new dbretrieval();
        db.getdata();  
    }
}
