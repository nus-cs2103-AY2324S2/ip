import java.util.Scanner;

public class Ragdoll{
    private static String USER = "Master";
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " /$$$$$$$                            /$$           /$$ /$$\n"
                + "| $$__  $$                          | $$          | $$| $$\n"
                + "| $$  \\ $$  /$$$$$$   /$$$$$$   /$$$$$$$  /$$$$$$ | $$| $$\n"
                + "| $$$$$$$/ |____  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$| $$\n"
                + "| $$__  $$  /$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$| $$\n"
                + "| $$  \\ $$ /$$__  $$| $$  | $$| $$  | $$| $$  | $$| $$| $$\n"
                + "| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$/| $$| $$\n"
                + "|__/  |__/ \\_______/ \\____  $$ \\_______/ \\______/ |__/|__/\n"
                + "                     /$$  \\ $$                            \n"
                + "                    |  $$$$$$/                            \n"
                + "                     \\______/                             \n";

        System.out.println("Hello! I am your virtual assistant \n" + "\n" + logo);

        System.out.println("How can I assist you today, " + USER + "?");

        System.out.println(LINE);

        while(true) {
            String input = scanner.nextLine().trim();
            System.out.println(LINE);

            if ("bye".equalsIgnoreCase(input)) {
                break;
            } else if ("list".equalsIgnoreCase(input)) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark")) {
                unmarkTask(input);
            } else {
                addTask(input);
            }

            System.out.println(LINE);
        }

        scanner.close();
        System.out.println("See ya, " + USER + "!");
        System.out.println(LINE);
    }

    private static void addTask(String input) {
        if (taskCount >= MAX_TASKS) {
            System.out.println(USER + ", task list is full!");
            return;
        }

        String[] parts = input.split(" ", 2);
        String taskType = parts[0].toLowerCase();
        Task task;

        switch (taskType) {
            case "todo":
                task = new ToDo(parts[1]);
                break;
            case "deadline":
                String[] info = parts[1].split(" /by ", 2);
                task = new Deadline(info[0], info[1]);
                break;
            case "event":
                String[] eventParts = parts[1].split(" /from ", 2);
                String[] timeParts = eventParts[1].split(" /to ", 2);
                task = new Event(eventParts[0], timeParts[0], timeParts[1]);
                break;
            default:
                System.out.println("Sorry, " + USER + ", this is an invalid task type.");
                return;
        }

        tasks[taskCount++] = task;
        System.out.println(USER + "!, I've added this task:\n  " + task);
        if (taskCount <= 1) {
            System.out.println("Now you have " + taskCount + " task in the list, " + USER + "!");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list, " + USER + "!");
        }
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println("There is no task yet, " + USER + "!");
        } else {
            System.out.println(USER + ", your task list has the following tasks: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
    }

    private static void markTask(String input) {
        int idx = Integer.parseInt(input.substring(5)) - 1;
        if (idx < 0 || idx >= taskCount || tasks[idx] == null) {
            System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
        } else {
            tasks[idx].mark();
            System.out.println(USER + "! I've marked this task as done: \n" + tasks[idx]);
        }
    }

    private static void unmarkTask(String input) {
        int idx = Integer.parseInt(input.substring(7)) - 1;
        if (idx < 0 || idx >= taskCount || tasks[idx] == null) {
            System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
        } else {
            tasks[idx].unmark();
            System.out.println("Ok, " + USER + "! I've undone this task: \n" + tasks[idx]);
        }
    }

    private static class Task{
        protected String description;
        protected boolean isDone;
        protected String type;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            String status = isDone ? "[X]" : "[ ]";
            return status + " " + description;
        }
    }

    private static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
            this.type = "[T]";
        }

        @Override
        public String toString() {
            return type + super.toString();
        }
    }

    private static class Deadline extends Task {
        private String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
            this.type = "[D]";
        }

        @Override
        public String toString() {
            return type + super.toString() + " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        private String from;
        private String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
            this.type = "[E]";
        }

        @Override
        public String toString() {
            return type + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }
}
