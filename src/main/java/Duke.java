import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String NAME = "Fatnom";
    private static final int lineLength = 60;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList= new ArrayList<>();

        String welcomeMessage = "hello! i'm " + NAME + "!!!\n" +
                                "i see you've adopted me! yay!\n" +
                                "what can i do for you?";
        Duke.printMessage(welcomeMessage);

        while (true) {
            String input = sc.nextLine();
            String[] inputTokens = input.split(" ");
            String command = inputTokens[0];

            //command: bye
            if (command.equalsIgnoreCase("bye")) {
                Duke.printMessage("bye! come visit me again!");
                break;
            //command: list
            } else if (command.equalsIgnoreCase("list")) {
                String listMessage = "alright! here is your task list:\n";
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
                taskList.get(taskNum - 1).complete();
                String markedMessage = "good job!!! i've marked this task as done:\n" +
                                        "   " + taskList.get(taskNum - 1).printTask();
                Duke.printMessage(markedMessage);
            //command: unmark
            } else if (command.equalsIgnoreCase("unmark")) {
                int taskNum = Integer.parseInt(inputTokens[1]);
                taskList.get(taskNum - 1).unmark();
                String unmarkedMessage = "okay! i've unmarked this task:\n" +
                        "   " + taskList.get(taskNum - 1).printTask();
                Duke.printMessage(unmarkedMessage);
            //command: todo
            } else if (command.equalsIgnoreCase("todo")) {
                int len = inputTokens.length;
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
            }
        }
        sc.close();
    }
}

