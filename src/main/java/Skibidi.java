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

    public void echo() {
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
            System.out.print(String.format("%d. %s", i+1, this.list[i]));
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
            System.out.println("added: " + in);
            this.list[items] = new Task(in);
            this.items++;
        }
    }
}
