import java.util.Scanner;
import java.util.ArrayList;

public class Luke {

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    private static String logo = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    private static ArrayList<Task> history = new ArrayList<>();

    private static void greet() {
        System.out.println("Hello! I'm\n" + logo + "\n");
    }

    private static void bye() {
        System.out.println("Don't be ridiculous!\n" +
                "It's... it's not like I want to see you again or anything!\n");
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            //task mode
            //first, determine the type of input.

            String input = sc.nextLine().trim(); //trim removes preceding and trailing whitespace.

            if (input.equals("bye")) {
                bye();
                sc.close();
                break;
            } else if (input.equals("list")) {
                int num = 1;
                if (history.size() == 0) {
                    System.out.println("Looks like you have way too much free time on your hands, huh.");
                }
                for (Task s : history) {
                    if (s.isDone()) {
                        System.out.println(num + "." + s.fullStatus());
                    } else {
                        System.out.println(num + "." + s.fullStatus());
                    }
                    num += 1;
                }
                System.out.println();
            } else if (input.split(" ")[0].equals("mark")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                history.get(idx).complete();
                System.out.println("Good work, I guess.");
                System.out.println((idx + 1) + "." + history.get(idx).fullStatus());
                System.out.println();
            } else {
                //it is a task.
                Task task;
                String taskType = input.split(" ")[0];
                if (taskType.equals("todo")) {
                    task = new Todo(input.substring(4).trim()); //TODO: better not hardcode 5 lol
                } else if (taskType.equals("deadline")) {
                    task = new Deadline(input.split("/")[0].substring(8).trim(),
                            input.split("/")[1].substring(2).trim());
                } else if (taskType.equals("event")) {
                    task = new Event(input.split("/")[0].substring(5).trim(),
                            input.split("/")[1].substring(4).trim(),
                            input.split("/")[2].substring(2).trim());
                } else {
                    task = new Task(input); //default task
                }

                history.add(task);
                System.out.println("I helped you add task '" + task.fullStatus() + "'. But do it yourself next time! Hmmph!"  + "\n");
            }
        }
    }
}


