import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void echoText(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("      %s\n", text);
        System.out.println("    ____________________________________________________________");
    }

    public static void addList(String text, ArrayList<Task> list) {
        Task newTask = new Task(text);
        list.add(newTask);
        System.out.println("    ____________________________________________________________");
        System.out.printf("      added: %s\n", text);
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
            String[] split = command.split("\\s+");

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
                addList(command, list);
            }
            command = sc.nextLine();
        }

        //exit
        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye! Hope to see you again soon wooo!");
        System.out.println("    ____________________________________________________________");
    }


}
