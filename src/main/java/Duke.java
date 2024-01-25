import exceptions.DukeException;
import exceptions.DukeTaskNoDescException;
import exceptions.DukeUnknownTaskException;

import java.util.Scanner;

public class Duke {
    public static int i = 0;
    public static void createList(Task[] listArr) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < 100; j++) { // printing out all items in the list
            if (listArr[j] == null) { // for early termination
                break;
            } else {
                System.out.println(j + 1 + ". " + listArr[j].toString());
            }
        }
    }

    public static void createTodo(String userTxt, Task[] listArr) {
        String test = userTxt.substring(4);
        try {
            if (test.strip().equals("")) {
                throw new DukeTaskNoDescException();
            } else {
                Task t = new ToDo(userTxt.substring(5));
                listArr[Duke.i] = t; // add task to list
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                Duke.i++;
                System.out.println("Now you have " + Duke.i + " tasks in the list.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createDeadline(String userTxt, Task[] listArr) {
        String test = userTxt.substring(8);
        try {
            if (test.strip().equals("")) {
                throw new DukeTaskNoDescException();
            } else {
                int splitIdx = userTxt.lastIndexOf("/");
                String desc = userTxt.substring(9, splitIdx - 1);
                String by = userTxt.substring(splitIdx + 3);
                Task t = new Deadline(desc, by);
                listArr[Duke.i] = t; // add task to list
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                Duke.i++;
                System.out.println("Now you have " + Duke.i + " tasks in the list.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createEvent(String userTxt, Task[] listArr) {
        String test = userTxt.substring(5);
        try {
            if (test.strip().equals("")) {
                throw new DukeTaskNoDescException();
            } else {
                int frmIdx = userTxt.lastIndexOf(" /from");
                int toIdx = userTxt.lastIndexOf("/to");
                String desc = userTxt.substring(6, frmIdx);
                String frm = userTxt.substring(frmIdx + 6, toIdx);
                String to = userTxt.substring(toIdx + 3);
                Task t = new Event(desc, frm, to);
                listArr[Duke.i] = t; // add task to list
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                Duke.i++;
                System.out.println("Now you have " + Duke.i + " tasks in the list.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Jojo :)");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        Task[] listArr = new Task[100];
        String userTxt = sc.nextLine();
        while (!userTxt.equals("bye")) {
            if (userTxt.equals("list")) {
                createList(listArr);
            } else if (userTxt.startsWith("mark")) {
                Integer taskNum = Integer.valueOf(userTxt.substring(5)) - 1;
                listArr[taskNum].setDone();
            } else if (userTxt.startsWith("unmark")) {
                Integer taskNum = Integer.valueOf(userTxt.substring(7)) - 1;
                listArr[taskNum].setUndone();
            } else {
                if (userTxt.startsWith("todo")) {
                    createTodo(userTxt, listArr);
                } else if (userTxt.startsWith("deadline")) {
                    createDeadline(userTxt, listArr);
                } else if (userTxt.startsWith("event")) {
                    createEvent(userTxt, listArr);
                } else {
                    try {
                        throw new DukeUnknownTaskException();
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------");
            userTxt = sc.nextLine();
        }
        if (userTxt.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------");
        }
    }
}
