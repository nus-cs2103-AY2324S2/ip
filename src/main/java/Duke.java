import java.lang.reflect.Type;
import java.util.*;


public class Duke {
    private static String LINE_BREAK = "\n---------------------------------------------\n";
    private static final Map<String, String> MESSAGES = new HashMap<>() {{
        put("ADD", "Added %s. When are you going to add 'feed Squid'?");
        put("LIST", "Here are your tasks. Sucks to be you, my only 2 tasks are eating and sleeping.");
        put("HELLO", "HMm human. What do you want again?");
        put("BYE", "You're done. Time for my food.");
        put("ECHO", "Squid: ");
        put("TODO", "Added todo\n%s");
        put("DEADLINE", "Added deadline\n%s");
        put("EVENT", "Added event\n%s");
        put("MARK_COMPLETE", "That was slow, but at least you completed: \n %s");
        put("MARK_INCOMPLETE", "Can't make up your mind? \n %s");
        put("MARK_NOT_FOUND", "I can't find the task, dummy human!");
        put("DEADLINE_TO_STRING", " (by: %s)");
        put("EVENT_TO_STRING", " (from: %s to %s)");
    }};

    private static final Map<String, String> REGEX = new HashMap<>() {{
        put("DEADLINE", " /by ");
        put("EVENT_FROM", " /from ");
        put("EVENT_TO", " /to ");
    }};

    private static ArrayList<Task> tasks;

    private static class Task {
        private String task;
        private boolean completed;
        private enum Types {
            T,
            D,
            E
        }

        private Types type;

        private Date deadline;
        private Date from;
        private Date to;

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public String completedIcon() {
            return this.completed ? "X" : " ";
        }

        public Task(String task) {
            this.task = task;
            this.completed = false;
            this.type = Types.T;
        }

        public Task(String task, Date deadline) {
            this(task);
            this.type = Types.D;
            this.deadline = deadline;
        }

        public Task(String task, Date from, Date to) {
            this(task);
            this.type = Types.E;
            this.from = from;
            this.to = to;
        }

        public String toString() {
            String base = String.format("[%s][%s]: %s", this.type, this.completedIcon(), this.task);
            String add = this.type == Types.D
                    ? String.format(MESSAGES.get("DEADLINE_TO_STRING"), this.deadline)
                    : String.format(MESSAGES.get("EVENT_TO_STRING"), this.from, this.to);
            return this.type == Types.T ? base : base + add;
        }
    }

    private static class Date {
        private String date;
        private String formattedDate;
        private void formatDate() {
            this.formattedDate = this.date;
        }
        public Date(String date) {
            this.date = date;
            formatDate();
        }

        public String toString() {
            return this.formattedDate;
        }
    }

    private static void Squid() {
        tasks = new ArrayList<>();
    }

    private static void list() {
        echo(MESSAGES.get("LIST"));
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            System.out.printf("%d: %s%n", i + 1, currTask);
        }
    }

    private static void hello() {
        System.out.println(LINE_BREAK);
        echo(MESSAGES.get("HELLO"));
        System.out.println(LINE_BREAK);
    }

    private static void bye() {
        String message = MESSAGES.get("BYE");
        echo(message);
    }

    private static void echo(String message) {
        System.out.println(MESSAGES.get("ECHO") + message);
    }

    private static void todo(String message) {
        Task t = new Task(message);
        tasks.add(t);
        echo(String.format(MESSAGES.get("TODO"), t));
    }

    private static void deadline(String message) {
        String[] params = message.split(REGEX.get("DEADLINE"));
        String task = params[0];
        Date date = new Date(params[1]);
        Task t = new Task(task, date);
        tasks.add(t);
        echo(String.format(MESSAGES.get("DEADLINE"), t));
    }

    private static void event(String message) {
        String[] params = message.split(REGEX.get("EVENT_FROM"));
        String task = params[0];
        String[] dates = params[1].split(REGEX.get("EVENT_TO"));
        Date from = new Date(dates[0]);
        Date to = new Date(dates[1]);
        Task t = new Task(task, from, to);
        tasks.add(t);
        echo(String.format(MESSAGES.get("EVENT"),  t));
    }


    private static void mark(String task, boolean completed) {
        // Find the task entry.
        Task found = null;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).task.equals(task)) {
                found = tasks.get(i);
            }
        }
        if (found != null) {
            found.setCompleted(completed);
            echo(String.format(completed
                    ? MESSAGES.get("MARK_COMPLETE")
                    : MESSAGES.get("MARK_INCOMPLETE"), found));


        } else {
            echo(MESSAGES.get("MARK_NOT_FOUND"));
        }
    }

    private static boolean parseInput(boolean loop, String input) {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0];
        String arguments = inputs.length > 1 ? inputs[1] : "";
        System.out.println(LINE_BREAK);

        switch (command) {
            case ("bye"):
                loop = false;
                bye();
                break;
            case ("list"):
                list();
                break;
            case ("mark"):
                mark(arguments, true);
                break;
            case ("unmark"):
                mark(arguments, false);
                break;
            case ("todo"):
                todo(arguments);
                break;
            case ("deadline"):
                deadline(arguments);
                break;
            case ("event"):
                event(arguments);
                break;
            default:
                todo(input);
                break;
        }
        return loop;
    }

    public static void main(String[] args) {
        Squid();
        hello();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            String input = scanner.nextLine().strip();
            loop = parseInput(loop, input);
            System.out.println(LINE_BREAK);
        }

    }


}
