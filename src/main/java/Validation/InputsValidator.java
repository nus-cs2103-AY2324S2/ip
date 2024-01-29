package Validation;

import Commands.addDeadlineTaskCommand;
import Commands.addEventTaskCommand;
import Commands.addToDoTaskCommand;

public class InputsValidator {

    private static InputsValidator instance;

    private InputsValidator() {}

    public static InputsValidator getInstance() {
        if (instance == null) {
            instance = new InputsValidator();
        }
        return instance;
    }

    public boolean validateMarkTaskCompletedInput(String userInput) {
        String[] inputtedWords = userInput.split(" ");
        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.out.println("Invalid task index. Please Reenter");
        }
        return false;
    }

    public boolean validateMarkTaskIncompleteInput(String userInput) {
        String[] inputtedWords = userInput.split(" ");
        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.out.println("Invalid task index. Please Reenter");
        }
        return false;
    }

    public boolean validateRemoveTaskInput(String userInput) {
        String[] inputtedWords = userInput.split(" ");
        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.out.println("Invalid task index. Please Reenter");
        }
        return false;
    }

    public boolean validateAddToDoTaskInput(String userInput){
        String taskName = userInput.substring(addToDoTaskCommand.COMMAND.length()).trim();
        if (!taskName.isEmpty()) {
            return true;
        } else {
            System.out.println("Task name not provided. Please Reenter.");
        }
        return false;
    }

    public boolean validateAddDeadlineTaskInput(String userInput){
        int byIndex = userInput.indexOf("/by");

        if (byIndex != -1) {
            String taskName = userInput.substring(addDeadlineTaskCommand.COMMAND.length(), byIndex - 1).trim();

            if (!taskName.isEmpty()) {
                String deadline = userInput.substring(byIndex + "/by".length()).trim();
                return true;
            } else {
                System.out.println("Task name not provided. Please Reenter.");
            }
        } else {
            System.out.println("Invalid input format. Please Reenter. Ensure '/by' is specified for a Deadline Task. E.g. deadline Do Homework /by Sunday.");
        }
        return false;
    }

    public boolean validateAddEventTaskInput(String userInput) {
        int fromIndex = userInput.indexOf("/from");
        int toIndex = userInput.indexOf("/to");

        if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex) {
            String taskName = userInput.substring(addEventTaskCommand.COMMAND.length(), fromIndex - 1).trim();
            if (!taskName.isEmpty()) {
                String startDateTime = userInput.substring(fromIndex + "/from".length(), toIndex - 1).trim();
                String endDateTime = userInput.substring(toIndex + "/to".length()).trim();
                return true;
            } else {
                System.out.println("Task name not provided. Please Reenter.");
            }
        } else {
            System.out.println("Invalid input format. Please Reenter. Ensure '/from' & '/to' is specified for a Event Task. E.g. event School Meeting /from Mon 2pm /to 4pm. Please Reenter." );
        }
        return false;
    }

}
