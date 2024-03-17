package hal.gui;

import hal.exception.HALException;

import java.util.ArrayList;

public class Parser {

    public ArrayList<String> parse(String userInput) throws HALException {
        String[] userInputArray = userInput.split(" ");
        String taskType = userInputArray[0].toLowerCase();
        ArrayList<String> outputArray = new ArrayList<>();

        if (userInputArray.length == 1) {
            // Missing description error
            throw new HALException("Missing description!");

        // If task description is not empty, proceed as per normal
        } else {
            switch (taskType) {
            case "todo":
                String description = userInput.substring(4).trim();

                outputArray.add(taskType);
                outputArray.add(description);
                break;

            case "deadline":
                int keywordByIndex = userInput.indexOf("/by");
                if (keywordByIndex != -1) {
                    String descriptionDeadline = userInput.substring(8, keywordByIndex).trim();
                    String deadline = userInput.substring(keywordByIndex + 3).trim();

                    outputArray.add(taskType);
                    outputArray.add(descriptionDeadline);
                    outputArray.add(deadline);
                } else {
                    // Missing keyword error
                    throw new HALException("Missing keyword /by!");
                }
                break;

            case "event":
                int keywordFromIndex = userInput.indexOf("/from");
                int keywordToIndex = userInput.indexOf("/to");

                if (keywordFromIndex != -1 && keywordToIndex != -1) {
                    String descriptionEvent = userInput.substring(5, keywordFromIndex).trim();
                    String deadlineFromEvent = userInput.substring(keywordFromIndex + 5, keywordToIndex).trim();
                    String deadlineToEvent = userInput.substring(keywordToIndex + 3).trim();

                    outputArray.add(taskType);
                    outputArray.add(descriptionEvent);
                    outputArray.add(deadlineFromEvent);
                    outputArray.add(deadlineToEvent);
                } else {
                    // Missing keyword error
                    throw new HALException("Missing keyword /from and /to!");
                }
                break;

            default:
                // Handle other cases if necessary
                break;
            }
        }
        return outputArray;
    }
}