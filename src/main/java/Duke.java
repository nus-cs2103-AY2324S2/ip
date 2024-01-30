import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Duke {
    Storage storage;
    Ui ui;
    TaskList taskList;

    String command = "";
    String secondaryInput = "";
    Scanner scanner1;
    boolean isEnded = false;

    String[] commandList = new String[] {"bye", "mark", "unmark", "todo", "deadline", "event", "list"};
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    public void exit() {
        this.ui.bye();
        this.scanner1.close();
        this.storage.saveFile(this.taskList);
        this.isEnded = true;
        horizontalLines();
    }

    public void horizontalLines() {
        System.out.println("\n    ____________________________________________________________");
    }

    public void input() {
        boolean isCommandValid = false;
        this.command ="";
        this.secondaryInput ="";
        String commandInput = scanner1.nextLine();

        if (commandInput.equals("bye")) {
            this.command = commandInput;
            isCommandValid = true;
            exit();
        } else if (commandInput.equals("list")) {
            this.command = commandInput;
            isCommandValid = true;
            this.taskList.listTask();
        } else {
            String[] inputSplit = commandInput.split(" ", 2);
            this.command = inputSplit[0];

            try {
                if (this.command.equals("mark")) {
                    isCommandValid = true;
                    this.taskList.markTask(Integer.valueOf(inputSplit[1]) - 1);
                } else if (this.command.equals("unmark")) {
                    isCommandValid = true;
                    this.taskList.unmarkTask(Integer.valueOf((inputSplit[1])) - 1);
                } else if (this.command.equals("delete")) {
                    isCommandValid = true;
                    this.taskList.deleteTask(Integer.valueOf((inputSplit[1])) - 1);
                }
            } catch(IndexOutOfBoundsException e) {
                if (this.taskList.size() == 0) {
                    System.out.println("\tYou have no task to mark,unmark or delete!");
                } else {
                    System.out.println("\tYou only have " + taskList.size() +" tasks!");
                    System.out.println("\tSelect a number from 1 to " + taskList.size() + ".");
                }
            } catch(NumberFormatException e) {
                System.out.println("\tPlease input a number.");
            }

            try {
                if ((this.command.equals("todo")) || (this.command.equals("deadline"))
                        || (this.command.equals("event"))) {
                    this.secondaryInput = inputSplit[1];
                    isCommandValid = true;
                    this.taskList.addTask(this.command, this.secondaryInput);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (this.command.equals("deadline")) {
                    System.out.println("\tPlease input a date or time with a / in front.");
                } else if (this.command.equals("event")) {
                    System.out.println("\tPlease input a start and end time or date with a / in front of both periods.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("\tInvalid DateTime Format. Please input as follows:");
                System.out.println("\tdd-mm-yyyy hh:mm");
            }
        }

        if (!isCommandValid) {
            System.out.println("\tNo such command or too many parameters. Please try again");
        }
    }

    public void run() {
        try {
            this.taskList = storage.loadFile();
        } catch (IOException e) {
            System.out.println("Run failed.");
        }
    }

    public static void main(String[] args) {
        Duke Duke1 = new Duke("data/tasks.txt");
        Duke1.run();
        Duke1.scanner1 = new Scanner(System.in);
        Duke1.horizontalLines();
        Duke1.ui.greeting();

        while (!Duke1.isEnded) {
            Duke1.horizontalLines();
            Duke1.input();
            //Duke1.horizontalLines();
        }
    }
}
