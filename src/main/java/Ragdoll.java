import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ragdoll{
    private static String USER = "Master";
    private static List<Task> tasks = new ArrayList<>();
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
                + "                     /$$  \\ $$\n"
                + "                    |  $$$$$$/\n"
                + "                     \\______/\n";

        System.out.println("Hello! I am your virtual assistant\n" + "\n" + logo);

        System.out.println("How can I assist you today, " + USER + "?");

        System.out.println(LINE);

        while(true) {
            String input = scanner.nextLine().trim();
            System.out.println(LINE);

            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            if ("bye".equalsIgnoreCase(input)) {
                break;
            } else if ("list".equalsIgnoreCase(input)) {
                listTasks();
            } else if ("mark".equalsIgnoreCase(command)) {
                markTask(input);
            } else if ("unmark".equalsIgnoreCase(command)) {
                unmarkTask(input);
            } else if ("delete".equalsIgnoreCase(command)) {
                deleteTask(input);
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
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            System.out.println("Sorry, " + USER + ", please format your tasks as [task type] [task description]!\n" +
                    "I am only able to handle 3 task types: todo, deadline, and event.");
            return;
        }

        String taskType = parts[0].toLowerCase();
        Task task;

        try {
            switch (taskType) {
                case "todo":
                    task = new ToDo(parts[1]);
                    break;
                case "deadline":
                    String[] info = parts[1].split(" /by ", 2);
                    if (info.length < 2) {
                        throw new IllegalArgumentException("Sorry, " + USER +
                                ", please use 'deadline [task] /by [date]'.");
                    }
                    task = new Deadline(info[0], info[1]);
                    break;
                case "event":
                    String[] eventParts = parts[1].split(" /from ", 2);
                    if (eventParts.length < 2) {
                        throw new IllegalArgumentException("Sorry, " + USER +
                                ", please use 'event [task] /from [start time] /to [end time]'.");
                    }
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts.length < 2) {
                        throw new IllegalArgumentException("Sorry, " + USER +
                                ", please use 'event [task] /from [start time] /to [end time]'.");
                    }
                    task = new Event(eventParts[0], timeParts[0], timeParts[1]);
                    break;
                default:
                    System.out.println("Sorry, " + USER + ", please format your tasks as [task type] [task description]!\n" +
                            "I am only able to handle 3 task types: todo, deadline, and event.");
                    return;
            }

            tasks.add(task);
            taskCount++;

            System.out.println(USER + ", I've added this task:\n  " + task);
            if (taskCount <= 1) {
                System.out.println("Now you have " + taskCount + " task in the list, " + USER + "!");
            } else {
                System.out.println("Now you have " + taskCount + " tasks in the list, " + USER + "!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            if (idx < 0 || idx >= taskCount) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                Task removed = tasks.remove(idx);
                taskCount--;

                System.out.println(USER + ", I've removed this task:\n  " + removed);
                if (taskCount <= 1) {
                    System.out.println("Now you have " + taskCount + " task in the list, " + USER + "!");
                } else {
                    System.out.println("Now you have " + taskCount + " tasks in the list, " + USER + "!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as delete [task index].");
        }
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println("There is no task yet, " + USER + "!");
        } else {
            System.out.println(USER + ", your task list has the following tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void markTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(5)) - 1;
            if (idx < 0 || idx >= taskCount || tasks.get(idx) == null) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                tasks.get(idx).mark();
                System.out.println(USER + "! I've marked this task as done:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as mark [task index].");
        }
    }

    private static void unmarkTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            if (idx < 0 || idx >= taskCount || tasks.get(idx) == null) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                tasks.get(idx).unmark();
                System.out.println("Ok, " + USER + "! I've undone this task:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as mark [task index].");
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
