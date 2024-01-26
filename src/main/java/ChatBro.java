import java.util.Scanner;

public class ChatBro {
    public static void main(String[] args) {
        System.out.println("_________________________\n" +
                " __  __       __\n" +
                " \\ \\/ /__    / /\n" +
                "  \\  / _ \\  /_/ \n" +
                "  /_/\\___/ (_)\n\n" +
                "I'm ChatBro!\n" +
                "What can I do for you bro?\n" +
                "Use the available commands: list, bye, mark, unmark, OR\n" +
                "create a new task (todo, deadline, event) to store in your list bro.\n" +
                "_________________________\n");

        Scanner sc = new Scanner(System.in);
        boolean isQuit = false;
        Task[] taskList = new Task[101]; // First element left empty for 1-based indexing
        int taskCount = 0;

        while (!isQuit) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list bro:\n" +
                            "_________________________");
                    for (int i = 1; i <= 100; i++) {
                        if (taskList[i] == null) {
                            break;
                        }
                        System.out.println(i + "." + taskList[i].toString());
                    }
                    System.out.println("_________________________\n");
                    break;

                case "todo":
                    String[] todoSplit = input.split("todo ");
                    for (int i = 1; i <= 100; i++) {
                        if (taskList[i] == null) {
                            taskList[i] = new ToDo(todoSplit[1]);
                            taskCount++;
                            System.out.println("_________________________\n" +
                                    "Ok bro, I've added: \n" + taskList[i].toString() + "\n into your list.\n" +
                                    "You've got " + taskCount + " task(s) now.\n" +
                                    "_________________________\n");
                            break;
                        }
                    }
                    break;

                case "deadline":
                    String[] deadlineSplit = input.split(" /by ");
                    String deadlineName = deadlineSplit[0].substring(9); // 9 is the length of "deadline "
                    for (int i = 1; i <= 100; i++) {
                        if (taskList[i] == null) {
                            taskList[i] = new Deadline(deadlineName, deadlineSplit[1]);
                            taskCount++;
                            System.out.println("_________________________\n" +
                                    "Ok bro, I've added: \n" + taskList[i].toString() + "\n into your list.\n" +
                                    "You've got " + taskCount + " task(s) now.\n" +
                                    "_________________________\n");
                            break;
                        }
                    }
                    break;

                case "event":
                    String[] eventFromSplit = input.split(" /from ");
                    String eventName = eventFromSplit[0].substring(6); // 6 is the length of "event "
                    String[] eventToSplit = eventFromSplit[1].split(" /to "); // ETS[0] is from, ETS[1] is to

                    for (int i = 1; i <= 100; i++) {
                        if (taskList[i] == null) {
                            taskList[i] = new Event(eventName, eventToSplit[0], eventToSplit[1]);
                            taskCount++;
                            System.out.println("_________________________\n" +
                                    "Ok bro, I've added: \n" + taskList[i].toString() + "\n into your list.\n" +
                                    "You've got " + taskCount + " task(s) now.\n" +
                                    "_________________________\n");
                            break;
                        }
                    }
                    break;

                case "mark":
                    int taskNum = Integer.parseInt(inputSplit[1]);
                    if (taskList[taskNum] == null) {
                        System.out.println("_________________________\n" +
                                "Sorry bro, this task doesn't exist in your list.\n" +
                                "_________________________\n");
                        break;
                    }
                    taskList[taskNum].markAsDone();
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum + " is marked as done:\n" +
                            taskList[taskNum].toString() +
                            "\n_________________________\n");
                    break;

                case "unmark":
                    int taskNum2 = Integer.parseInt(inputSplit[1]);
                    if (taskList[taskNum2] == null) {
                        System.out.println("_________________________\n" +
                                "Sorry bro, this task doesn't exist in your list.\n" +
                                "_________________________\n");
                        break;
                    }
                    taskList[taskNum2].markAsUndone();
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum2 + " is marked as not done yet:\n" +
                            taskList[taskNum2].toString() +
                            "\n_________________________\n");
                    break;

                case "bye":
                    isQuit = true;
                    System.out.println("_________________________\n" +
                            "Hasta la vista, bro!\n" +
                            "_________________________\n");
                    break;

                default:
                    System.out.println("_________________________\n" +
                            "Sorry bro, I don't understand what you mean.\n" +
                            "_________________________\n");
                    break;
            }
        }
        sc.close();
    }
}
