import java.util.Scanner;
public class Duke {
    private final TaskManager tm = new TaskManager();

    private void greet() {
        System.out.println("Hello! I'm Friendy.");
        System.out.println("What can I do for you?");
    }

    private void farewell() {
        System.out.println("Bye. I will miss you!");
    }

    private void add(String s) {
        System.out.println("added: " + s);
        Task t = new Task(s);
        tm.addTask(t);

    }

    private String[] listen() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().split(" ");
    }

    private void list() {
        tm.listTasks();
    }
    private void mark(int i) {
        System.out.println("Setting task as done...");
        tm.markDone(i);
        tm.listTasks();
    }

    private void unmark(int i) {
        System.out.println("Setting task as not done...");
        tm.undo(i);
        tm.listTasks();
    }
    public static void main(String[] args) {
        Duke d = new Duke();
        d.greet();
        boolean end = false;
        while(!end) {
            String[] s = d.listen();
            switch (s[0]) {
                case "bye":
                    d.farewell();
                    end = true;
                    break;
                case "list":
                    d.list();
                    break;
                case "mark":
                    d.mark(Integer.parseInt(s[1]));
                    break;
                case "unmark":
                    d.unmark(Integer.parseInt(s[1]));
                    break;
                default:
                    d.add(String.join(" ", s));
                    break;
            }
        }
    }
}

