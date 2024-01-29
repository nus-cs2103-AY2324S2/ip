import Task.Task;
import Task.TaskManager;
import UI.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Osiris {

    public static final String NAME = "Osiris";

    private final TaskManager taskManager = new TaskManager();

    private final Ui userInterface = new Ui();

    public void startChat(){
        Scanner scanner = new Scanner(System.in);
        this.taskManager.initialise();

        this.userInterface.outputIntroductions();

        boolean terminateChat = false;

        while (!terminateChat){

            this.userInterface.messageOsirisPrompt();
            String userInput = scanner.nextLine();
            String[] inputtedWords = userInput.split(" ");
            String taskName;

            switch (inputtedWords[0]) {
                case "bye":
                    terminateChat = true;
                    break;
                case "list":
                    this.printUserTasks();
                    break;
                case "mark":
                    if (inputtedWords.length == 2) {
                        String taskIndexString = inputtedWords[1];
                        if (taskIndexString.matches("\\d+")) {
                            int taskIndex = Integer.parseInt(taskIndexString);
                            this.markTaskCompleted(taskIndex);
                        } else {
                            System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                        }
                    } else {
                        System.out.println("Invalid task index. Please Reenter");
                    }
                    break;
                case "unmark":
                    if (inputtedWords.length == 2) {
                        String taskIndexString = inputtedWords[1];
                        if (taskIndexString.matches("\\d+")) {
                            int taskIndex = Integer.parseInt(taskIndexString);
                            this.markTaskIncomplete(taskIndex);
                        } else {
                            System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                        }
                    } else {
                        System.out.println("Invalid task index. Please Reenter");
                    }
                    break;
                case "delete":
                    if (inputtedWords.length == 2) {
                        String taskIndexString = inputtedWords[1];
                        if (taskIndexString.matches("\\d+")) {
                            int taskIndex = Integer.parseInt(taskIndexString);
                            this.removeTask(taskIndex);
                        } else {
                            System.out.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                        }
                    } else {
                        System.out.println("Invalid task index. Please Reenter");
                    }
                    break;
                case "todo":
                    taskName = userInput.substring("todo".length()).trim();

                    if (!taskName.isEmpty()) {
                        this.addToDoTask(taskName);
                    } else {
                        System.out.println("Task name not provided. Please Reenter.");
                    }
                    break;
                case "deadline":
                    int byIndex = userInput.indexOf("/by");

                    if (byIndex != -1) {
                        taskName = userInput.substring("deadline".length(), byIndex - 1).trim();

                        if (!taskName.isEmpty()) {
                            String deadline = userInput.substring(byIndex + "/by".length()).trim();
                            this.addDeadlineTask(taskName, deadline);
                        } else {
                            System.out.println("Task name not provided. Please Reenter.");
                        }
                    } else {
                        System.out.println("Invalid input format. Please Reenter. Ensure '/by' is specified for a Deadline Task. E.g. deadline Do Homework /by Sunday.");
                    }
                    break;
                case "event":
                    int fromIndex = userInput.indexOf("/from");
                    int toIndex = userInput.indexOf("/to");

                    if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex) {
                        taskName = userInput.substring("event".length(), fromIndex - 1).trim();
                        if (!taskName.isEmpty()) {
                            String startDateTime = userInput.substring(fromIndex + "/from".length(), toIndex - 1).trim();
                            String endDateTime = userInput.substring(toIndex + "/to".length()).trim();
                            this.addEventTask(taskName, startDateTime, endDateTime);
                        } else {
                            System.out.println("Task name not provided. Please Reenter.");
                        }
                    } else {
                        System.out.println("Invalid input format. Please Reenter. Ensure '/from' & '/to' is specified for a Event Task. E.g. event School Meeting /from Mon 2pm /to 4pm. Please Reenter." );
                    }
                    break;
                default:
                    this.userInterface.unsupportedCommandsOutput();
                    break;
            }

        }

        this.taskManager.termintate();

        this.userInterface.outputGoodbyes();
    }

    private void addToDoTask(String taskName) {
        boolean isSuccess = this.taskManager.addToDoTask(taskName, false);

        if (isSuccess) {
            this.userInterface.addToDoTaskSuccessNotification(this.taskManager.getTask(
                    this.taskManager.getTotalTaskCount() - 1).toString(), this.taskManager.getTotalTaskCount());
        }
    }

    private void addDeadlineTask(String taskName, String deadline) {
        boolean isSuccess = this.taskManager.addDeadlineTask(taskName,deadline, false);

        if (isSuccess) {
            this.userInterface.addDeadlineTaskSuccessNotification(this.taskManager.getTask(
                    this.taskManager.getTotalTaskCount() - 1).toString(), this.taskManager.getTotalTaskCount());
        }
    }

    private void addEventTask(String taskName, String startDateTime, String endDateTime) {
        boolean isSuccess = this.taskManager.addEventTask(taskName, startDateTime, endDateTime, false);

        if (isSuccess) {
            this.userInterface.addEventTaskSuccessNotification(this.taskManager.getTask(
                    this.taskManager.getTotalTaskCount() - 1).toString(), this.taskManager.getTotalTaskCount());
        }
    }

    private void markTaskCompleted(int index){
        boolean isSuccess = this.taskManager.markTaskCompleted(index - 1);

        if (isSuccess) {
            this.userInterface.markTaskCompletedSuccessNotification(this.taskManager.getTask(index - 1).toString());
        }
    }

    private void markTaskIncomplete(int index) {
        boolean isSuccess = this.taskManager.markTaskIncomplete(index - 1);

        if (isSuccess) {
            this.userInterface.markTaskIncompleteSuccessNotification(this.taskManager.getTask(index - 1).toString());
        }
    }

    private void removeTask(int index) {
        Task removedTask = this.taskManager.removeTask(index - 1);

        if (removedTask != null) {
            this.userInterface.removeTaskSuccessNotification(removedTask.toString(),this.taskManager.getTotalTaskCount());
        }
    }
    private void printUserTasks(){
        ArrayList<Task> taskList = this.taskManager.getUserTasks();
        ArrayList<String> taskDetailsArrayList = taskList.stream().map(task -> task.toString()).collect(Collectors.toCollection(ArrayList::new));
        this.userInterface.printUserTasks(taskDetailsArrayList);
    }

    private void printSeparator() {
        System.out.println("----------------------------------------");
    }

    // To be added to seperate class.

    public static LocalDate dateFormatter(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate date = LocalDate.parse(dateStr, dateTimeFormatter);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: '" + dateStr);
            System.out.println("Please try /by dd-mm-yyyy for a deadline tasks.");
            return null;
        }
    }

    public static LocalDateTime dateTimeFormatter(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the time range.");
            System.out.println("Please provide date time range 'dd-MM-yyyy HHmm' format.");
            return null;
        }
    }

    public LocalDateTime[] timeRangeFormatter(String fromDateTimeStr, String toTimeStr) {
        DateTimeFormatter startDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        DateTimeFormatter endDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            LocalDateTime startDateTime = LocalDateTime.parse(fromDateTimeStr, startDateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(toTimeStr, endDateTimeFormatter);
            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date time range.");
            System.out.println("Please provide date time range in 'dd-MM-yyyy HHmm' format.");
            return null;
        }
    }
}
