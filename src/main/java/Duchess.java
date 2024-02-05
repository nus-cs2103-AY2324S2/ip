import java.util.Scanner;
import java.io.IOException;

import duchess.tasks.Deadline;
import duchess.tasks.Event;
import duchess.tasks.Task;
import duchess.tasks.ToDo;

import duchess.util.DuchessException;
import duchess.util.Parser;
import duchess.util.Storage;
import duchess.util.TaskList;
import duchess.util.Ui;

public class Duchess {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Create new Duchess object.
     * Create new Ui, Storage, Parser, and TaskList objects.
     */
    public Duchess() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DuchessException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run Duchess program.
     */
    public void run() {
        String lineBreak = "\t\t------------------------------------------";

        System.out.println(lineBreak);

        ui.printMenu();
        System.out.println(lineBreak);

        Scanner scan = new Scanner( System.in );
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            try {
                int action = parser.getAction(input);
                if (action == 1) {  // List
                    System.out.println(lineBreak);
                    tasks.printTaskList();
                    System.out.println(lineBreak);
                } else if (action == 2) {   // Delete task
                    try {
                        int itemIndex = Character.getNumericValue(input.charAt(7));
                        tasks.deleteTask(itemIndex);
                    } catch (IndexOutOfBoundsException f) {
                        System.out.println(lineBreak);
                        System.out.println("\t\tPlease specify a valid task number.");
                        System.out.println(lineBreak);
                        input = "list";
                        continue;
                    }
                } else if (action == 3) {   // Create task: To Do
                    System.out.println(lineBreak);
                    try {
                        String[] toDoDetails = parser.getToDoDetails(input);
                        Task t = new ToDo(toDoDetails[1]);
                        tasks.createTask(t);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("\t\tIncorrect input." +
                                "\n\t\tTo create a 'To Do': todo <description>");
                        System.out.println(lineBreak);
                    }
                } else if (action == 4) {   // Create task: duchess.tasks.Deadline
                    System.out.println(lineBreak);
                    try {
                        String[] deadlineDetails = parser.getDeadlineDetails(input);
                        Task t = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        tasks.createTask(t);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("\t\tIncorrect input. " +
                                "\n\t\tTo create a 'Deadline': deadline <description> /by <by>");
                        System.out.println(lineBreak);
                    }
                } else if (action == 5) {   // Create task: duchess.tasks.Event
                    System.out.println(lineBreak);
                    try {
                        String[] eventDetails = parser.getEventDetails(input);
                        Task t = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                        System.out.println(eventDetails[0]);
                        tasks.createTask(t);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("\t\tIncorrect input. " +
                                "\n\t\tTo create an 'Event': event <description> /from <from> /to <to>");
                        System.out.println(lineBreak);
                    }
                } else if (action == 6) {   // Unmark task
                    try {
                        int itemIndex = Character.getNumericValue(input.charAt(7));
                        tasks.unmarkTask(itemIndex);
                    } catch (IndexOutOfBoundsException f) {
                        System.out.println(lineBreak);
                        System.out.println("\t\tOOPS!!! Please specify a valid task number.");
                        System.out.println(lineBreak);
                        input = "list";
                        continue;
                    }
                } else if (action == 7) {   // Mark task
                    try {
                        int itemIndex = Character.getNumericValue(input.charAt(5));
                        tasks.markTask(itemIndex);
                    } catch (IndexOutOfBoundsException f) {
                        System.out.println(lineBreak);
                        System.out.println("\t\tOOPS!!! Please specify a valid task number.");
                        System.out.println(lineBreak);
                        input = "list";
                        continue;
                    }
                }
            } catch (DuchessException e) {
                System.out.println(lineBreak);
                System.out.println("\t\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                ui.printMenu();
                System.out.println(lineBreak);
            }
            input = scan.nextLine();
        }

        try {
            storage.save(tasks.retrieveTaskList());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ui.printExit();
    }

    public static void main(String[] args) {

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        new Duchess().run();
    }
}