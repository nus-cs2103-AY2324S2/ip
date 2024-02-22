package duke.conversation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Conversation {

    // OpenAI API endpoint
    private static final String endpoint = "https://api.openai.com/v1/chat/completions";

    // API key
    private String apiKey;

    // Conversation history
    private List<String> conversationHistory = new LinkedList<>();

    public Conversation() {

        try {
            Properties env = EnvLoader.loadEnvVariables(".env");
            this.apiKey = env.getProperty("OPENAI_API_KEY");
            if (this.apiKey == null || this.apiKey.isEmpty()) {
                throw new IllegalStateException("API key not found in .env file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load .env file.");
        }
    }


    /**
     * Generates a response using OpenAI's API based on the user's input message.
     *
     * @param userInput The user's input message.
     * @return The generated response.
     */
    public String generateResponse(String userInput) {
        HttpClient httpClient = HttpClient.newHttpClient();
        int maxResponseLength = 50;
        double temperature = 0.8;

        // Add user input to the conversation history
        conversationHistory.add("{\"role\": \"user\", \"content\": \"" + userInput.replace("\"", "\\\"") + "\"}");

        // Prepare the requestBody with the conversation history
        String requestBody = String.format(
                "{\"model\": \"gpt-3.5-turbo\", \"messages\": %s, \"max_tokens\": %d, \"temperature\": %f}",
                conversationHistory.toString(), maxResponseLength, temperature
        );

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (httpResponse.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(httpResponse.body());
                JSONArray choices = jsonResponse.getJSONArray("choices");
                if (choices.length() > 0) {
                    JSONObject firstChoice = choices.getJSONObject(0);
                    JSONObject message = firstChoice.getJSONObject("message");
                    String content = message.getString("content");

                    // Add the bot's response to the conversation history
                    conversationHistory.add("{\"role\": \"assistant\", \"content\": \"" + content.replace("\"", "\\\"") + "\"}");

                    return content;
                } else {
                    return "Received unexpected response structure from API.";
                }
            } else {
                System.out.println("Error Status Code: " + httpResponse.statusCode());
                System.out.println("Error Body: " + httpResponse.body());
                return "Error processing your request";
            }
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
            return "Error parsing API response: " + e.getMessage();
        }
    }

    /**
     * Resets the conversation history, starting a new conversation.
     */
    public void resetConversation() {
        conversationHistory.clear();
    }
}