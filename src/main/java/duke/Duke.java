package duke;

import command.Command;
import exception.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("    OOPS!!! ");
            } catch (Exception e) {
                System.out.println(Ui.INDENT_SEPERATOR + "\n    OOPS!!! Something went wrong D:\n"
                        + Ui.INDENT_SEPERATOR);
            } finally {
                storage.writeTasks(tasks);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
