package com.willfp.burst;

import java.io.IOException;
import java.net.*;

public class Junk {
    public static void main(String args[]) throws IOException {
        String message = "Stop Being a cunt";
        int session = 6669420;
        try {
            URL url = new URL("http://206.189.112.220/help2.php?message="+ URLEncoder.encode(message, "UTF-8") +"&session="+session );
            System.out.println(url);
            URLConnection con = url.openConnection();
            con.connect();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}