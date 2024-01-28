import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {
    private static final Task[] store = new Task[100];
    private static int count = 0;
    private static final String DATA_FILE_PATH = "./data/botYue.txt";

    public static void main(String[] args) {
        dialogue();
        loadTasks();


        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();

            try {
                check(inputs);
                saveTasks();

            } catch (DukeException e) {
                System.out.println("   ____________________________________________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("   ____________________________________________________________");
            }

        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm BotYue");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void check(String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) {

        } else if (input.startsWith("list")) {
            list();

        } else if (input.startsWith("mark")) {
            mark(input);

        } else if (input.startsWith("unmark")) {
            unmark(input);

        } else if (input.startsWith("todo")) {
            add(TaskType.TODO, input.substring(4).trim());

        } else if (input.startsWith("deadline")) {
            add(TaskType.DEADLINE, input.substring(8).trim());

        } else if (input.startsWith("event")) {
            add(TaskType.EVENT, input.substring(5).trim());

        } else if (input.startsWith("delete")) {
            deleteTask(input.substring(6).trim());

    } else {
            throw new DukeException("OOPS!!! I don't know what that means. Can you make it clear?");

        }
    }

    public static void add(Task task) {
        store[count++] = task;

        System.out.println("   ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + count + " tasks in the list.");
        System.out.println("   ____________________________________________________________");
    }

   public static void add(TaskType type, String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a " + type + " task cannot be empty!");
        }

        Task task;
        switch (type) {
            case TODO:
                task = new TodoTask(description);
                break;

            case DEADLINE:
                task = new DeadlineTask(description);
                break;

            case EVENT:
                task = new EventTask(description);
                break;

            default:
                throw new DukeException("OOPS!!! Unsupported task type.");
        }

        add(task);
    }


    public static void deleteTask(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num);

            if (index > 0 && index <= count) {
                System.out.println("   ____________________________________________________________");
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + store[index - 1]);

                for (int i = index - 1; i < count - 1; i++) {
                    store[i] = store[i + 1];
                }

                store[count - 1] = null;
                count--;

                System.out.println("    Now you have " + count + " tasks in the list.");
                System.out.println("   ____________________________________________________________");

            } else {
                throw new DukeException("OOPS!!! Task index is out of range.");
            }

        } catch (NumberFormatException e) {

            throw new DukeException("OOPS!!! Please enter a valid task index to delete.");
        }
    }


    public static void list() {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + ". " + store[i]);
        }

        System.out.println("   ____________________________________________________________");
    }

    public static void mark(String input) {
        int index = getIndex(input);

        if (checkValid(index)) {
            store[index - 1].markDone();

            System.out.println("   ____________________________________________________________");
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    public static void unmark(String input) {
        int index = getIndex(input);
        if (checkValid(index)) {
            store[index - 1].markNotDone();
            System.out.println("   ____________________________________________________________");
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    private static int getIndex(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static boolean checkValid(int index) {
        return index > 0 && index <= count;
    }


    public static void loadTasks() {
        try {
            File file = new File(DATA_FILE_PATH);

            if (file.exists()) {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");

                    if (parts.length >= 3) {
                        Task task;

                        switch (parts[0]) {
                            case "T":
                                task = new TodoTask(parts[2]);
                                break;
                            case "D":
                                task = new DeadlineTaskLoad(parts[2], parts[3]);
                                break;
                            case "E":
                                task = new EventTaskLoad(parts[2], parts[3]);
                                break;
                            default:
                                continue;
                        }
                        if (parts[1].equals("1")) {
                            task.markDone();
                        }
                        add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    public static void saveTasks() {
        try {
            File file = new File(DATA_FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < count; i++) {
                Task task = store[i];
                String taskType;

                if (task instanceof TodoTask) {
                    taskType = "T";
                } else if (task instanceof DeadlineTask) {
                    taskType = "D";
                } else if (task instanceof EventTask) {
                    taskType = "E";
                } else {
                    continue;
                }
                writer.write(taskType + " | " + (task.marked ? "1" : "0") + " | " + task.getTask());
                if (task instanceof DeadlineTask) {
                    writer.write(" | " + ((DeadlineTask) task).getBy());

                } else if (task instanceof EventTask) {
                    writer.write(" | " + ((EventTask) task).getTime());
                }

                writer.write(System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }


}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}











/*
import java.util.Scanner;
public class Duke {
    private static final Task[] store = new Task[100];
    private static int count = 0;

    public static void main(String[] args) {
        dialogue();

        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();

            try {
                check(inputs);
            } catch (DukeException e) {
                System.out.println("   ____________________________________________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("   ____________________________________________________________");
            }

        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm QuantumBot");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void check(String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) {

        } else if (input.startsWith("list")) {
            list();
        } else if (input.startsWith("mark")) {
            mark(input);
        } else if (input.startsWith("unmark")) {
            unmark(input);
        } else if (input.startsWith("todo")) {
            addTodoTask(input.substring(4).trim());
        } else if (input.startsWith("deadline")) {
            addDeadlineTask(input.substring(8).trim());
        } else if (input.startsWith("event")) {
            addEventTask(input.substring(5).trim());
        } else if (input.startsWith("delete")) {
            deleteTask(input.substring(6).trim());
        } else {
            throw new DukeException("OOPS!!! I don't know what that means. Can you make it clear?");

        }
    }

    public static void add(Task task) {
        store[count++] = task;

        System.out.println("   ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + count + " tasks in the list.");
        System.out.println("   ____________________________________________________________");
    }


    public static void addTodoTask(String task) throws DukeException {

        if (task.isEmpty()) {
            throw new DukeException("Oh no!! The description of a todo event cannot be empty.");
        }
        add(new TodoTask(task));
    }

    public static void addDeadlineTask(String task) throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("OOPS!! The description of a deadline event is empty.");
        }
        add(new DeadlineTask(task));
    }

    public static void addEventTask(String task) throws DukeException {

        if (task.isEmpty()) {
            throw new DukeException("The description of an event is empty, what event you want to add?");
        }
        add(new EventTask(task));
    }

    public static void deleteTask(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num);

            if (index > 0 && index <= count) {
                System.out.println("   ____________________________________________________________");
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + store[index - 1]);

                for (int i = index - 1; i < count - 1; i++) {
                    store[i] = store[i + 1];
                }
                store[count - 1] = null;
                count--;

                System.out.println("    Now you have " + count + " tasks in the list.");
                System.out.println("   ____________________________________________________________");
            } else {
                throw new DukeException("OOPS!!! Task index is out of range.");
            }
        } catch (NumberFormatException e) {

            throw new DukeException("OOPS!!! Please enter a valid task index to delete.");
        }
    }


    public static void list() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + ". " + store[i]);
        }
        System.out.println("   ____________________________________________________________");
    }

    public static void mark(String input) {
        int index = getIndex(input);
        if (checkValid(index)) {
            store[index - 1].markDone();
            System.out.println("   ____________________________________________________________");
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    public static void unmark(String input) {
        int index = getIndex(input);
        if (checkValid(index)) {
            store[index - 1].markNotDone();
            System.out.println("   ____________________________________________________________");
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    private static int getIndex(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static boolean checkValid(int index) {
        return index > 0 && index <= count;
    }
}

abstract class Task {
    protected String task;
    protected boolean marked;

    public Task(String task) {
        this.task = task;
        this.marked = false;
    }

    public void markDone() {
        marked = true;
    }

    public void markNotDone() {
        marked = false;
    }

    public String mark() {
        return (marked ? "[X]" : "[ ]");
    }


    public abstract String tag();

    public abstract String toString();
}

class TodoTask extends Task {
    public TodoTask(String task) {
        super(task);
    }

    @Override
    public String tag() {
        return "[T]";
    }

    @Override
    public String toString() {
        return tag() + mark() + " " + task;
    }
}

class DeadlineTask extends Task {
    private String by;

    public DeadlineTask(String task) {
        super(task);
        parseDeadline(task);
    }

    private void parseDeadline(String task) {
        String[] split = task.split("/by", 2);

        if (split.length == 2) {
            this.task = split[0];
            this.by = split[1].trim();
        }
    }

    @Override
    public String tag() {
        return "[D]";
    }

    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (by: " + by + ")";
    }
}

class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String task) {
        super(task);
        parseEvent(task);
    }

    private void parseEvent(String task) {
        String[] split = task.split("/from", 2);

        if (split.length == 2) {
            this.task = split[0];

            String[] details = split[1].split("/to", 2);
            if (details.length == 2) {
                this.from = details[0].trim();
                this.to = details[1].trim();
            }
        }
    }

    @Override
    public String tag() {
        return "[E]";
    }
    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (from: " + from + " to: " + to + ")";
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}*/



/*
Level 4
import java.util.Scanner;
public class Duke {
    private static final Task[] store = new Task[100];
    private static int count = 0;

    public static void main(String[] args) {
        dialogue();

        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();
            check(inputs);

        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm QuantumBot");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void check(String input) {
        if (input.equalsIgnoreCase("bye")) {

        } else if (input.startsWith("list")) {
            list();
        } else if (input.startsWith("mark")) {
            mark(input);
        } else if (input.startsWith("unmark")) {
            unmark(input);
        } else if (input.startsWith("todo")) {
            addTodoTask(input.substring(5).trim());
        } else if (input.startsWith("deadline")) {
            addDeadlineTask(input.substring(9).trim());
        } else if (input.startsWith("event")) {
            addEventTask(input.substring(6).trim());
        } else {
            System.out.println("   ____________________________________________________________");
            System.out.println("   " + input);
            System.out.println("   ____________________________________________________________");

        }
    }

    public static void add(Task task) {
        store[count++] = task;

        System.out.println("   ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + count + " tasks in the list.");
        System.out.println("   ____________________________________________________________");
    }


    public static void addTodoTask(String task) {
        add(new TodoTask(task));
    }

    public static void addDeadlineTask(String task) {
        add(new DeadlineTask(task));
    }

    public static void addEventTask(String task) {
        add(new EventTask(task));
    }

    public static void list() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + ". " + store[i]);
        }
        System.out.println("   ____________________________________________________________");
    }

    public static void mark(String input) {
        int index = getIndex(input);
        if (checkValid(index)) {
            store[index - 1].markDone();
            System.out.println("   ____________________________________________________________");
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    public static void unmark(String input) {
        int index = getIndex(input);
        if (checkValid(index)) {
            store[index - 1].markNotDone();
            System.out.println("   ____________________________________________________________");
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    private static int getIndex(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static boolean checkValid(int index) {
        return index > 0 && index <= count;
    }
}

abstract class Task {
    protected String task;
    protected boolean marked;

    public Task(String task) {
        this.task = task;
        this.marked = false;
    }

    public void markDone() {
        marked = true;
    }

    public void markNotDone() {
        marked = false;
    }

    public String mark() {
        return (marked ? "[X]" : "[ ]");
    }


    public abstract String tag();

    public abstract String toString();
}

class TodoTask extends Task {
    public TodoTask(String task) {
        super(task);
    }

    @Override
    public String tag() {
        return "[T]";
    }

    @Override
    public String toString() {
        return tag() + mark() + " " + task;
    }
}

class DeadlineTask extends Task {
    private String by;

    public DeadlineTask(String task) {
        super(task);
        parseDeadline(task);
    }

    private void parseDeadline(String task) {
        String[] split = task.split("/by", 2);

        if (split.length == 2) {
            this.task = split[0];
            this.by = split[1].trim();
        }
    }

    @Override
    public String tag() {
        return "[D]";
    }

    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (by: " + by + ")";
    }
}

class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String task) {
        super(task);
        parseEvent(task);
    }

    private void parseEvent(String task) {
        String[] split = task.split("/from", 2);

        if (split.length == 2) {
            this.task = split[0];

            String[] details = split[1].split("/to", 2);
            if (details.length == 2) {
                this.from = details[0].trim();
                this.to = details[1].trim();
            }
        }
    }

    @Override
    public String tag() {
        return "[E]";
    }
    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (from: " + from + " to: " + to + ")";
    }
}*/








/*Level 3
import java.util.Scanner;
public class Duke {
    private static final Task[] store = new Task[100];
    private static int count = 0;

    public static void main(String[] args) {
        dialogue();

        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();
            check(inputs);

        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm QuantumBot");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void check(String input) {
        if (input.equalsIgnoreCase("bye")) {

        } else if (input.equalsIgnoreCase("list")) {
            list();
        } else if (input.startsWith("mark")) {
            mark(input);
        } else if (input.startsWith("unmark")) {
            unmark(input);
        } else {
            add(input);
        }
    }

    public static void add(String task) {
        store[count++] = new Task(task);

        System.out.println("   ____________________________________________________________");
        System.out.println("    added: " + task);
        System.out.println("   ____________________________________________________________");
    }

    public static void list() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + ". " + store[i]);
        }
        System.out.println("   ____________________________________________________________");
    }

    public static void mark(String input) {
        int index = getIndex(input);
        if (checkValid(index)) {
            store[index - 1].markDone();
            System.out.println("   ____________________________________________________________");
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    public static void unmark(String input) {
        int index = getIndex(input);
        if (checkValid(index)) {
            store[index - 1].markNotDone();
            System.out.println("   ____________________________________________________________");
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    private static int getIndex(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static boolean checkValid(int index) {
        return index > 0 && index <= count;
    }
}

class Task {
    protected String task;
    protected boolean marked;

    public Task(String task) {
        this.task = task;
        this.marked = false;
    }

    public void markDone() {
        marked = true;
    }

    public void markNotDone() {
        marked = false;
    }

    public String mark() {
        return (marked ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return mark() + " " + task;
    }
}*/


/*
Level 2
import java.util.Scanner;
public class Duke {
    private static final String[] store = new String[100];
    private static int count = 0;

    public static void main(String[] args) {
        dialogue();

        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();
            check(inputs);

        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm QuantumBot");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void check(String input) {
        if (input.equalsIgnoreCase("bye")) {

        } else if (input.startsWith("list")) {
            list();

        } else {
            add(input);

        }
    }

    public static void add(String task) {
            store[count++] = task;

            System.out.println("   ____________________________________________________________");
            System.out.println("    added: " + task);
            System.out.println("   ____________________________________________________________");
    }

    public static void list() {

        System.out.println("   ____________________________________________________________");

        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + ". " + store[i]);
        }
        System.out.println("   ____________________________________________________________");
    }
}*/


/*
Level 1
public class Duke {
    public static void main(String[] args) {
        dialogue();

        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();
            if (!inputs.equalsIgnoreCase("bye")) {
                echo(inputs);
            }
        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {
        System.out.println("   ____________________________________________________________");
        System.out.println("   Hello! I'm QuantumBot");
        System.out.println("   What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void echo(String input) {
        System.out.println("   ____________________________________________________________");
        System.out.println("   " + input);
        System.out.println("   ____________________________________________________________");
    }
}*/


/*Level 0
public class Duke {

    public static void main(String[] args) {

            /*String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dialogue();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void dialogue() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BotYue");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}*/
