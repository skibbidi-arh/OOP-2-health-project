package Gemini;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeminiApi {

    public static String getResponseFromGemini(String prompt) {
        String apiKey = "AIzaSyDyijN-xM9Be0Tn7PgZdr23qx3EpyW4rzE";
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

        String jsonInput = String.format("""
            {
              "contents": [{
                "parts":[{"text": "%s"}]
              }]
            }
        """, prompt);

        try {
            URL url = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            InputStream responseStream = (status < 400) ? con.getInputStream() : con.getErrorStream();

            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(responseStream, "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
            }

            con.disconnect();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray candidates = jsonResponse.getJSONArray("candidates");
            JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
            JSONArray parts = content.getJSONArray("parts");
            return parts.getJSONObject(0).getString("text").trim();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
