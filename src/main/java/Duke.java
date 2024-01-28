import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        String input;
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        List<String> stringList;
        String command, trail;
        boolean isExit = false;

        while (!isExit){
            try {
                // Take user input
                input = scanner.nextLine();  // Read user input

                stringList = parser.parse(input);
                command = stringList.get(0);
                trail = stringList.get(1);

                Options choice = optionType(command);

                ui.nextCommand(choice, tasks, trail, storage);

                if (choice.equals(optionType("bye"))) {
                    isExit = true;
                }

            } catch (DukeException de) {
                String text = "\t____________________________________________________________\n"
                        + "\t" + de +"\n"
                        + "\t____________________________________________________________\n";
                System.out.println(text);
            } catch (NumberFormatException nfe) {
                String text = "\t____________________________________________________________\n"
                        + "\tInvalid number, please enter a valid number.\n"
                        + "\t____________________________________________________________\n";
                System.out.println(text);
            } catch (IndexOutOfBoundsException oobe) {
                String text;
                if (tasks.taskList.size() == 0) {
                    text = "\t____________________________________________________________\n"
                            + "\tNo tasks found. Please add a task first!\n"
                            + "\t____________________________________________________________\n";
                } else {
                    text = "\t____________________________________________________________\n"
                            + "\tInvalid number, please enter a valid number between 1 and " + tasks.taskList.size() + ".\n"
                            + "\t____________________________________________________________\n";
                }
                System.out.println(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DateTimeParseException dtpe) {
                String text;
                text = "\t____________________________________________________________\n"
                        + "\tPlease enter a datetime format of yyyy-mm-dd.\n"
                        + "\t____________________________________________________________\n";
                System.out.println(text);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

    public static Options optionType(String option) {
        switch (option) {
            case "bye":
                return Options.bye;
            case "list":
                return Options.list;
            case "delete":
                return Options.delete;
            case "mark":
                return Options.mark;
            case "unmark":
                return Options.unmark;
            case "todo":
                return Options.todo;
            case "deadline":
                return Options.deadline;
            case "event":
                return Options.event;
            case "save":
                return Options.save;
            default:
                return Options.error;
        }
    }
}



enum Options {
    bye, list, delete, mark, unmark, todo, deadline, event, error, save
}
