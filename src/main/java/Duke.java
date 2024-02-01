import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Encapsulates the data and behaviour of the chatbot.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Duke {
    private static final String NAME = "Fatnom";
    private static Storage storage;
    private static final int LINE_LENGTH = 60;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Creates a line, in the form of a String.
     *
     * @return The line.
     */
    public static String createLine() {
        String line = "";
        for (int i = 0; i < LINE_LENGTH; i++) {
            line += "_";
        }
        return line;
    }

    /**
     * Prints a line for UI.
     */
    public static void printLine() {
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    /**
     * Prints a message bound by 2 lines for UI.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        Duke.printLine();
        System.out.println(message);
        Duke.printLine();
    }

    /**
     * Prints the message for when a task has successfully been added.
     *
     * @param taskMessage The message to be printed.
     * @param totalNumOfTasks The total number of tasks in the task list after the new task has been added.
     */
    public static void printAddedTask(String taskMessage, int totalNumOfTasks) {
        String addedTaskMessage = "got it!! i've added this task:\n"
                + "   " + taskMessage + "\n"
                + "you now have " + totalNumOfTasks + " tasks in the task list!";
        Duke.printLine();
        System.out.println(addedTaskMessage);
        Duke.printLine();
    }

    /**
     * Prints the message for when a task has successfully been deleted.
     *
     * @param taskMessage The message to be printed.
     * @param remainingNumOfTasks The remaining number of tasks in the task list after the task has been deleted.
     */
    public static void printDeletedTask(String taskMessage, int remainingNumOfTasks) {
        String deletedTaskMessage = "got it!! i've deleted this task:\n"
                + "   " + taskMessage + "\n"
                + "you now have " + remainingNumOfTasks + " tasks left in the task list!";
        Duke.printLine();
        System.out.println(deletedTaskMessage);
        Duke.printLine();
    }

    /**
     * Displays a welcome message and loads the save file (if any).
     * Then, executes the commands inputted by the user.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            ArrayList<Task> taskList= new ArrayList<>();

            String welcomeMessage = "hello! i'm " + NAME + "!!!\n"
                    + "i'm here to manage your tasklist!\n"
                    + "what can i do for you?";
            Duke.printMessage(welcomeMessage);
            try {
                Duke.storage = new Storage();
                taskList = Storage.loadData();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            while (true) {
                String input = sc.nextLine();
                String[] inputTokens = input.split(" ");
                String[] deadlineFormatChecker = input.split("/by");
                String[] eventFormatChecker = input.split("/from");
                String command = inputTokens[0];

                //command: bye
                if (command.equalsIgnoreCase("bye")) {
                    Duke.printMessage("bye!! come visit me again! :D");
                    break;
                //command: list
                } else if (command.equalsIgnoreCase("list")) {
                    String listMessage = "alright! here is your task list:\n";
                    if (taskList.size() == 0) {
                        listMessage += ".\n"
                                + ".\n"
                                + ".\n"
                                + ".\n"
                                + ".\n"
                                + ".\n"
                                + "SURPRISE!! nothing at all! what a good life!";
                    }
                    for (int i = 0; i < taskList.size(); i++) {
                        int index = i + 1;
                        String taskMessage = taskList.get(i).printTask();
                        if (i == taskList.size() - 1) {
                            listMessage += index + ". " + taskMessage;
                        } else {
                            listMessage += index + ". " + taskMessage + "\n";
                        }
                    }
                    Duke.printMessage(listMessage);
                //command: mark
                } else if (command.equalsIgnoreCase("mark")) {
                    int taskNum = Integer.parseInt(inputTokens[1]);
                    if (taskNum == 0) {
                        String exceptionMessage = Duke.createLine() + "\n"
                                + "task 0? how can i mark a task that doesn't exist?!\n"
                                + Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    } else if (taskNum > taskList.size()) {
                        String exceptionMessage = Duke.createLine() + "\n"
                                + "hahaha! you only have " + taskList.size() + " tasks in your task list!!\n"
                                + "there's no task " + taskNum + "!\n"
                                + Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    taskList.get(taskNum - 1).complete();
                    String markedMessage = "good job!!! i've marked this task as done:\n"
                            + "   " + taskList.get(taskNum - 1).printTask();
                    Duke.printMessage(markedMessage);
                    Storage.saveData(taskList);
                //command: unmark
                } else if (command.equalsIgnoreCase("unmark")) {
                    int taskNum = Integer.parseInt(inputTokens[1]);
                    if (taskNum == 0) {
                        String exceptionMessage = Duke.createLine() + "\n"
                                + "task 0? how can i unmark a task that doesn't exist?!\n"
                                + Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    } else if (taskNum > taskList.size()) {
                        String exceptionMessage = Duke.createLine() + "\n"
                                + "hahaha! you only have " + taskList.size() + " tasks in your task list!!\n"
                                + "there's no task " + taskNum + "!\n"
                                + Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    taskList.get(taskNum - 1).unmark();
                    String unmarkedMessage = "okay! i've unmarked this task:\n"
                            + "   " + taskList.get(taskNum - 1).printTask();
                    Duke.printMessage(unmarkedMessage);
                    Storage.saveData(taskList);
                //command: delete
                } else if (command.equalsIgnoreCase("delete")) {
                    int taskNum = Integer.parseInt(inputTokens[1]);
                    if (taskNum == 0) {
                        String exceptionMessage = Duke.createLine() + "\n"
                                + "error: there's no such thing as task 0!\n"
                                + Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    } else if (taskNum > taskList.size()) {
                        String exceptionMessage = Duke.createLine() + "\n"
                                + "error! you only have " + taskList.size() + " tasks in your task list!!\n"
                                + "there's no task " + taskNum + "!\n"
                                + Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    Task deletedTask = taskList.get(taskNum - 1);
                    String deletedTaskMessage = deletedTask.printTask();
                    taskList.remove(taskNum - 1);
                    int remainingNumOfTasks = taskList.size();
                    Duke.printDeletedTask(deletedTaskMessage, remainingNumOfTasks);
                    Storage.saveData(taskList);
                //command: todo
                } else if (command.equalsIgnoreCase("todo")) {
                    int len = inputTokens.length;
                    if (len == 1) {
                        String exceptionMessage = Duke.createLine() + "\n"
                                + "you didn't specify what you want to do! use this format instead:\n"
                                + "todo [task description]\n"
                                + Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    String todoName = "";
                    for (int i = 1; i < len; i++) {
                        todoName += " " + inputTokens[i];
                    }
                    ToDo addedTask = new ToDo(todoName);
                    taskList.add(addedTask);
                    int totalNumOfTasks = taskList.size();
                    Duke.printAddedTask(addedTask.printTask(), totalNumOfTasks);
                    Storage.saveData(taskList);
                //command: deadline
                } else if (command.equalsIgnoreCase("deadline")) {
                    if (deadlineFormatChecker.length != 2) {
                        String exceptionMessage = Duke.createLine() + "\n"
                                + "error! please specify the deadline task in this format:\n"
                                + "deadline [task description] /by [YYYY-MM-DD HH:MM]\n"
                                + Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    int len = inputTokens.length;
                    String deadlineName = "";
                    LocalDateTime deadline = LocalDateTime.of(2023, 12, 5, 16, 0);
                    for (int i = 1; i < len; i++) {
                        if (inputTokens[i].equals("/by")) {
                            String dateTimeString = inputTokens[i + 1] + " " + inputTokens[i + 2];
                            deadline = LocalDateTime.parse(dateTimeString, formatter);
                            break;
                        } else {
                            deadlineName += " " + inputTokens[i];
                        }
                    }
                    Deadline addedTask = new Deadline(deadlineName, deadline);
                    taskList.add(addedTask);
                    int totalNumOfTasks = taskList.size();
                    Duke.printAddedTask(addedTask.printTask(), totalNumOfTasks);
                    Storage.saveData(taskList);
                //command: event
                } else if (command.equalsIgnoreCase("event")) {
                    String exceptionMessage = Duke.createLine() + "\n"
                            + "error! please specify the event task in this format:\n"
                            + "event [task description] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]\n"
                            + Duke.createLine();
                    if (eventFormatChecker.length != 2) {
                        throw new DukeException(exceptionMessage);
                    } else if (eventFormatChecker[1].split("to").length != 2) {
                        throw new DukeException(exceptionMessage);
                    }
                    int len = inputTokens.length;
                    String eventName = "";
                    LocalDateTime start = LocalDateTime.of(2023, 12, 5, 16, 0);
                    LocalDateTime end = LocalDateTime.of(2023, 12, 5, 16, 0);
                    for (int i = 1; i < len; i++) {
                        if (inputTokens[i].equals("/from")) {
                            String startString = inputTokens[i + 1] + " " + inputTokens[i + 2];
                            String endString = inputTokens[i + 4] + " " + inputTokens[i + 5];
                            start = LocalDateTime.parse(startString, formatter);
                            end = LocalDateTime.parse(endString, formatter);
                            break;
                        } else {
                            eventName += " " + inputTokens[i];
                        }
                    }
                    Event addedTask = new Event(eventName, start, end);
                    taskList.add(addedTask);
                    int totalNumOfTasks = taskList.size();
                    Duke.printAddedTask(addedTask.printTask(), totalNumOfTasks);
                    Storage.saveData(taskList);
                } else {
                    String exceptionMessage = Duke.createLine() + "\n"
                            + "hm? i don't understand what that means :(\n"
                            + "you can try any of these commands instead!!\n"
                            + "list\n"
                            + "mark\n"
                            + "unmark\n"
                            + "delete\n"
                            + "todo\n"
                            + "deadline\n"
                            + "event\n"
                            + "bye\n"
                            + Duke.createLine();
                    throw new DukeException(exceptionMessage);
                }
            }
            sc.close();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            System.out.println("invalid date time format! please use YYYY-MM-DD HH:MM format!");
        }
    }
}

