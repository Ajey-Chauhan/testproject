package project;

import java.io.IOException;
import java.net.URI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Maven1234
{
    /**
     * @return json data in <String>responseBody
     * takes @param from database and request required json data
     * @throws Exception
     */
    public String calculated() throws Exception
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            Connection con =
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/metadata", "postgres", "ajchauhan");
            System.out.println("connection success");
            Statement st = con.createStatement();
            String sql = "select version,id,p,caller,platform,cc,l from parameter";
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            String version = String.valueOf(rs.getInt("version"));
            String id = String.valueOf(rs.getInt("id"));
            String p = rs.getString("p");
            String caller = rs.getString("caller");
            String platform = rs.getString("platform");
            String cc = rs.getString("cc");
            String l = rs.getString("l");
            rs.next();

            URI uri = new URIBuilder().setScheme("http").setHost("uclient-api.itunes.apple.com")
                .setPath("/WebObjects/MZStorePlatform.woa/wa/lookup").setParameter("version", version)
                .setParameter("id", id).setParameter("p", p).setParameter("caller", caller)
                .setParameter("platform", platform).setParameter("cc", cc).setParameter("l", l).build();

            HttpGet httpget = new HttpGet(uri);

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>()
            {

                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException
                {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);

            con.close();

            return responseBody;

        } finally {
            httpclient.close();

        }
    }

}
