package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class filedata
{
    /**
     * @return json data i.e. expected json data for junit testing
     * @throws Exception
     */
    public String file() throws Exception
    {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream("C:\\java-project\\src\\test\\resources\\correctfile")));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        String result = sb.toString();
        Class cls = result.getClass();
        System.out.println("The type of the object is: " + cls.getName());
        System.out.println("\n\n\n" + result);

        return result;
    }
}
