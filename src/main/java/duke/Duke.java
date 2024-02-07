package duke;

import duke.command.Parser;
import duke.command.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.Objects;
import java.util.Scanner;

import java.time.LocalDate;

/**
 * Represents the main Duke application class responsible for running the program.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Runs the Duke application.
     */
    public void run() {

        storage.getFileContents(tasks);

        Scanner scanner = new Scanner(System.in);

        ui.showWelcomeMessage();

        while (true) {
            try {
                Task[] taskArr = tasks.getTasks();
                int counter = tasks.getCounter();

                String input = scanner.nextLine();

                ui.showMessage(input);
                Object[] parseRes = Parser.parseCommand(input);
                String type = (String) parseRes[0];

                if (parseRes.length == 1) {
                    if (Objects.equals(type, "bye")) {
                        ui.showByeMessage();
                        break;
                    }

                    if (Objects.equals(type, "list")) {
                        ui.showTaskList(taskArr, counter);
                        continue;
                    }
                }

                if (parseRes.length > 1) {
                    if (Objects.equals(type, "mark")) {
                        int taskNumber = (int) parseRes[1];
                        if (taskNumber >= 1 && taskNumber <= counter) {
                            tasks.markTaskAsDone(taskNumber);
                            ui.showMarkTaskDoneMessage(taskArr[taskNumber]);
                        } else {
                            ui.showMessage("UH OH! Invalid task number, please provide a valid task number!");
                        }
                        continue;
                    }

                    if (Objects.equals(type, "delete")) {
                        int taskNumber = (int) parseRes[1];
                        if (taskNumber >= 1 && taskNumber <= counter) {
                            ui.showRemoveTaskMessage(taskArr[taskNumber - 1], counter);
                            tasks.deleteTask(counter - 1);
                        } else {
                            throw new DukeException("UH OH! Invalid task number, please provide a valid task number!");
                        }
                        continue;
                    }

                    if (Objects.equals(type, "todo")) {
                        String taskDesc = (String) parseRes[1];

                        Task t = new Todo(taskDesc);
                        tasks.addTask(t);
                        ui.showAddTaskMessage(t, counter);

                    } else if (Objects.equals(type, "deadline")) {
                        String taskDesc = (String) parseRes[1];
                        LocalDate by = (LocalDate) parseRes[2];

                        Task t = new Deadline(taskDesc, by);
                        tasks.addTask(t);
                        ui.showAddTaskMessage(t, counter);

                    } else if (Objects.equals(type, "event")) {
                        String taskDesc = (String) parseRes[1];
                        LocalDate fromDate = (LocalDate) parseRes[2];
                        String fromTime = (String) parseRes[3];
                        LocalDate toDate = (LocalDate) parseRes[4];
                        String toTime = (String) parseRes[5];

                        Task t = new Event(taskDesc, fromDate, fromTime, toDate, toTime);
                        tasks.addTask(t);
                        ui.showAddTaskMessage(t, counter);

                    } else {
                        throw new DukeException("UH OH! I don't understand what you mean.. sorry D:");
                    }
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        storage.writeToFile(tasks);
    }

    /**
     * The entry point for running the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

}

