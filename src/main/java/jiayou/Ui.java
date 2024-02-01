package jiayou;

import java.util.Scanner;
import jiayou.task.*;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Parser parser = new Parser();
    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void greet() {
        System.out.println(Ui.LINE);
        System.out.println("Hello! I'm Jiayou.");
        System.out.println("What can I do for you?");
        System.out.println(Ui.LINE);
        chat();
    }

    public void chat() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println(Ui.LINE);

            if (input.equals("bye")) { break;}
            this.parser.parse(this.tasks, input);

            System.out.println(Ui.LINE);
        }
        sc.close();
        bye();
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Ui.LINE);
    }
}
