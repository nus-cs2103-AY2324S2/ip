import java.util.Scanner;

public class Skibidi {
    public static String logo = " ____  _  _____ ____ ___ ____ ___ \n"+
            "/ ___|| |/ /_ _| __ )_ _|  _ \\_ _|\n"+
            "\\___ \\| ' / | ||  _ \\| || | | | | \n" +
            " ___) | . \\ | || |_) | || |_| | | \n" +
            "|____/|_|\\_\\___|____/___|____/___|";

    private String[] list = new String[100];

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
        int items = 0;
        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) break;
            else if (in.equals("list")) this.printList(items);
            else {
                System.out.println("added: " + in);
                this.list[items] = in;
                items++;
            }
            this.printLine();
        }
    }

    public void printList(int items) {
        for (int i = 0; i < items; i++) {
            System.out.print(String.format("%d. %s\n", i+1, this.list[i]));
        }
    }
}
