import Exceptions.*;
import Tasks.*;


import java.util.ArrayList;
import java.util.Scanner;

public class Lelu {

    public static ArrayList<Task> tasks;
    public static int index;
    public static void greet() {
        String greet = "    Hi! I am your favourite friend, Lelu :)\n    What can I do for you?\n";
        System.out.println(greet);
    }
    public static void exit() {
        String exit = "    Ok bye, you shall be missed :(";
        System.out.println(exit);
    }
    public static void list() {
        for (int i = 0; i < Lelu.tasks.size(); i++) {
            System.out.printf("    %d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println();
    }
    public static void mark(int i) {
        Lelu.tasks.get(i).markTask();
        System.out.printf("    Great job completing your task!\n      %s\n\n", tasks.get(i).toString());
    }
    public static void unmark(int i) {
        Lelu.tasks.get(i).unmarkTask();
        System.out.printf("    Don't forget to complete your task soon...\n      %s\n\n", tasks.get(i).toString());
    }

    public static void delete(int i) {
        Task t = Lelu.tasks.get(i);
        Lelu.tasks.remove(i);
        System.out.printf("    Ok, I have removed your task:\n    %s\n    You have %d task(s) in the " +
                "list now.\n\n", t.toString(), Lelu.tasks.size());;
    }
    public static void listen() throws InvalidInputException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String taskName = sc.nextLine();

            if (taskName.equals("bye")) {
                Lelu.exit();
                break;
            } else if (taskName.equals("list")) {
                Lelu.list();
                continue;
            }
            Task t = null;
            switch (taskName.split(" ")[0]) {
                case "mark":
                    Lelu.mark(Integer.parseInt(taskName.split(" ")[1]) - 1);
                    break;
                case "unmark":
                    Lelu.unmark(Integer.parseInt(taskName.split(" ")[1]) - 1);
                    break;
                case "delete":
                    Lelu.delete(Integer.parseInt(taskName.split(" ")[1]) - 1);
                    break;
                case "todo":
                    t = ToDo.ToDoOf(taskName);
                    break;
                case "deadline":
                    t = Deadline.DeadlineOf(taskName);
                    break;
                case "event":
                    t = Event.EventOf(taskName);
                    break;
                default:
                    throw new InvalidInputException("Please type in:\n" +
                            "------------------------\n" +
                            "[to record your task(s)/ events]\n" +
                            "- todo <task>\n" +
                            "- deadline <task> /by <date>\n" +
                            "- event <event> /from <date and time> /to <date and time>\n\n" +
                            "[to view your task(s)]\n" +
                            "- list\n\n" +
                            "[to mark or unmark your task as done]\n" +
                            "- mark <task number in list>\n" +
                            "- unmark <task number in list>\n" +
                            "-------------------------------------\n");
            }
            if (t != null) {
                Lelu.tasks.add(t);
                System.out.printf("    Ok! I have added your task:\n      %s\n    You have %d task(s) in the " +
                        "list now.\n\n", t.toString(), Lelu.tasks.size());
            }
        }
    }

    public static void main(String[] args) {
        Lelu.greet();
        Lelu.tasks = new ArrayList<>();
        Lelu.index = 0;
        while (true) {
            try {
                Lelu.listen();
                break;
            } catch (LeluException e) {
                System.out.println(e.getMessage());
            }
        }
     }

}

