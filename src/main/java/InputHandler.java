import java.util.Arrays;

public class InputHandler {
    private static final String BY_PARAM = "/by";
    private static final String FROM_PARAM = "/from";
    private static final String TO_PARAM = "/to";

    private int findParamIdx(String[] tokens, String param) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(param)) {
                return i;
            }
        }
        return -1;
    }

    private String getParamValue(String[] tokens, int paramIdx, int endIdx) {
        String[] subArray = Arrays.copyOfRange(tokens, paramIdx + 1, endIdx);
        String paramVal = String.join(" ", subArray);
        return paramVal;
    }

    public void handleInput(String userInput, Chatbot bot) {
        String[] tokens = userInput.split(" ");
        String cmd = tokens[0];
        switch (cmd) {
            case "list":
                bot.displayTasks();
                break;
            case "mark":
                int markTaskIdx = Integer.parseInt(tokens[1]);
                bot.markTask(markTaskIdx);
                break;
            case "unmark":
                int unmarkTaskIdx = Integer.parseInt(tokens[1]);
                bot.unmarkTask(unmarkTaskIdx);
                break;
            case "todo":
                String todoTitle = userInput.substring(cmd.length() + 1);
                bot.addTask(new ToDo(todoTitle));
                break;
            case "deadline":
                int paramIdx = findParamIdx(tokens, BY_PARAM);
                if (paramIdx == -1) {
                    System.out.println("Invalid command");
                    break;
                }
                String deadlineTitle = getParamValue(tokens, 1, paramIdx);
                String deadlineBy = getParamValue(tokens, paramIdx, tokens.length);
                bot.addTask(new Deadline(deadlineTitle, deadlineBy));
                break;
            case "event":
                int fromParamIdx = findParamIdx(tokens, FROM_PARAM);
                int toParamIdx = findParamIdx(tokens, TO_PARAM);
                if (fromParamIdx == -1 || toParamIdx == -1 || fromParamIdx > toParamIdx) {
                    System.out.println("Invalid command");
                    break;
                }
                String eventTitle = getParamValue(tokens, 1, fromParamIdx);
                String eventFrom = getParamValue(tokens, fromParamIdx, toParamIdx - 1);
                String eventTo = getParamValue(tokens, toParamIdx, tokens.length);
                bot.addTask(new Event(eventTitle, eventFrom, eventTo));
                break;        
        }
    }
}