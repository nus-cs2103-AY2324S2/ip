import java.util.Scanner;

public class Skibidi {
    public static String logo = " ____  _  _____ ____ ___ ____ ___ \n"+
            "/ ___|| |/ /_ _| __ )_ _|  _ \\_ _|\n"+
            "\\___ \\| ' / | ||  _ \\| || | | | | \n" +
            " ___) | . \\ | || |_) | || |_| | | \n" +
            "|____/|_|\\_\\___|____/___|____/___|";

    private Task[] list = new Task[100];
    private int items = 0;

    public void printLine() {
        System.out.println("\n-------------------------------------------------------------------\n");
    }

    public void greet() {
        System.out.println(Skibidi.logo);
        this.printLine();
        System.out.println("Hello! I'm Skibidi!\nWhat can I do for you?");
        this.printLine();
    }

    public void bye() {
        System.out.println("Bye! Hope to see you again soon!");
        this.printLine();
    }

    public void chat() {
        Scanner sc = new Scanner(System.in);
        String in = null;
        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) break;

            inputComprehension(in);

            this.printLine();
        }
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.items; i++) {
            System.out.printf("%d. %s", i+1, this.list[i]);
        }
    }

    private void inputComprehension(String in) {
        if (in.equals("list")) {
            printList();

        // Marking a task as done
        } else if (in.startsWith("mark")) {
            int t;
            try {
                t = Integer.parseInt(in.substring(5));
                if (t > this.items || t < 1) {
                    System.out.println("Sorry, index out of range!");
                } else {
                    this.list[t-1].markAsDone();
                    System.out.print("Nice! I've marked this task as done:\n  ");
                    System.out.println(this.list[t-1]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number!");
            }

        // Marking a task as not done
        } else if (in.startsWith("unmark")) {
            try {
                int t = Integer.parseInt(in.substring(7));
                if (t > this.items || t < 1) {
                    System.out.println("Sorry, index out of range!");
                } else {
                    this.list[t-1].markAsNotDone();
                    System.out.print("OK, I've marked this task as not done yet:\n  ");
                    System.out.println(this.list[t-1]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number!");
            }

        } else {
            addTask(in);
        }
    }
// /to mon
// 0123456
    public void addTask(String s) {
        // Todo_
        if (s.startsWith("todo")) {
            this.list[items] = new Todo(s.substring(5));
        // Deadline
        } else if (s.startsWith("deadline")) {
            String n = s.substring(9, s.indexOf('/')-1);
            String t = s.substring(s.indexOf('/') + 4);
            this.list[items] = new Deadline(n, t);
        // Event
        } else if (s.startsWith("event")) {
            String n = s.substring(6, s.indexOf('/') - 1);
            String f = s.substring(s.indexOf('/') + 6, s.indexOf('/', s.indexOf('/') + 1) - 1);
            String t = s.substring(s.indexOf('/', s.indexOf('/') + 1) + 4);
            this.list[items] = new Event(n, f, t);
        } else {
            System.out.println("This is not a valid input!!!");
        }

        System.out.print("Got it added this task:\n  " + this.list[items]);
        this.items++;
        System.out.printf("Now you have %d tasks in the list.", items);
    }
}
