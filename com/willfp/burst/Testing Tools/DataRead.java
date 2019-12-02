package com.willfp.burst;

import java.io.*;
import java.net.*;

public class DataRead {
    public static void main(String[] arg) throws IOException {
        URL url = new URL("https://burst.willfp.com/sessions/Public/conversation.txt");
        BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}