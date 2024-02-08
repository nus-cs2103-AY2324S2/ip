package GandalfBot;

public class Parser {
    String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

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
