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
    private static abstract class Task {
        protected String task;
        protected boolean completed;

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public String completedIcon() {
            return this.completed ? "X" : " ";
        }

        public Task(String task) {
            this.task = task;
            this.completed = false;
        }

        public abstract String getType();

        public abstract String getAdditionalInfo();

        public abstract String toString();
    }

    private static class Todo extends Task {
        public Todo(String task) {
            super(task);
        }

        @Override
        public String getType() {
            return "[T]";
        }

        @Override
        public String getAdditionalInfo() {
            return "";
        }

        @Override
        public String toString() {
            return String.format("%s[%s]: %s", getType(), completedIcon(), task);
        }
    }

    private static class Deadline extends Task {
        private Date deadline;

        public Deadline(String task, Date deadline) {
            super(task);
            this.deadline = deadline;
        }

        @Override
        public String getType() {
            return "[D]";
        }

        @Override
        public String getAdditionalInfo() {
            return String.format(MESSAGES.get("DEADLINE_TO_STRING"), deadline);
        }

        @Override
        public String toString() {
            return String.format("%s[%s]: %s%s", getType(), completedIcon(), task, getAdditionalInfo());
        }
    }

    private static class Event extends Task {
        private Date from;
        private Date to;

        public Event(String task, Date from, Date to) {
            super(task);
            this.from = from;
            this.to = to;
        }

        @Override
        public String getType() {
            return "[E]";
        }

        @Override
        public String getAdditionalInfo() {
            return String.format(MESSAGES.get("EVENT_TO_STRING"), from, to);
        }

        @Override
        public String toString() {
            return String.format("%s[%s]: %s%s", getType(), completedIcon(), task, getAdditionalInfo());
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
        Task t = new Todo(message);
        tasks.add(t);
        echo(String.format(MESSAGES.get("TODO"), t));
    }

    private static void deadline(String message) {
        String[] params = message.split(REGEX.get("DEADLINE"));
        String task = params[0];
        Date date = new Date(params[1]);
        Task t = new Deadline(task, date);
        tasks.add(t);
        echo(String.format(MESSAGES.get("DEADLINE"), t));
    }

    private static void event(String message) {
        String[] params = message.split(REGEX.get("EVENT_FROM"));
        String task = params[0];
        String[] dates = params[1].split(REGEX.get("EVENT_TO"));
        Date from = new Date(dates[0]);
        Date to = new Date(dates[1]);
        Task t = new Event(task, from, to);
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
