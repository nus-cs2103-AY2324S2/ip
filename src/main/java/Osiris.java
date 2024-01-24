import Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Osiris {

    public static final String NAME = "Osiris";

    private final TaskManager taskManager = new TaskManager();

    public void startChat(){
        Scanner scanner = new Scanner(System.in);
        boolean terminateChat = false;

        this.printSeparator();
        this.outputIntroductions();
        this.printSeparator();

        while (!terminateChat){

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
                    System.out.println("Sorry, but I am unable to comprehend the instruction at my current development stage. Please enter something else.");
                    break;
            }

        }

        this.printSeparator();
        this.outputGoodbyes();
        this.printSeparator();
    }

    public void outputIntroductions() {
        String introductions = String.format("Hello! I'm %s.\nWhat can I do for you?", Osiris.NAME);
        System.out.println(introductions);
    }

    public void outputGoodbyes() {
        String goodbyes = "Bye. Hope to see you again soon!";
        System.out.println(goodbyes);
    }

    private void echoUserInput(String input) {
        this.printSeparator();
        System.out.println("     " + input);
        this.printSeparator();
    }

    private void addUserTask(String userInput) {
        this.taskManager.addUserTask(userInput);

        this.printSeparator();
        System.out.println("     Added Task.Task: " + userInput);
        this.printSeparator();
    }

    private void addToDoTask(String taskName) {
        this.taskManager.addToDoTask(taskName);

        this.printSeparator();
        System.out.println("     Got it. I've added this task:");
        System.out.println("        " + this.taskManager.getTask(taskManager.getTotalTaskCount() - 1).toString());
        System.out.printf("     Now you have %d tasks in the list.%n", taskManager.getTotalTaskCount());
        this.printSeparator();
    }

    private void addDeadlineTask(String taskName, String deadline) {
        this.taskManager.addDeadlineTask(taskName,deadline);

        this.printSeparator();
        System.out.println("     Got it. I've added this task:");
        System.out.println("        " + this.taskManager.getTask(taskManager.getTotalTaskCount() - 1).toString());
        System.out.printf("     Now you have %d tasks in the list.%n", taskManager.getTotalTaskCount());
        this.printSeparator();
    }

    private void addEventTask(String taskName, String startDateTime, String endDateTime) {
        this.taskManager.addEventTask(taskName, startDateTime, endDateTime);

        this.printSeparator();
        System.out.println("     Got it. I've added this task:");
        System.out.println("        " + this.taskManager.getTask(taskManager.getTotalTaskCount() - 1).toString());
        System.out.printf("     Now you have %d tasks in the list.%n", taskManager.getTotalTaskCount());
        this.printSeparator();
    }

    private void markTaskCompleted(int index){
        boolean successful = this.taskManager.markTaskCompleted(index - 1);

        if (successful) {
            this.printSeparator();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("        " + this.taskManager.getTask(index - 1).toString());
            this.printSeparator();
        }
    }

    private void markTaskIncomplete(int index) {
        boolean successful = this.taskManager.markTaskIncomplete(index - 1);

        if (successful) {
            this.printSeparator();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("        " + this.taskManager.getTask(index - 1).toString());
            this.printSeparator();
        }
    }
    private void printUserTasks(){
        ArrayList<Task> toPrint = this.taskManager.getUserTasks();

        this.printSeparator();
        for (int i = 0; i < toPrint.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + toPrint.get(i).toString());
        }
        this.printSeparator();
    }

    private void printSeparator() {
        System.out.println("----------------------------------------");
    }
}
