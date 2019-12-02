package org.auxilor;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.Buffer;
import java.nio.BufferUnderflowException;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Communicate implements Interface {
    void send(String message,String session) throws IOException {
        if(message.isBlank() || message.isEmpty()){
            return;
        }

        URL url = new URL("https://burst.willfp.com/send.php");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        String nl = "\n";

        Map<String, String> arguments = new HashMap<>();
        arguments.put("message", message+nl);
        arguments.put("session", session);
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        textField.setText("");

        textPane.setCaretPosition(textPane.getDocument().getLength());
    }
    void receive(String session) throws IOException {
        String url = "https://burst.willfp.com/sessions/" + session + "/conversation.txt";

        HttpURLConnection get = (HttpURLConnection) new URL(url).openConnection();
        get.setRequestMethod("GET");
        get.setRequestProperty("User-Agent", "Burst");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(get.getInputStream()))) {
            StringBuilder responce = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                responce.append(line + "\n");
            }
            textPane.setText(responce.toString());
        }



        /* prob the better one

        HttpClient test - failed reference in static content?

        HttpRequest get = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://burst.willfp.com/sessions" + session + "/conversation.txt"))
                .setHeader("User-Agent", "Burst")
                .build();
        HttpResponse<String> response = HttpClient.send(get, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
         */

        /* basic one needs work

        Buffered string reader - one line only? start new line?

        URL url = new URL("https://burst.willfp.com/sessions/" + session + "/conversation.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        while((inputLine = in.readLine()) != null)
            textPane.setText(inputLine+"\n");
        in.close();
         */
    }
}
