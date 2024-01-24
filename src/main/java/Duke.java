import java.util.Scanner;
public class Duke {
    private final TaskManager tm = new TaskManager();
    Scanner sc = new Scanner(System.in);

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

    private Event createEvent(String s) throws DukeException {
        int fromIndex = s.indexOf("/from");
        int toIndex = s.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || s.length() < 7) {
            throw new DukeException("Format Error, Event must be in format: A /from B /to C");
        }
        String eventName = s.substring(6, fromIndex - 1);
        try {
            String from = s.substring(fromIndex + 6, toIndex - 1);
            String to = s.substring(toIndex + 4);
            return new Event(eventName, from, to);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Format Error, Event must be in format: A /from B /to C");
        }
    }

    private Deadline createDeadline(String s) throws DukeException {
        int byIndex = s.indexOf("/by");
        if (byIndex == -1 || s.length() < 10) {
            throw new DukeException("Format Error, Deadline must be in format: A /by B");
        }
        String deadlineName = s.substring(9, byIndex - 1);
        try {
            String deadlineBy = s.substring(byIndex + 4);
            return new Deadline(deadlineName, deadlineBy);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Format Error, Deadline must be in format: A /by B");
        }
    }

    private Todo createTodo(String s) throws DukeException {
        if (s.length() < 6) {
            throw new DukeException("Error, description of todo is missing");
        }
        String name = s.substring(5);
        return new Todo(name);
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
                    try {
                        Todo t = d.createTodo(s1);
                        d.addTask(t);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        Deadline dl = d.createDeadline(s1);
                        d.addTask(dl);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        Event e = d.createEvent(s1);
                        d.addTask(e);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    try {
                        throw new DukeException("Unknown Command");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

