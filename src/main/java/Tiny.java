import java.io.*;
import java.util.ArrayList;

public class Tiny {

    public static void main(String[] args) throws IOException {
        String startUpMessage = "   ____________________________________________________________\n" +
                "   Hello! I'm Tiny!\n" +
                "   What can I do for you?\n" +
                "   ____________________________________________________________\n";

        ArrayList<Task> tasks = new ArrayList<>();
        int totalTasks = 0;

        System.out.println(startUpMessage);

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            if (isBye(input))
                break;

            printLine();
            if (isList(input)) {
                list(input, tasks, totalTasks);

                // mark command
            } else if (isMark(input)) {
                mark(input, tasks);

                // unmark command
            } else if (isUnmark(input)) {
                unmark(input, tasks);

                // todo command
            } else if (isTodo(input)) {
                try {
                    String name = "";
                    String[] st = input.split("");
                    String[] s = input.split(" ");
                    if (!s[0].equals("todo")) {
                        tabPrint("OOPS! You need to type \"todo <description>\" to create a new todo!");
                    } else {
                        for (int i = 5; i < st.length; i++) {
                            name += st[i];
                        }

                        if (name == "") {
                            tabPrint("OOPS! The description of a todo cannot be empty.");
                        } else {
                            tasks.add(new Todo(name));
                            totalTasks++;
                            printAdd(tasks.get(totalTasks - 1).toString(), totalTasks);
                        }
                    }                 
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    tabPrint("OOPS! You need to type \"deadline <description> by: <deadline>\" to create a new todo!");
                }

                // deadline command
            } else if (isDeadline(input)) {
                try {
                    String name = "";
                    String[] st = input.split("/by ");
                    String[] s = input.split(" ");
                    if (!s[0].equals("deadline")) {
                        tabPrint("OOPS! You need to type \"deadline <description> /by <deadline>\" to create a new deadline!");
                    } else {
                        for (int i = 9; i < st.length; i++) {
                            name += st[i];
                        }     
                        tasks.add(new Deadline(name.trim(), st[1]));
                        totalTasks++;
                        printAdd(tasks.get(totalTasks - 1).toString(), totalTasks);
                    }                 
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    tabPrint("OOPS! You need to type \"deadline <description> /by <deadline>\" to create a new deadline!");
                }

            } else if (isEvent(input)) {
                try {
                    String name = "";
                    String[] s = input.split(" ");
                    if (!s[0].equals("event")) {
                        System.out.println("YES");
                        tabPrint("OOPS! You need to type \"event <description> /from <start date> /to <end date>\" to create a new deadline!");
                    } else {
                        String[] from = input.split("/from ");
                        String[] fromTo = from[1].split("/to ");
                        name = from[0].substring(5);
                        tasks.add(new Event(name.trim(), fromTo[0].trim(), fromTo[1].trim()));
                        totalTasks++;
                        printAdd(tasks.get(totalTasks - 1).toString(), totalTasks);
                    }                 
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("NO");

                    tabPrint("OOPS! You need to type \"event <description> /from <start date> /to <end date>\" to create a new deadline!");
                }

                // Add command
            } else {
                System.out.println("I'm sorry, but I don't know what that means :-(");
            }
            printLine();
        }
        bye();
    }

    public static boolean isList(String input) {
        return input.equals("list");
    }
    public static boolean isMark(String input) {
        return checkCmd(input, "mark", 4);
    }    

    public static boolean isUnmark(String input) {
        return checkCmd(input, "unmark", 6);
    }

    public static boolean isTodo(String input) {
        return checkCmd(input, "todo", 4);
    }

    public static boolean isDeadline(String input) {
        return checkCmd(input, "deadline", 8);
    }

    public static boolean isEvent(String input) {
        return checkCmd(input, "event", 5);
    }

    public static boolean isBye(String input) {
        return input.equals("bye");
    }


    public static void list(String input, ArrayList<Task> tasks, int totalTasks) {
        if (totalTasks == 0) {
            tabPrint("You don't have any tasks!");
        }

        for (int i = 0; i < totalTasks; i++) {
            tabPrint((i + 1) + "." + tasks.get(i));
        }
    }



    public static void mark(String input, ArrayList<Task> tasks) {
        try {
            String[] s = input.split(" ");
            if (s.length != 2 || !s[0].equals("mark")) {
                tabPrint("OOPS! You need to type \"mark <number>\" to change the status to done!");
                return;
            }
            int ind = Integer.parseInt(s[1]);
            tabPrint("Nice! I've marked this task as done:");
            tasks.get(ind - 1).taskDone();
            System.out.println(tasks.get(ind - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            tabPrint("OOPS! You need to type \"mark <number>\" to change the status to done!");

        } catch (NullPointerException e) {
            tabPrint("OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.");
        }
    }



    public static void unmark(String input, ArrayList<Task> tasks) {
        try {
            String[] s = input.split(" ");
            if (s.length != 2 || !s[0].equals("unmark")) {
                tabPrint("OOPS! You need to type \"unmark <number>\" to change the status not done!");
            }
            int ind = Integer.parseInt(s[1]);
            tabPrint("OK, I've marked this task as not done yet:");
            tasks.get(ind - 1).taskUndone();
            System.out.println(tasks.get(ind - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            tabPrint(
                    "OOPS! You need to type \"unmark <number>\" to change the status to not done!");
        } catch (NullPointerException e) {
            tabPrint(
                    "OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.");
        }
    }    

    public static void bye() {
        printLine();
        tabPrint("Bye. Hope to see you again soon!");
        printLine();
    }

    public static boolean checkCmd(String input, String name, int len) {
        return input.length() >= len && input.substring(0, len).equals(name);
    }

    public static void tabPrint(String input) {
        System.out.println("   " + input);
    }

    public static void printLine() {
        tabPrint("____________________________________________________________\n");
    }

    public static void printAdd(String content, int num) {
        tabPrint("Got it. I've added this task:");
        tabPrint("   " + content);
        tabPrint("Now you have " + num + " task(s) in the list.");
    }
}
