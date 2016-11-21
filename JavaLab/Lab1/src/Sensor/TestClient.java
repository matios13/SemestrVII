package Sensor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class TestClient {
    public static void main(String[] args) throws Exception{
        String url = "http://localhost:8000/get?hello=word&foo=bar";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }
}
