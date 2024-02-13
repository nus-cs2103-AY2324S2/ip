import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Awex {
    /**
     * Coverts txt file of tasks to list of task objects
     *
     * @param list LinkedList for Task objects to be added
     * @param str String from txt file with task details
     */
    public static void listFiller(LinkedList<Task> list, String str) {
        String[] arr = str.split(" / ");
        if (arr[0].equals("T")) {
            list.add(new TodoTask(arr[2], arr[1]));
        } else if (arr[0].equals("D")) {
            list.add(new DeadlineTask(arr[2], arr[1], arr[3]));
        } else {
            list.add(new EventTask(arr[2], arr[1], arr[3], arr[4]));
        }
    }

    /**
     * Prints explainer message after user gives erroneous inputs.
     */
    public static void message() {
        System.out.println("Input type must be one of:");
        System.out.println("  1. list");
        System.out.println("  2. mark <task number>");
        System.out.println("  3. unmark <task number>");
        System.out.println("  4. todo <task>");
        System.out.println("  5. deadline <task> /by <YYYY-MM-DD hh:mm>");
        System.out.println("  6. event <task> /from <YYYY-MM-DD hh:mm> /to <YYYY-MM-DD hh:mm>");
        System.out.println("  7. delete <task number>");
        System.out.println("Type 'bye' to exit.");
    }

    public static void main(String[] args) {
        try {
            System.out.println("Hello! I'm AWEX!\nWhat can I do for you?");

            File f = new File("./list.txt");
            Scanner sc;
            LinkedList<Task> list = new LinkedList<>();
            if (!f.createNewFile()) {
                sc = new Scanner(f);
                while (sc.hasNext()) {
                    listFiller(list, sc.nextLine());
                }
            }

            sc = new Scanner(System.in);
            while (true) {
                String next = sc.nextLine();
                String[] arr = next.split(" ", 2);
                if (next.equals("bye")) {
                    FileWriter fw = new FileWriter("./list.txt");
                    int len = list.size();
                    for (int i = 0; i < len; i++) {
                        fw.write(list.get(i).toString() + System.lineSeparator());
                    }
                    fw.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (next.equals("list")) {
                    if (arr.length > 1) {
                        message();
                    } else if (list.isEmpty()){
                        System.out.println("List is empty.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        int len = list.size();
                        for (int i = 1; i <= len; i++) {
                            System.out.println(i + "." + list.get(i - 1).showAll());
                        }
                    }
                } else if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
                    String[] array = next.split(" ");
                    if (array.length != 2) {
                        System.out.println("Format should be '" + arr[0] + " <task number>'");
                    } else {
                        int i = Integer.parseInt(array[1]);
                        int len = list.size();
                        if (i == 0) {
                            System.out.println("Pick a value between 1 and " + len + ".");
                        } else if (i > len) {
                            System.out.println("List has only " + len + " tasks.");
                        } else {
                            if (arr[0].equals("delete")) {
                                System.out.println("Noted. I've removed this task:");
                                System.out.println("  " + list.remove(i - 1).showAll());
                                System.out.println("Now you have " + list.size() + " tasks in the list.");
                            } else {
                                Task t = list.get(i - 1);
                                t.changeStatus(arr[0]);
                                System.out.println("  " + t.showAll());
                            }
                        }
                    }
                } else {
                    Task t;
                    if (arr[0].equals("todo")) {
                        if (arr.length == 1) {
                            System.out.println("Format should be 'todo <task>'");
                            continue;
                        }
                        t = TodoTask.of(arr[1]);
                    } else if (arr[0].equals("deadline")) {
                        String[] array = next.split("/");
                        if (array.length != 2) {
                            System.out.println("Format should be 'deadline <task> /by <YYYY-MM-DD hh:mm>'");
                            continue;
                        }
                        t = DeadlineTask.of(arr[1]);
                    } else if (arr[0].equals("event")){
                        String[] array = next.split("/");
                        if (array.length != 3) {
                            System.out.println("Format should be 'event <task> /from <YYYY-MM-DD hh:mm> /to <YYYY-MM-DD hh:mm>'");
                            continue;
                        }
                        t = EventTask.of(arr[1]);
                    } else {
                        message();
                        continue;
                    }
                    list.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t.showAll());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}