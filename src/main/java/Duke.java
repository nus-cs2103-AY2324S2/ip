import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        String line = "\t________________________________________________________________";

        Scanner read = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        System.out.println(line + "\n" + logo + "\n\t Hello! I'm JavAssist");
        System.out.println("\t What can I do for you?\n" + line);
        String input;
        while(true) {
            System.out.println();
            input = read.nextLine();
            System.out.println(line);
            if (input.equals("list")) {
                print(list);
            } else if (input.matches("mark\\s\\d+")) {
                String[] s = input.split(" ");
                int num = Integer.parseInt(s[1]);
                if (num <= list.size()) {
                    Task t = list.get(num - 1);
                    t.done();
                    System.out.println("\t Nice! I've marked this task as done:");
                    System.out.println("\t\t" + t.printTask());
                }
            } else if (input.matches("unmark\\s\\d+")) {
                String[] s = input.split(" ");
                int num = Integer.parseInt(s[1]);
                if (num <= list.size()) {
                    Task t = list.get(num - 1);
                    t.undo();
                    System.out.println("\t Nice! I've marked this task as not done yet:");
                    System.out.println("\t\t" + t.printTask());
                }
            } else if (input.equals("bye")) {
                break;
            } else {
                list.add(new Task(input));
                System.out.println("\t added: " + input);
            }
            System.out.println(line);
        }

        System.out.println("\t Bye. Hope to see you again soon!\n" + line);
    }

    private static void print(ArrayList<Task> list) {
        int count = 1;
        for (Task t : list) {
            System.out.println("\t " + count + "." + t.printTask());
            count++;
        }
    }
}
