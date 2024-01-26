import javax.sound.midi.SysexMessage;
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
                System.out.println("\t\tAdded a new task to the list!");
                if (arr[0].equals("deadline")) {
                    Deadline d = new Deadline(newArr[0], newArr[1]);
                    list.add(d);
                    System.out.println("\t\t  " + d.toString());
                } else if (arr[0].equals("todo")) {
                    Todo td = new Todo(newArr[0]);
                    list.add(td);
                    System.out.println("\t\t  " + td.toString());
                } else if (arr[0].equals("event")) {
                    Event e = new Event(newArr[0], newArr[1], newArr[2]);
                    list.add(e);
                    System.out.println("\t\t  " + e.toString());
                }
                System.out.println("\t\tYou have " + list.size() + " too many tasks to do!!!" +
                        "\n\t\tQuickly start working on them!!!\n");
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
