import java.util.Scanner;
import commands.Command;
import commands.ListCommand;
import commands.MarkCommand;

public class Parser {
    private Ui ui;
    public Parser() {
        this.ui = new Ui();
    }

    public Command parseCommand(String command) {
        String[] split = command.split("\\s+", 2);
        if (split[0].toLowerCase().equals("list")) {
            return new ListCommand();
        } else if (split[0].toLowerCase().equals("mark")) {
            return new MarkCommand(false);
        } else if (split[0].toLowerCase().equals("unmark")) {
            return new MarkCommand(true);
            /*try {
                unmarkTask(split, list);
            } catch (InvalidCommandException e) {
                System.out.println("    ____________________________________________________________");
                System.out.printf("      %s\n", e.getMessage());
                System.out.println("    ____________________________________________________________");
            }*/
        } else if (split[0].toLowerCase().equals("delete")) {
            return new DeleteCommand();
                /*try {
                    deleteTask(split, list);
                } catch (InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }*/
        } else {
            return new CreateTaskCommand();
                /*try {
                    createTask(split, list);
                } catch (InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }*/
            }
        }
    }
}

/*

 */
