package xellsys.msintplugin.sample;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloWorldTest {

    @Test
    public void callHelloWorld() throws IOException {
        URL url = resolveUrl();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        Assert.assertEquals(readContent(con), "Hello World!");
    }


    private static URL resolveUrl() throws MalformedURLException {
        if (inDocker()) {
            return new URL("http://javahelloworld:8080/hello");
        } else {
            return new URL("http://localhost:8080/hello");
        }
    }

    private static boolean inDocker() {
        return new File("/.dockerenv").exists();
    }

    private String readContent(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}