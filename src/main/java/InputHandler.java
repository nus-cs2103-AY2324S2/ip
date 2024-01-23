import java.util.Arrays;
import exceptions.BluException;
import exceptions.InvalidCommandException;
import exceptions.IllegalCommandException;

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

    private boolean isParamEmpty(int startIdx, int endIdx) {
        if (startIdx + 1 == endIdx) {
            return true;
        }
        return false;
    }

    public void handleInput(String userInput, Chatbot bot) throws BluException {
        String[] tokens = userInput.split(" ");
        String cmd = tokens[0];
        switch (cmd) {
            case "list":
                bot.displayTasks();
                break;
            case "mark":
                if (tokens.length != 2) {
                    throw new IllegalCommandException("Please specify a task number\n"
                                                            + "Usage: mark <task_number>");
                }
                try {
                    int markTaskIdx = Integer.parseInt(tokens[1]);
                    bot.markTask(markTaskIdx);

                } catch (NumberFormatException e) {
                    throw new IllegalCommandException(tokens[1] + " is not a valid integer!\n" 
                                                            + "Usage: mark <task_number>");
                }
                break;
            case "unmark":
                if (tokens.length != 2) {
                    throw new IllegalCommandException("Please specify a task number\n"
                                                            + "Usage: unmark <task_number>");
                }
                try {
                    int unmarkTaskIdx = Integer.parseInt(tokens[1]);
                    bot.unmarkTask(unmarkTaskIdx);
                } catch (NumberFormatException e) {
                    throw new IllegalCommandException(tokens[1] + " is not a valid integer!\n" 
                                                            + "Usage: unmark <task_number>");
                }
                break;
            case "todo":
                if (tokens.length < 2) {
                    throw new IllegalCommandException("Description of a todo cannot be empty.\n"
                                                            + "Usage: todo <task_title>");
                }
                String todoTitle = userInput.substring(cmd.length() + 1);
                bot.addTask(new ToDo(todoTitle));
                break;
            case "deadline":
                int baseIdx = 0;
                int paramIdx = findParamIdx(tokens, BY_PARAM);
                if (paramIdx == -1) {
                    throw new IllegalCommandException(BY_PARAM + " parameter not found!\n"
                                                            + "Usage: deadline <task_title> /by <datetime>");
                } 
                if (isParamEmpty(baseIdx, paramIdx)) {
                    throw new IllegalCommandException("Description of a deadline cannot be empty.\n"
                    + "Usage: deadline <task_title> /by <datetime>");
                }
                String deadlineTitle = getParamValue(tokens, baseIdx, paramIdx);
                if (isParamEmpty(paramIdx, tokens.length)) {
                    throw new IllegalCommandException("Datetime of deadline cannot be empty.\n"
                                                            + "Usage: deadline <task_title> /by <datetime>");
                }
                String deadlineBy = getParamValue(tokens, paramIdx, tokens.length);
                bot.addTask(new Deadline(deadlineTitle, deadlineBy));
                break;
            case "event":
                baseIdx = 0;
                int fromParamIdx = findParamIdx(tokens, FROM_PARAM);
                int toParamIdx = findParamIdx(tokens, TO_PARAM);
                if (fromParamIdx == -1 || toParamIdx == -1) {
                    throw new IllegalCommandException(FROM_PARAM + " or " + TO_PARAM + " not found!\n" 
                                                            + "Usage: event <task_title> /from <datetime> /to <datetime>");            
                } 
                
                if (isParamEmpty(baseIdx, fromParamIdx)) {
                    throw new IllegalCommandException("Description of event cannot be empty.\n"
                                                            + "Usage: event <task_title> /from <datetime> /to <datetime>");

                }
                String eventTitle = getParamValue(tokens, 0, fromParamIdx);
                if (isParamEmpty(fromParamIdx, toParamIdx) || isParamEmpty(toParamIdx, tokens.length)) {
                    throw new IllegalCommandException("Datetimes of event cannot be empty.\n"
                                                            + "Usage: event <task_title> /from <datetime> /to <datetime>");
                }
                String eventFrom = getParamValue(tokens, fromParamIdx, toParamIdx - 1);
                String eventTo = getParamValue(tokens, toParamIdx, tokens.length);
                bot.addTask(new Event(eventTitle, eventFrom, eventTo));
                break;
            default:
                throw new InvalidCommandException(cmd);
        }
    }
}