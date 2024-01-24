import java.util.Scanner;

public class Skibidi {
    public static String logo = " ____  _  _____ ____ ___ ____ ___ \n"+
            "/ ___|| |/ /_ _| __ )_ _|  _ \\_ _|\n"+
            "\\___ \\| ' / | ||  _ \\| || | | | | \n" +
            " ___) | . \\ | || |_) | || |_| | | \n" +
            "|____/|_|\\_\\___|____/___|____/___|\n";

    public void printLine() {
        System.out.println("-------------------------------------------------------------------\n");
    }

    public void greet() {
        System.out.println(Skibidi.logo);
        this.printLine();
        System.out.println("Hello! I'm Skibidi!\nWhat can I do for you?\n");
        this.printLine();
    }

    public void bye() {
        System.out.println("Bye! Hope to see you again soon!\n");
        this.printLine();
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String in = null;
        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) break;
            System.out.println(in + '\n');
            this.printLine();
        }
    }
}
