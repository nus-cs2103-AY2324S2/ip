import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("\t\tHello, my name is Xilef.\n\t\tHow may I help you today??\n");

        while (true) {
            String str = scanner.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                System.out.println("\t\tThese are the things on your agenda today");
                for (int i = 1; i < list.size() + 1; i++) {
                    Task t = list.get(i -1);
                    System.out.println("\t\t" + i +"." + t.getStatusIcon() + " " + t.description);
                }
            } else if (str.contains("unmark ")) {
                Task t = list.get(getPos(str, 7));
                t.unmarkDone();
                System.out.println("\t\t Reminder, you have not completed this task yet:\n\t\t  " + t.getStatusIcon() +
                        " " + t.description);
            } else if (str.contains("mark ")) {
                Task t = list.get(getPos(str, 5));
                t.markDone();
                System.out.println("\t\t Great job, you have accomplished this task:\n\t\t  " + t.getStatusIcon() +
                        " " + t.description);
            } else {
                Task t = new Task(str);
                list.add(t);
                System.out.println("\t\tadded: " + str);
            }
        }
        System.out.println("\t\tBye bye, see you next time !!!");
    }
    private static int getPos(String str, int i) {
        String index = str.substring(i);
        int pos = Integer.parseInt(index);
        return pos - 1;
    }
}
