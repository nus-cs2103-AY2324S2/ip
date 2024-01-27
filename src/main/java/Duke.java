import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String NAME = "Fatnom";
    private static final int lineLength = 60;

    public static String createLine() {
        String line = "";
        for (int i = 0; i < lineLength; i++) {
            line += "_";
        }
        return line;
    }

    public static void printLine() {
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void printMessage(String message) {
        Duke.printLine();
        System.out.println(message);
        Duke.printLine();
    }

    public static void printAddedTask(String taskMessage, int totalNumOfTasks) {
        String addedTaskMessage = "got it!! i've added this task:\n" +
                                  "   " + taskMessage + "\n" +
                                  "you now have " + totalNumOfTasks + " tasks in the task list!";
        Duke.printLine();
        System.out.println(addedTaskMessage);
        Duke.printLine();
    }

    public static void printDeletedTask(String taskMessage, int remainingNumOfTasks) {
        String deletedTaskMessage = "got it!! i've deleted this task:\n" +
                                    "   " + taskMessage + "\n" +
                                    "you now have " + remainingNumOfTasks + " tasks left in the task list!";
        Duke.printLine();
        System.out.println(deletedTaskMessage);
        Duke.printLine();
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            ArrayList<Task> taskList= new ArrayList<>();

            String welcomeMessage = "hello! i'm " + NAME + "!!!\n" +
                                    "i see you've adopted me! yay :3\n" +
                                    "what can i do for you?";
            Duke.printMessage(welcomeMessage);

            while (true) {
                String input = sc.nextLine();
                String[] inputTokens = input.split(" ");
                String command = inputTokens[0];

                //command: bye
                if (command.equalsIgnoreCase("bye")) {
                    Duke.printMessage("bye!! come visit me again! :D");
                    break;
                    //command: list
                } else if (command.equalsIgnoreCase("list")) {
                    String listMessage = "alright! here is your task list:\n";
                    if (taskList.size() == 0) {
                        listMessage += ".\n" +
                                       ".\n" +
                                       ".\n" +
                                       ".\n" +
                                       ".\n" +
                                       ".\n" +
                                       "SURPRISE!! nothing at all! what a good life ~ :3";
                    }
                    for(int i = 0; i < taskList.size(); i++) {
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
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "what!! task 0? how can i mark a task that doesn't exist?!\n" +
                                                  "are you playing with me... :p\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    } else if (taskNum > taskList.size()) {
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "hahaha! you only have " + taskList.size() + " tasks in your task list!!\n" +
                                                  "there's no task " + taskNum + "~ try again :3\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    taskList.get(taskNum - 1).complete();
                    String markedMessage = "good job!!! i've marked this task as done:\n" +
                                           "   " + taskList.get(taskNum - 1).printTask();
                    Duke.printMessage(markedMessage);
                    //command: unmark
                } else if (command.equalsIgnoreCase("unmark")) {
                    int taskNum = Integer.parseInt(inputTokens[1]);
                    if (taskNum == 0) {
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "what!! task 0? how can i unmark a task that doesn't exist?!\n" +
                                                  "are you playing with me... :p\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    } else if (taskNum > taskList.size()) {
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "hahaha! you only have " + taskList.size() + " tasks in your task list!!\n" +
                                                  "there's no task " + taskNum + " ~ try again :3\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    taskList.get(taskNum - 1).unmark();
                    String unmarkedMessage = "okay! i've unmarked this task:\n" +
                                             "   " + taskList.get(taskNum - 1).printTask();
                    Duke.printMessage(unmarkedMessage);
                    //command: delete
                } else if (command.equalsIgnoreCase("delete")) {
                    int taskNum = Integer.parseInt(inputTokens[1]);
                    if (taskNum == 0) {
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "beepbeep!!! there's no task 0!!!\n" +
                                                  "hmf >:P\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    } else if (taskNum > taskList.size()) {
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "hold up!! you only have " + taskList.size() + " tasks in your task list!!\n" +
                                                  "there's no task " + taskNum + " ~ try again :3\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    Task deletedTask = taskList.get(taskNum - 1);
                    String deletedTaskMessage = deletedTask.printTask();
                    taskList.remove(taskNum - 1);
                    int remainingNumOfTasks = taskList.size();
                    Duke.printDeletedTask(deletedTaskMessage, remainingNumOfTasks);
                    //command: todo
                } else if (command.equalsIgnoreCase("todo")) {
                    int len = inputTokens.length;
                    if (len == 1) {
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "error!! you didn't specify what you want todo!\n" +
                                                  "use this format instead: todo taskname :p\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    String todoName = "";
                    for(int i = 1; i < len; i++) {
                        todoName += " " + inputTokens[i];
                    }
                    ToDo addedTask = new ToDo(todoName);
                    taskList.add(addedTask);
                    int totalNumOfTasks = taskList.size();
                    Duke.printAddedTask(addedTask.printTask(), totalNumOfTasks);
                    //command: deadline
                } else if (command.equalsIgnoreCase("deadline")) {
                    int len = inputTokens.length;
                    if (len == 1) {
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "error!! you didn't specify the deadline task!\n" +
                                                  "use this format instead: deadline taskname /by deadline :p\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    String deadlineName = "";
                    String deadline = "";
                    for(int i = 1; i < len; i++) {
                        if (inputTokens[i].equals("/by")) {
                            deadline += inputTokens[i + 1];
                            int j = i + 2;
                            while (j < len) {
                                deadline += " " + inputTokens[j];
                                j++;
                            }
                            break;
                        } else {
                            deadlineName += " " + inputTokens[i];
                        }
                    }
                    Deadline addedTask = new Deadline(deadlineName, deadline);
                    taskList.add(addedTask);
                    int totalNumOfTasks = taskList.size();
                    Duke.printAddedTask(addedTask.printTask(), totalNumOfTasks);
                    //command: event
                } else if (command.equalsIgnoreCase("event")) {
                    int len = inputTokens.length;
                    if (len == 1) {
                        String exceptionMessage = Duke.createLine() + "\n" +
                                                  "error!! you didn't specify the event!\n" +
                                                  "use this format instead: event taskname /from startdate /to enddate :p\n" +
                                                  Duke.createLine();
                        throw new DukeException(exceptionMessage);
                    }
                    String eventName = "";
                    String start = "";
                    String end = "";
                    for (int i = 1; i < len; i++) {
                        if (inputTokens[i].equals("/from")) {
                            start += inputTokens[i + 1];
                            int j = i + 2;
                            int k = 0;
                            while (j < len) {
                                if (inputTokens[j].equals("/to")) {
                                    end += inputTokens[j + 1];
                                    k = j + 2;
                                    break;
                                } else {
                                    start += " " + inputTokens[j];
                                    j++;
                                }
                            }
                            while (k < len) {
                                end += " " + inputTokens[k];
                                k++;
                            }
                            break;
                        } else {
                            eventName += " " + inputTokens[i];
                        }
                    }
                    Event addedTask = new Event(eventName, start, end);
                    taskList.add(addedTask);
                    int totalNumOfTasks = taskList.size();
                    Duke.printAddedTask(addedTask.printTask(), totalNumOfTasks);
                } else {
                    String exceptionMessage = Duke.createLine() + "\n" +
                                              "hm? i don't understand what that means :(\n" +
                                              "you can try any of these commands instead!!\n" +
                                              "list\n" +
                                              "mark\n" +
                                              "unmark\n" +
                                              "delete\n" +
                                              "todo\n" +
                                              "deadline\n" +
                                              "event\n" +
                                              "bye\n" +
                                              Duke.createLine();
                    throw new DukeException(exceptionMessage);
                }
            }
            sc.close();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}

