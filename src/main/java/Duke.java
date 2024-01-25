import taskTypes.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String DELIMITER = "______________________________________";

        System.out.println(DELIMITER);
        System.out.println("Hello! I'm Drew");
        System.out.println("What can I do for you?");
        System.out.println(DELIMITER);

        String userInput = sc.nextLine();
        Task[] taskList = new Task[100];
        int listLength = 0;

        while (!userInput.equalsIgnoreCase("bye")){
            String reply = "";

            Command userCommand = Command.UNKNOWN;
            int inputLength = userInput.length();

            if(inputLength == 4 && userInput.substring(0, 4).equalsIgnoreCase("list")) {
                userCommand = Command.LIST;
            } else if (inputLength > 4 && userInput.substring(0, 4).equalsIgnoreCase("mark")) {
                userCommand = Command.MARK;
            } else if (inputLength > 6 && userInput.substring(0, 6).equalsIgnoreCase("unmark")) {
                userCommand = Command.UNMARK;
            } else if (inputLength > 4 && userInput.substring(0, 4).equalsIgnoreCase("todo")) {
                userCommand = Command.TODO;
            } else if (inputLength > 8 && userInput.substring(0, 8).equalsIgnoreCase("deadline")) {
                userCommand = Command.DEADLINE;
            } else if (inputLength > 5 && userInput.substring(0, 5).equalsIgnoreCase("event")) {
                userCommand = Command.EVENT;
            } else {
                userCommand = Command.ADD;
            }

            switch (userCommand) {
                case LIST: {
                    reply = reply + "Here are the tasks in your list:" + "\n";
                    for (int i = 0; i < listLength; i++) {
                        reply = reply + Integer.toString(i + 1) + ". " +
                                taskList[i].statusString() + "\n";
                    }
                    reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                    break;
                }
                case MARK: {
                    int taskIndex;
                    taskIndex = Integer.parseInt(userInput.substring(5));
                    taskList[taskIndex - 1].setDone();
                    reply = "Well done! I have marked this task as done:\n" +
                            taskList[taskIndex - 1].statusString() + "\n";
                    break;
                }
                case UNMARK: {
                    int taskIndex;
                    taskIndex = Integer.parseInt(userInput.substring(7));
                    taskList[taskIndex - 1].setNotDone();
                    reply = "Ok. I have marked this task as not done yet:\n" +
                            taskList[taskIndex - 1].statusString() + "\n";
                    break;
                }
                case TODO: {
                    String todoDescription = userInput.substring(5);
                    Todo newTask = new Todo(todoDescription);
                    taskList[listLength] = newTask;
                    reply = "Got it. I've added this task:\n";
                    reply = reply + newTask.statusString() + "\n";
                    listLength ++;
                    reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                    break;
                }
                case DEADLINE: {
                    int firstBackslashIndex = userInput.indexOf("/");
                    String deadlineDescription = userInput.substring(9, firstBackslashIndex);
                    String date = userInput.substring(firstBackslashIndex + 4);
                    Deadline newTask = new Deadline(deadlineDescription, date);
                    taskList[listLength] = newTask;
                    reply = "Got it. I've added this task:\n";
                    reply = reply + newTask.statusString() + "\n";
                    listLength ++;
                    reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                    break;
                }
                case EVENT: {
                    int firstBackslashIndex = userInput.indexOf("/");
                    int secondBackslashIndex = userInput.indexOf("/", firstBackslashIndex + 1);
                    String eventDescription = userInput.substring(6, firstBackslashIndex);
                    String startDate = userInput.substring(firstBackslashIndex + 6, secondBackslashIndex);
                    String endDate = userInput.substring(secondBackslashIndex + 4);
                    Event newTask = new Event(eventDescription, startDate, endDate);
                    taskList[listLength] = newTask;
                    reply = "Got it. I've added this task:\n";
                    reply = reply + newTask.statusString() + "\n";
                    listLength ++;
                    reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                    break;
                }
                case ADD:
                    taskList[listLength] = new Task(userInput);
                    reply = String.format("added: %s\n", userInput);
                    listLength ++;
                    break;
            }


            System.out.println(DELIMITER);
            System.out.print(reply);
            System.out.println(DELIMITER);

            userInput = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
