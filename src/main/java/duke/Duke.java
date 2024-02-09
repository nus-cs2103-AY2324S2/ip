package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] fullCommand = Parser.parse(ui.readCommand());
                String command = fullCommand[0];

                if (command.equals("bye")) {
                    ui.showBye();
                    isExit = true;
                    break;
                } else if (command.equals("list")) {
                    ui.showList(tasks);
                } else if (command.equals("mark") || command.equals("unmark") || command.equals("todo")
                        || command.equals("deadline") || command.equals("event") || command.equals("delete")){
                    if (fullCommand.length > 1) {
                        if (command.equals("mark")) {
                            int indexToMark = Integer.parseInt(fullCommand[1]) - 1;
                            Task task = tasks.getTasks().get(indexToMark);
                            task.markComplete();
                            ui.showTask(" Nice! I've marked this task as done:", task);
                            storage.saveToFile(tasks.getTasks());
                        } else if (command.equals("unmark")) {
                            int indexToUnmark = Integer.parseInt(fullCommand[1]) - 1;
                            Task task = tasks.getTasks().get(indexToUnmark);
                            task.markIncomplete();
                            ui.showTask(" OK, I've marked this task as not done yet:", task);
                            storage.saveToFile(tasks.getTasks());
                        } else if (command.equals("todo")) {
                            Task task = new ToDo(fullCommand[1]);
                            tasks.addTask(task);
                            ui.showTaskWithNum(" Got it. I've added this task:\n  ", task, tasks);
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else if (command.equals("deadline")) {
                            Task task = new Deadline(fullCommand[1]);
                            tasks.addTask(task);
                            ui.showTaskWithNum("Got it. I've added this task:\n", task, tasks);
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else if (command.equals("event")) {
                            Task task = new Event(fullCommand[1]);
                            tasks.addTask(task);
                            ui.showTaskWithNum("Got it. I've added this task:\n", task, tasks);
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else {
                            int deleteIndex = Integer.parseInt(fullCommand[1]) - 1;
                            Task deletedTask = tasks.deleteTask(deleteIndex);
                            ui.showTaskWithNum(" Noted. I've removed this task:\n", deletedTask, tasks);

                            storage.saveToFile(tasks.getTasks());
                        }
                    } else {
                        throw new DukeException("____________________________________________________________\n" +
                                " OOPS! Your Only Friend cannot take in an empty " + command +
                                "\n Make sure " + command + "has a description!\n" +
                                "____________________________________________________________\n");
                    }

                } else {
                    throw new DukeException(" OOPS! Turns out Your Only Friend does not know what that is :(\n");
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
