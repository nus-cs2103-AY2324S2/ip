package duke;

import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Ui {
    public static String logo = " ____  _  _____ ____ ___ ____ ___ \n" +
            "/ ___|| |/ /_ _| __ )_ _|  _ \\_ _|\n" +
            "\\___ \\| ' / | ||  _ \\| || | | | | \n" +
            " ___) | . \\ | || |_) | || |_| | | \n" +
            "|____/|_|\\_\\___|____/___|____/___|";

    private List<Task> list = new ArrayList<>();

    public static void printLine() {
        System.out.println("\n-------------------------------------------------------------------\n");
    }

    public void greet() {
        System.out.println(Ui.logo);
        printLine();
        System.out.println("Hello! I'm Skibidi!\nWhat can I do for you?");
        printLine();
    }

    public void bye() {
        System.out.println("Bye! Hope to see you again soon!");
        printLine();
    }

    public void chat(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        String in = null;
        Parser p = new Parser();

        while (!(tasks == null)) {
            in = sc.nextLine();

            tasks = p.parse(in, tasks, storage); // Parse will return null if command is "bye"

            printLine();
        }
    }

}