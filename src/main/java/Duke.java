import javax.swing.text.TabStop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Duke {
    private static final String LINE_BREAK = "\n---------------------------------------------\n";
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
        put("DELETE", "Task %s deleted: \n%s\nCurrent tasks:\n");

        put("INCORRECT_INPUT_EXCEPTION", "You haven't taught me that trick yet..");
        put("NOT_ENOUGH_INPUTS_EXCEPTION",
                "hmm, %s what?\nUsage: %s");
        put("NOT_ENOUGH_DATES_EXCEPTION",
                "You need a name for the task and a total of %d date(s) for a %s task!\nUsage: %s");
        put("DUPLICATE_TASK_NAME_EXCEPTION", "There already exists a task with the name %s!");
        put("INCORRECT_INDEX_EXCEPTION", "Please enter a valid integer!\nUsage: %s");

        put("TODO_CORRECT_USAGE", "todo [task]");
        put("ECHO_CORRECT_USAGE", "echo [message]");
        put("EVENT_CORRECT_USAGE", "event [task] /from [date] /to [date]");
        put("DEADLINE_CORRECT_USAGE", "deadline [task] /by [date]");
        put("DELETE_CORRECT_USAGE", "delete [index]");
    }};

    private static final Map<String, String> REGEX = new HashMap<>() {{
        put("DEADLINE", " /by ");
        put("EVENT_FROM", " /from ");
        put("EVENT_TO", " /to ");
    }};

    private static class Tasks {
        private static ArrayList<Task> arr;

        public static int size() {
            return arr.size();
        }

        public static Task get(int i) {
            return arr.get(i);
        }
        public static void add(Task task) throws DuplicateTaskNameException {
            long dupes = arr.stream().filter(task1 -> task1.task.equals(task.task)).count();
            if (dupes != 0) {
                throw new DuplicateTaskNameException(
                        String.format(MESSAGES.get("DUPLICATE_TASK_NAME_EXCEPTION"), task.task));
            }
            arr.add(task);
        }

        public static Task delete(String index) throws IncorrectIndexException, NotEnoughInputsException {
            int i = 0;
            Task deleted;
            try {
                i = Integer.parseInt(index.strip()) - 1;
                deleted = arr.get(i);
            } catch (Exception e) {
                throw new IncorrectIndexException(
                        String.format(MESSAGES.get("INCORRECT_INDEX_EXCEPTION"),MESSAGES.get("DELETE_CORRECT_USAGE")));
            }
            arr.remove(i);
            echo(String.format(MESSAGES.get("DELETE"), index, deleted));
            Tasks.list();
            return deleted;
        }

        public static void list() {
            for (int i = 0; i < Tasks.size(); i++) {
                Task currTask = Tasks.get(i);
                System.out.printf("%d: %s%n", i + 1, currTask);
            }
        }

        public Tasks() {
            arr = new ArrayList<>();
        }

    }
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

    private abstract static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    private static class IncorrectInputException extends DukeException {
        public IncorrectInputException(String message) {
            super(message);
        }
    }

    private static class NotEnoughInputsException extends IncorrectInputException {
        public NotEnoughInputsException(String message) {
            super(message);
        }
    }

    private static class NotEnoughDatesException extends NotEnoughInputsException {
        public NotEnoughDatesException(String message) {
            super(message);
        }
    }

    private static class DuplicateTaskNameException extends IncorrectInputException {

        public DuplicateTaskNameException(String message) {
            super(message);
        }
    }

    private static class IncorrectIndexException extends IncorrectInputException {
        public IncorrectIndexException(String message) {
            super(message);
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
        new Tasks();
    }

    private static void list() throws NotEnoughInputsException {
        echo(MESSAGES.get("LIST"));
        Tasks.list();
    }

    private static void hello() throws NotEnoughInputsException {
        System.out.println(LINE_BREAK);
        echo(MESSAGES.get("HELLO"));
        System.out.println(LINE_BREAK);
    }

    private static void bye() throws NotEnoughInputsException {
        String message = MESSAGES.get("BYE");
        echo(message);
    }

    private static void echo(String message, boolean isFromUser) throws NotEnoughInputsException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            MESSAGES.get("NOT_ENOUGH_INPUTS_EXCEPTION"), "echo", MESSAGES.get("ECHO_CORRECT_USAGE")));
        }
        echo(params[1]);
    }

    private static void echo(String message) throws NotEnoughInputsException {
        if (message.isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(
                            MESSAGES.get("NOT_ENOUGH_INPUTS_EXCEPTION"), "echo", MESSAGES.get("ECHO_CORRECT_USAGE")));
        }
        System.out.println(MESSAGES.get("ECHO") + message);
    }

    private static void todo(String message) throws NotEnoughInputsException, DuplicateTaskNameException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            MESSAGES.get("NOT_ENOUGH_INPUTS_EXCEPTION"), "todo", MESSAGES.get("TODO_CORRECT_USAGE")));

        }

        Task t = new Todo(params[1]);
        Tasks.add(t);
        echo(String.format(MESSAGES.get("TODO"), t));
    }

    private static void deadline(String message) throws NotEnoughInputsException, DuplicateTaskNameException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            MESSAGES.get("NOT_ENOUGH_INPUTS_EXCEPTION"),
                            "deadline",
                            MESSAGES.get("DEADLINE_CORRECT_USAGE")));
        }
        String[] arguments = params[1].split(REGEX.get("DEADLINE"));

        if (arguments.length == 1) {
            throw new NotEnoughDatesException(
                    String.format(
                            MESSAGES.get("NOT_ENOUGH_DATES_EXCEPTION"),
                            1,
                            "deadline",
                            MESSAGES.get("DEADLINE_CORRECT_USAGE")));
        }
        String task = arguments[0];
        Date date = new Date(arguments[1]);
        Task t = new Deadline(task, date);
        Tasks.add(t);
        echo(String.format(MESSAGES.get("DEADLINE"), t));
    }

    private static void event(String message) throws NotEnoughInputsException, DuplicateTaskNameException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            MESSAGES.get("NOT_ENOUGH_INPUTS_EXCEPTION"),
                            "event",
                            MESSAGES.get("EVENT_CORRECT_USAGE")));
        }
        params = params[1].split(REGEX.get("EVENT_FROM"));
        if (params.length != 2 || params[1].split(REGEX.get("EVENT_TO")).length != 2) {
            throw new NotEnoughDatesException(
                    String.format(
                            MESSAGES.get("NOT_ENOUGH_DATES_EXCEPTION"),
                            2,
                            "event",
                            MESSAGES.get("EVENT_CORRECT_USAGE")));
        }
        String[] dates = params[1].split(REGEX.get("EVENT_TO"));
        Date from = new Date(dates[0]);
        Date to = new Date(dates[1]);
        Task t = new Event(params[0], from, to);
        Tasks.add(t);
        echo(String.format(MESSAGES.get("EVENT"),  t));
    }


    private static void mark(String task, boolean completed) throws NotEnoughInputsException {
        // Find the task entry.
        Task found = null;
        for (int i = 0; i < Tasks.size(); i++) {
            if (Tasks.get(i).task.equals(task)) {
                found = Tasks.get(i);
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

    private static void delete(String input) throws NotEnoughInputsException, IncorrectIndexException {
        String[] params = input.split(" ", 2);
        if (params.length == 1) {
            throw new NotEnoughInputsException(
                    String.format(MESSAGES.get("NOT_ENOUGH_INPUTS_EXCEPTION"),
                            "delete",
                            MESSAGES.get("DELETE_CORRECT_USAGE")));
        }
        Tasks.delete(params[1]);
    }

    private static boolean parseInput(boolean loop, String input) throws DukeException {
        System.out.println(LINE_BREAK);
        String[] messages = input.split(" ", 2);
        String command = messages[0];

        try {
            switch (command) {
                case ("bye"):
                    loop = false;
                    bye();
                    break;
                case ("echo"):
                    echo(input, true);
                    break;
                case ("list"):
                    list();
                    break;
                case ("mark"):
                    mark(input, true);
                    break;
                case ("unmark"):
                    mark(input, false);
                    break;
                case ("todo"):
                    todo(input);
                    break;
                case ("deadline"):
                    deadline(input);
                    break;
                case ("event"):
                    event(input);
                    break;
                case ("delete"):
                    delete(input);
                    break;
                default:
                    throw new IncorrectInputException(MESSAGES.get("INCORRECT_INPUT_EXCEPTION"));
            }
        } catch (IncorrectInputException e) {
            echo(e.getMessage());
        }
        return loop;
    }

    public static void main(String[] args) throws DukeException {
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
