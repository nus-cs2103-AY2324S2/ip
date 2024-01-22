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
}















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
