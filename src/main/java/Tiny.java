import java.io.*;

public class Tiny {

    public static void main(String[] args) throws IOException {
        String startUpMessage = "   ____________________________________________________________\n" +
                "   Hello! I'm Tiny!\n" +
                "   What can I do for you?\n" +
                "   ____________________________________________________________\n";

        Task[] tasks = new Task[100];
        int totalTasks = 0;

        System.out.println(startUpMessage);

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            if (isBye(input))
                break;

            System.out.println("   ____________________________________________________________");

            if (isList(input)) {
                list(input, tasks, totalTasks);

                // mark command
            } else if (isMark(input)) {
                mark(input, tasks);

                // unmark command
            } else if (isUnmark(input)) {
                unmark(input, tasks);

                //Bye command
            } else {
                tasks[totalTasks] = new Task(input);
                totalTasks++;
                System.out.println("   added: " + input);                
            }
            System.out.println("   ____________________________________________________________\n");
        }
        bye();
    }


    public static boolean isList(String input) {
        return input.equals("list");
    }

    public static void list(String input, Task[] tasks, int totalTasks) {
        if (totalTasks == 0) {
            System.out.println("   You don't have any tasks!");
        }

        for (int i = 0; i < totalTasks; i++) {
            System.out.println("   " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getName());
        }
    }

    public static boolean isMark(String input) {
        return input.length() >= 6 && input.substring(0, 5).equals("mark ");
    }

    public static void mark(String input, Task[] tasks) {
        String[] s = input.split(" ");
        if (s.length != 2) {
            System.out.println("    OOPS! You need to type \"mark <number>\" to change the status to done!");
        }
        try {
            int ind = Integer.parseInt(s[1]);
            System.out.println(tasks[ind - 1].taskDone());
        } catch (NumberFormatException e) {
            System.out.println("    OOPS! You need to type \"mark <number>\" to change the status to done!");

        } catch (NullPointerException e) {
            System.out.println("    OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.");
        }
    }

    public static boolean isUnmark(String input) {
        return input.length() >= 8 && input.substring(0, 7).equals("unmark ");
    }

    public static void unmark(String input, Task[] tasks) {

        String[] s = input.split(" ");
        if (s.length != 2) {
            System.out.println("    OOPS! You need to type \"unmark <number>\" to change the status not done!");
        }
        try {
            int ind = Integer.parseInt(s[1]);
            System.out.println(tasks[ind - 1].taskUndone());
        } catch (NumberFormatException e) {
            System.out.println(
                    "    OOPS! You need to type \"unmark <number>\" to change the status to not done!");

        } catch (NullPointerException e) {
            System.out.println(
                    "    OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.");
        }
    }
    public static boolean isBye(String input) {
        return input.equals("bye");
    }

    public static void bye() {
        System.out.println("   ____________________________________________________________");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }
}
