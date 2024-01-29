package Interpreters;

import Commands.*;

public class UserInputInterpreter {

    public Command interpretUserInput(String userInput) {
        String[] inputtedWords = userInput.split(" ");
        String taskName;

        switch (inputtedWords[0]) {
            case terminateChatCommand.COMMAND:
                return new terminateChatCommand();
            case printUserTasksCommand.COMMAND:
                return new printUserTasksCommand();
            case markTaskCompletedCommand.COMMAND:
                if (inputtedWords.length == 2) {
                    String taskIndexString = inputtedWords[1];
                    if (taskIndexString.matches("\\d+")) {
                        int taskIndex = Integer.parseInt(taskIndexString);
                        return new markTaskCompletedCommand(taskIndex);
                    } else {
                        System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                    }
                } else {
                    System.out.println("Invalid task index. Please Reenter");
                }
                break;
            case markTaskIncompleteCommand.COMMAND:
                if (inputtedWords.length == 2) {
                    String taskIndexString = inputtedWords[1];
                    if (taskIndexString.matches("\\d+")) {
                        int taskIndex = Integer.parseInt(taskIndexString);
                        return new markTaskIncompleteCommand(taskIndex);
                    } else {
                        System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                    }
                } else {
                    System.out.println("Invalid task index. Please Reenter");
                }
                break;
            case removeTaskCommand.COMMAND:
                if (inputtedWords.length == 2) {
                    String taskIndexString = inputtedWords[1];
                    if (taskIndexString.matches("\\d+")) {
                        int taskIndex = Integer.parseInt(taskIndexString);
                        return new removeTaskCommand(taskIndex);
                    } else {
                        System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                    }
                } else {
                    System.out.println("Invalid task index. Please Reenter");
                }
                break;
            case addToDoTaskCommand.COMMAND:
                taskName = userInput.substring("todo".length()).trim();

                if (!taskName.isEmpty()) {
                    return new addToDoTaskCommand(taskName);
                } else {
                    System.out.println("Task name not provided. Please Reenter.");
                }
                break;
            case addDeadlineTaskCommand.COMMAND:
                int byIndex = userInput.indexOf("/by");

                if (byIndex != -1) {
                    taskName = userInput.substring("deadline".length(), byIndex - 1).trim();

                    if (!taskName.isEmpty()) {
                        String deadline = userInput.substring(byIndex + "/by".length()).trim();
                        return new addDeadlineTaskCommand(taskName, userInput);
                    } else {
                        System.out.println("Task name not provided. Please Reenter.");
                    }
                } else {
                    System.out.println("Invalid input format. Please Reenter. Ensure '/by' is specified for a Deadline Task. E.g. deadline Do Homework /by Sunday.");
                }
                break;
            case addEventTaskCommand.COMMAND:
                int fromIndex = userInput.indexOf("/from");
                int toIndex = userInput.indexOf("/to");

                if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex) {
                    taskName = userInput.substring("event".length(), fromIndex - 1).trim();
                    if (!taskName.isEmpty()) {
                        String startDateTime = userInput.substring(fromIndex + "/from".length(), toIndex - 1).trim();
                        String endDateTime = userInput.substring(toIndex + "/to".length()).trim();
                        return new addEventTaskCommand(taskName, startDateTime, endDateTime);
                    } else {
                        System.out.println("Task name not provided. Please Reenter.");
                    }
                } else {
                    System.out.println("Invalid input format. Please Reenter. Ensure '/from' & '/to' is specified for a Event Task. E.g. event School Meeting /from Mon 2pm /to 4pm. Please Reenter." );
                }
                break;
            default:
                return new UnsupportedCommand();
        }
        return new UnsupportedCommand();
    }
}
