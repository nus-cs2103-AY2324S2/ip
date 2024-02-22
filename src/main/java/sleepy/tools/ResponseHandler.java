package sleepy.tools;

/**
 * This class helps to formulate the Sleepy AI Chatbot's response.
 *
 * @author kjw142857
 */
public class ResponseHandler {

    private static StringBuilder responseString = new StringBuilder();

    /**
     * Appends a given line to the response.
     *
     * @param line Line to be appended.
     */
    public static void appendLineToResponse(String line) {
        responseString.append(line + "\n");
    }

    /**
     * Appends an error message.
     *
     * @param error Error which occurred.
     */
    public static void appendError(String error) {
        ResponseHandler.appendLineToResponse(error + " Nice try, you won't catch me sleeping :p");
    }

    /**
     * Returns and clears the response string.
     */
    public static String returnResponse() {
        String response = responseString.toString();
        responseString.setLength(0);
        assert responseString.length() == 0 : "Chatbot cache should be cleared after responding";
        return response;
    }

    /**
     * Clears the response string.
     */
    public static void clearResponse() {
        responseString.setLength(0);
    }
}
