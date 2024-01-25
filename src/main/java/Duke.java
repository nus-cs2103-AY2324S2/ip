import java.util.Scanner;

public class Duke {
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

    public static void createTodo(String userTxt, Task[] listArr, int i) {
        Task t = new ToDo(userTxt.substring(5));
        listArr[i] = t; // add task to list
        System.out.println(t.toString());
    }

    public static void createDeadline(String userTxt, Task[] listArr, int i) {
        int splitIdx = userTxt.lastIndexOf("/");
        String desc = userTxt.substring(9, splitIdx - 1);
        String by = userTxt.substring(splitIdx + 3);
        Task t = new Deadline(desc, by);
        listArr[i] = t; // add task to list
        System.out.println(t.toString());
    }

    public static void createEvent(String userTxt, Task[] listArr, int i) {
        int frmIdx = userTxt.lastIndexOf(" /from");
        int toIdx = userTxt.lastIndexOf("/to");
        String desc = userTxt.substring(6, frmIdx);
        String frm = userTxt.substring(frmIdx + 6, toIdx);
        String to = userTxt.substring(toIdx + 3);
        Task t = new Event(desc, frm, to);
        listArr[i] = t; // add task to list
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Jojo :)");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] listArr = new Task[100];
        String userTxt = sc.nextLine();
        int i = 0;
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
                System.out.println("Got it. I've added this task:");
                if (userTxt.startsWith("todo")) {
                    createTodo(userTxt, listArr, i);
                } else if (userTxt.startsWith("deadline")) {
                   createDeadline(userTxt, listArr, i);
                } else if (userTxt.startsWith("event")) {
                    createEvent(userTxt, listArr, i);
                }
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
            }
            userTxt = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
