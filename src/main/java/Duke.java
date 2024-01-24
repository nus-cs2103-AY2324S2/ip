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

    private void addTask(Task t) {
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
            String s1 = String.join(" ", s);
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
                case "todo":
                    String name = String.join(" ", s).substring(5);
                    Todo t = new Todo(name);
                    d.addTask(t);
                    break;
                case "deadline":
                    int byIndex = s1.indexOf("/by");
                    String deadlineName = s1.substring(9, byIndex - 1);
                    String deadlineBy = s1.substring(byIndex + 4);
                    Deadline dl = new Deadline(deadlineName, deadlineBy);
                    d.addTask(dl);
                    break;
                case "event":
                    int fromIndex = s1.indexOf("/from");
                    int toIndex = s1.indexOf("/to");
                    String eventName = s1.substring(6, fromIndex - 1);
                    String from = s1.substring(fromIndex + 6, toIndex - 1);
                    String to = s1.substring(toIndex + 4);
                    Event e = new Event(eventName, from, to);
                    d.addTask(e);
                    break;
            }
        }
    }
}

