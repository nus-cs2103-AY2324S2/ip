package gandalf;

/**
 * Class to interpret user's input
 */
public class Parser {
    String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Interprets the instance-level userInput field by checking for double-spacing and structure it to the
     * different information types
     *
     * @return An array of stringbuilders containing the various information required
     */
    public StringBuilder[] interpret() {
        StringBuilder taskType = new StringBuilder();
        StringBuilder taskName = new StringBuilder();
        StringBuilder date1 = new StringBuilder();
        StringBuilder date2 = new StringBuilder();
        StringBuilder[] parsedInput = {taskType, taskName, date1, date2};
        int curr_info = 0;
        for(int i = 0; i < userInput.length(); i++) {
            char curr_char = userInput.charAt(i);
            if(curr_char == ' ') {
                if(userInput.charAt(i - 1) == ' ') {
                    curr_info++;
                    continue;
                }
            }
            parsedInput[curr_info].append(curr_char);
        }
        return parsedInput;
    }
}
