import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Gulie {
    private final static String name = "Güliedistodiez";
    private final static String line = "____________________________________________________________";
    private ArrayList<Task> list;

    public Gulie() {
        System.out.println(line);
        this.greet();
        System.out.println(line);
        this.list = new ArrayList<>();
    }

    public boolean input(String str) {
        String[] splitted = str.split(" ");
        System.out.println(line);
        switch (splitted[0]) {
            case "bye": {
                this.exit();
                System.out.println(line);
                return false;
            } case "list": {
                this.list();
                break;
            } case "mark": {
                this.mark(Integer.parseInt(splitted[1]));
                break;
            } case "unmark": {
                this.unmark(Integer.parseInt(splitted[1]));
                break;
            } case "todo": {
                this.store(new ToDo(splitted[1]));
                break;
            } case "deadline": {
                String name = Gulie.getArgument(str,"deadline");
                String by = Gulie.getArgument(str, "/by");
                this.store(new Deadline(name, by));
                break;
            } case "event": {
                String name = Gulie.getArgument(str, "event");
                String from = Gulie.getArgument(str, "/from");
                String to = Gulie.getArgument(str, "/to");
                this.store(new Event(name, from, to));
                break;
            } default: {
                System.out.println(line);
                return false;
            }
        }
        System.out.println(line);
        return true;
    }

    private static String getArgument(String inp, String name) {
        name = " " + name + " ";
        inp = " " + inp;
        String str = inp.substring(inp.indexOf(name) + name.length());
        if (str.indexOf(" /") == -1)
            return str;
        else
            return str.substring(0, str.indexOf(" /"));
    }

    private void greet() {
        System.out.println(String.format(" Greetings. I am %s.\n What can I do for you?", name));
    }

    private void exit() {
        System.out.println(" Goodbye.");
    }

    private void list() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format(" %d. %s", i + 1, list.get(i)));
        }
    }

    private void store(Task task) {
        System.out.println(" Understood. I have added this task:\n   " + task);
        this.list.add(task);
        System.out.println(String.format("You now have %d tasks in the list", list.size()));
    }

    private void mark(int i) {
        Task task = list.get(i - 1);
        task.setMark(true);
        System.out.println(" I have marked this task as completed:\n   " + task);
    }

    private void unmark(int i) {
        Task task = list.get(i - 1);
        task.setMark(false);
        System.out.println(" I have marked this task as incomplete:\n   " + task);
    }

    public static void main(String[] args) {
        Gulie gulie = new Gulie();
        Scanner scanner = new Scanner(System.in);
        while (gulie.input(scanner.nextLine())) {

        }
    }
}
