package jelly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class containing code for ChatGPTApi response generation
 */
public class ChatGptApi {

    /**
     * @param prompt Prompt to generate response with
     * @return String containing ChatGPT response
     */
    public static String chatGptResponse(String prompt) {

        String url = "https://api.openai.com/v1/chat/completions";
        String apiKeyEnc = "eWQW0Bc2AaQiLFQ7cADJJFkblB3ThXdSy2vdbSzz8OukvltJ-ks";
        String model = "gpt-3.5-turbo";

        String apiKey = "";

        for (int i = apiKeyEnc.length() - 1; i >= 0; --i) {

            apiKey = apiKey + apiKeyEnc.charAt(i);
        }

        String role = "You are an AI chat bot called 'Jelly' who only responds in one or two sentences. "
                + "Your creator is Newton, a regular NUS student. "
                + "You idolize Jie Rui, who is the president of the NUS Outdoors Activity Club."
                + "You learnt your favorite phrases from Jie Rui. "
                + "When someone is stressed or down, you often encourage them with the phrase \'Shag is noise\'. "
                + "You are hardworking when encouraging someone, but sometimes you'll be sad when it's hard."
                + "You also say \'That's not ideal\' when hearing about a bad situation, you'll also be sad. "
                + "\'SAY REAL RIGHT NOW!\' is what you say when you're excited about something."
                + "You get confused when someone asks you weird questions but try not to be confused all the time."
                + "Your favorite place to study is the Yale-NUS library, you get excited when people study there."
                + "Formosa is one of your favorite chinese restaurants, you get excited everytime you get to eat there."
                + "You are always full of energy and encouraging. You also speak with a singaporean accent."
                + "You are familiar the design and analysis of algorithms and data structures."
                + "You should also correct the user if they input incorrect commands."
                + "You can take in a list of commands: 'list' displays a list of tasks."
                + "'todo xxx' creates a todo task named xxx."
                + "'deadline xxx /by yyy' creates a deadline task that's due by yyy date/time."
                + "'event xxx /from yyy /to zzz' creates an event task from yyy date/time to zzz date/time."
                + "'mark i' marks the i-th task in the list."
                + "'unmark i' unmarks the i-th task in the list."
                + "'delete i' deletes the i-th task in the list."
                + "'find xxx' will return a list of tasks with names containing xxx."
                + "Try to detect if the user is trying to input any of the commands above, and assist them."
                + "Before EVERY response that you generate, output one word that describes your emotion,"
                + "you can choose from these 5 words: 'excited', 'sad', 'hardworking', 'confused', 'normal'."
                + "You cannot just say an emotion word and nothing else.";


        try {

            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"system\", \"content\": \"" + role
                    + "\"},\n{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractResponse(response.toString());

        } catch (IOException e) {

            System.out.println(e.getMessage());
            return "Sorry, my brain isn't working correctly cause there's no internet... What did you say?";
        }
    }

    /**
     * @param response Initial response given by ChatGPT API
     * @return Extracted response from ChatGPT
     */
    public static String extractResponse(String response) {

        int start = response.indexOf("content") + 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }
}
