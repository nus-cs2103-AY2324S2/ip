import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
            System.out.printf("      Nothing added to list yet!");
        }
        for (Task task : list) {
            System.out.printf("%d. %s\n", count, task.toString());
            count++;
        }
        System.out.println("    ____________________________________________________________");
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
                if (split.length == 1) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      Wrong format! Please include the number that you want me to mark >:(");
                    System.out.println("    ____________________________________________________________");
                } else {
                    int number = Integer.parseInt(split[1]);
                    if (number < 1 || number > list.size()) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Invalid number! Index does not exist >:((");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        Task task = list.get(number - 1);
                        task.markDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Yippee! I have marked this task as done ;D");
                        System.out.printf("        %s\n", task.toString());
                        System.out.println("    ____________________________________________________________");
                    }
                }
            } else if (split[0].toLowerCase().equals("unmark")) {
                if (split.length == 1) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      Wrong format! Please include the number that you want me to unmark >:(");
                    System.out.println("    ____________________________________________________________");
                } else {
                    int number = Integer.parseInt(split[1]);
                    if (number < 1 || number > list.size()) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Invalid number! Index does not exist >:((");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        Task task = list.get(number - 1);
                        task.markNotDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Awww...I have marked this task as not done yet :(");
                        System.out.printf("        %s\n", task.toString());
                        System.out.println("    ____________________________________________________________");
                    }
                }
            } else {
                Task newTask;
                if (split[0].toLowerCase().equals("todo")) {
                    newTask = new ToDo(split[1].trim());
                } else if (split[0].toLowerCase().equals("deadline")) {
                    String[] deadlineSplit = split[1].trim().split("/by");
                    newTask = new Deadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
                } else if (split[0].toLowerCase().equals("event")) {
                    String[] fromSplit = split[1].split("/from");
                    String eventName = fromSplit[0].trim();

                    String[] toSplit = fromSplit[1].split("/to");
                    String from = toSplit[0].trim();
                    String to = toSplit[1].trim();
                    newTask = new Event(eventName, from, to);
                } else {
                    newTask = new Task(split[1]);
                }
                addList(newTask, list);
            }
            command = sc.nextLine();
        }

        //exit
        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye! Hope to see you again soon wooo!");
        System.out.println("    ____________________________________________________________");
    }


}
