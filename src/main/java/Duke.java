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
}








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
