package demon;

import java.nio.file.NoSuchFileException;

import java.io.IOException;

import java.util.Scanner;

import java.time.format.DateTimeParseException;

/**
 * A chatbot that allows user to add, delete, and modify tasks or reminders.
 */
public class Demon {
    private TaskList tasks;
    private Ui ui;
    String filePath = "src/main/taskList.txt";

    /**
     * Initializes the bot and load tasks saved in taskList.txt file
     * if there is any previous tasks.
     *
     * @param filePath The relative path to the taskList.txt file
     */
    public Demon(String filePath) {
        ui = new Ui();
        final Storage STORAGE = new Storage(filePath);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (NoSuchFileException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui = new Ui();
        Commands command = new Commands(filePath);
        ui.welcomeMessage();
        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            ui.inputMessage(input);
            if (input.equalsIgnoreCase("list")) {
                command.list(this.tasks);
                Ui.promptNext();
                input = sc.nextLine();
            } else if (input.split(" ",2)[0].equalsIgnoreCase("unmark")) {
                try {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    command.unmark(this.tasks, num);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    Ui.outOfBoundsIndex(e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("mark")) {
                try {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    command.mark(this.tasks, num);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    Ui.outOfBoundsIndex(e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("deadline")) {
                try {
                    command.addDeadline(this.tasks, input);
                } catch (NoTimingException | EmptyDescriptionException | IOException | DateTimeParseException e) {
                    System.err.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("todo")) {
                try {
                    command.addToDo(this.tasks, input);
                    Ui.promptNext();
                } catch (EmptyDescriptionException | IOException e) {
                    System.err.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("event")) {
                try {
                    command.addEvent(this.tasks, input);
                    Ui.promptNext();
                } catch (NoTimingException | EmptyDescriptionException | IOException | DateTimeParseException e) {
                    System.err.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("delete")) {
                try {
                    command.delete(this.tasks, input);
                    Ui.promptNext();
                } catch (EmptyDescriptionException | IndexOutOfBoundsException e) {
                    System.err.println("Error -> " + e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("find")) {
                try {
                    command.findTask(this.tasks, input);
                    Ui.promptNext();
                } catch (EmptyDescriptionException e) {
                    System.err.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }
            } else {
                Ui.inValidCommand();
                input = sc.nextLine();
            }
        }
        System.out.println("Entered: '" + input + "'");
        Ui.printDivider();
        Ui.exitMessage();
        Ui.printDivider();
    }

    public static void main(String[] args) {
        new Demon("src/main/taskList.txt").run();
    }
}
