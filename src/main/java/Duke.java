import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> validCommands = new ArrayList<>(List.of("todo", "deadline", "event"));
    public static void echoText(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("      %s\n", text);
        System.out.println("    ____________________________________________________________");
    }

    public static void addList(Task task, ArrayList<Task> list) {
        list.add(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("      Got it! I added:");
        System.out.printf("        %s\n", task.toString());
        System.out.printf("      You now have %d tasks in your list :D\n", list.size());
        System.out.println("    ____________________________________________________________");
    }

    public static void printList(ArrayList<Task> list) {
        int count = 1;
        System.out.println("    ____________________________________________________________");
        if (list.size() == 0) {
            System.out.println("      Nothing added to list yet!");
        }
        for (Task task : list) {
            System.out.printf("      %d. %s\n", count, task.toString());
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void markTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
        if (split.length == 1) {
            throw new InvalidCommandException("Wrong format! Please include the number that you want me to mark >:(");
        } else {
            int number = Integer.parseInt(split[1]);
            if (number < 1 || number > list.size()) {
                throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
            } else {
                Task task = list.get(number - 1);
                task.markDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("      Yippee! I have marked this task as done ;D");
                System.out.printf("        %s\n", task.toString());
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    private static void unmarkTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
        if (split.length == 1) {
            throw new InvalidCommandException("Wrong format! Please include the number that you want me to unmark >:(");
        } else {
            int number = Integer.parseInt(split[1]);
            if (number < 1 || number > list.size()) {
                throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
            } else {
                Task task = list.get(number - 1);
                task.markNotDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("      Awww...I have marked this task as not done yet :(");
                System.out.printf("        %s\n", task.toString());
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    private static void deleteTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
        if (split.length == 1) {
            throw new InvalidCommandException("Wrong format! Please include the number that you want me to delete >:(");
        } else {
            int number = Integer.parseInt(split[1]);
            if (number < 1 || number > list.size()) {
                throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
            } else {
                Task task = list.get(number - 1);
                list.remove(number - 1);
                System.out.println("    ____________________________________________________________");
                System.out.println("      Successfully removed task!");
                System.out.printf("        %s\n", task.toString());
                System.out.printf("      You have %d tasks left in the list :D\n", list.size());
                System.out.println("    ____________________________________________________________");
            }
        }
    }
    private static void createTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
        Task newTask;
        if (!validCommands.contains(split[0].toLowerCase())) {
            throw new InvalidCommandException("I don't quite understand that command :'( Sorry...");
        }
        if (split.length == 1) {
            throw new InvalidCommandException("You need to tell me the task name >:0");
        }
        if (split[0].toLowerCase().equals("todo")) {
            newTask = new ToDo(split[1].trim());
        } else if (split[0].toLowerCase().equals("deadline")) {
            String[] deadlineSplit = split[1].trim().split("/by");
            if (deadlineSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/by' to indicate the deadline!");
            }
            newTask = new Deadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
        } else {
            String[] fromSplit = split[1].split("/from");
            if (fromSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/from' to indicate event start time!"
                );
            }
            String eventName = fromSplit[0].trim();

            String[] toSplit = fromSplit[1].split("/to");
            if (toSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/to' to indicate event end time!"
                );
            }
            String from = toSplit[0].trim();
            String to = toSplit[1].trim();
            newTask = new Event(eventName, from, to);
        }
        addList(newTask, list);
    }
    public static void main(String[] args) {
        String name = "Yippee";
        ArrayList<Task> list = new ArrayList<>();

        //greeting
        System.out.println("    ____________________________________________________________");
        System.out.printf("      Hello! I'm %s\n", name);
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________");

        //scan for input
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        //echo until user inputs bye
        while(!command.toLowerCase().equals("bye")) {
            // split command by spaces
            String[] split = command.split("\\s+", 2);
            if (split[0].toLowerCase().equals("list")) {
                printList(list);
            } else if (split[0].toLowerCase().equals("mark")) {
                try {
                    markTask(split, list);
                } catch(InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else if (split[0].toLowerCase().equals("unmark")) {
                try {
                    unmarkTask(split, list);
                } catch (InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else if (split[0].toLowerCase().equals("delete")) {
                try {
                    deleteTask(split, list);
                } catch (InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else {
                try {
                    createTask(split, list);
                } catch(InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            }
            command = sc.nextLine();
        }

        //exit
        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye! Hope to see you again soon wooo!");
        System.out.println("    ____________________________________________________________");
    }


}
