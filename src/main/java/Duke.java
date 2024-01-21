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
                mark(input, true, list);
            } else if (input.matches("unmark\\s\\d+")) {
                mark(input, false, list);
            } else if (input.equals("bye")) {
                break;
            } else if (input.startsWith("todo")){
                Task t = new Todo(input.substring(5));
                add(t, list);
            } else if (input.startsWith("deadline")){
                String[] task = input.split("/by");
                Task t = new Deadline(task[0].substring(9).trim(), task[1].trim());
                add(t, list);
            } else if (input.startsWith("event")) {
                String[] task = input.split("/from");
                String[] time = task[1].split("/to");
                Task t = new Event(task[0].substring(6).trim(), time[0].trim(), time[1].trim());
                add(t, list);
            }
            System.out.println(line);
        }

        System.out.println("\t Bye. Hope to see you again soon!\n" + line);
    }

    private static void print(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task t : list) {
            System.out.println("\t " + count + "." + t.printTask());
            count++;
        }
    }

    private static void add(Task t, ArrayList<Task> list) {
        list.add(t);
        int size = list.size();
        System.out.println("\t Got it. I've added this task:\n\t\t " + t.printTask());
        System.out.println("\t Now you have " + size + (size > 1 ? " tasks" : " task") + " in the list.");
    }

    private static void mark(String input, boolean done, ArrayList<Task> list) {
        String[] s = input.split(" ");
        int num = Integer.parseInt(s[1]);
        if (num <= list.size()) {
            Task t = list.get(num - 1);
            if (!done) {
                t.undo();
                System.out.println("\t Ok, I've marked this task as not done yet:");
            } else {
                t.done();
                System.out.println("\t Nice! I've marked this task as done:");
            }
            System.out.println("\t\t" + t.printTask());
        }
    }
}
