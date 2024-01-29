import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("\t\tHello, my name is Xilef.\n\t\tHow may I help you today??\n");

        while (true) {
            String str = scanner.nextLine();
            String[] arr = str.split(" ");
            if (str.equals("bye")) {
                break;
            } else if (arr[0].equals("list")) {
                System.out.println("\t\tThese are the things on your agenda today");
                for (int i = 1; i < list.size() + 1; i++) {
                    Task t = list.get(i -1);
                    System.out.println("\t\t" + i +"." + t.toString());
                }
                System.out.println("");
            } else if (arr[0].equals("unmark")) {
                Task t = list.get(getIndex(arr));
                t.unmarkDone();
                System.out.println("\t\tReminder, you have not completed this task yet:\n\t\t  " + t.toString() + "\n");
            } else if (arr[0].equals("mark")) {
                Task t = list.get(getIndex(arr));
                t.markDone();
                System.out.println("\t\tGreat job, you have accomplished this task:\n\t\t  " + t.toString() + "\n");
            } else {
                String s = getDescripition(arr);
                String[] newArr = s.split(" /");
                try {
                    if (arr[0].equals("deadline")) {
                        if (newArr.length < 2) {
                            throw new DukeException("Incomplete deadline information");
                        }
                        System.out.println("\t\tAdded a new task to the list!");
                        Deadline d = new Deadline(newArr[0], newArr[1]);
                        list.add(d);
                        System.out.println("\t\t  " + d.toString());
                    } else if (arr[0].equals("todo")) {
                        System.out.println("\t\tAdded a new task to the list!");
                        Todo td = new Todo(newArr[0]);
                        list.add(td);
                        System.out.println("\t\t  " + td.toString());
                    } else if (arr[0].equals("event")) {
                        if (newArr.length < 3) {
                            throw new DukeException("Incomplete event information");
                        }
                        System.out.println("\t\tAdded a new task to the list!");
                        Event e = new Event(newArr[0], newArr[1], newArr[2]);
                        list.add(e);
                        System.out.println("\t\t  " + e.toString());
                    } else {
                        throw new DukeException("Invalid task type");
                    }
                    System.out.println("\t\tYou have " + list.size() + " too many tasks to do!!!" +
                            "\n\t\tQuickly start working on them!!!\n");
                } catch (DukeException e) {
                    System.out.println("\t\t" + e.getMessage());
                }
            }
        }
        System.out.println("\t\tBye bye, see you next time!!!");
    }
    private static int getIndex(String[] arr) {
        int index = Integer.parseInt(arr[1]);
        return index - 1;
    }

    private static String getDescripition(String[] arr) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            s.append(arr[i]).append(" ");
        }
        return s.toString();
    }
}
