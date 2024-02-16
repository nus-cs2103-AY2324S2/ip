package youdon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Parses user input and performs corresponding actions.
 * This class handles parsing user input commands and executing actions accordingly,
 * such as adding tasks, marking tasks as done, and deleting tasks.
 */
public class Parser {

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a new instance of the Parser class with the specified UI, task list, and storage.
     *
     * @param ui      The user interface for displaying messages.
     * @param tasks   The list of tasks to perform actions on.
     * @param storage The storage handler for saving and loading tasks.
     */
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Parses user input and executes corresponding actions until an empty input is provided.
     */
    public void parse() {
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();

        while (!(data.isEmpty())) {
            // try-catch block for exceptions
            try {
                YoudonException.EmptyDescException.detectMissingDesc(data);
                YoudonException.InvalidCommandException.detectInvalidCommand(data);
            } catch (YoudonException.EmptyDescException | YoudonException.InvalidCommandException e) {

                // print out error message
                ui.printYoudonErrorMsg(e.getMessage());

                // update data to next input
                data = scanNextInput(input);
            }

            // if input == "bye", print chatbot bye message
            if (data.equals("bye")) {
                ui.printByeMsg();
                break;
            }

            // if input == "list", return tasklist
            if (data.equals("list")) {
                ui.printTaskList(tasks);
                // wait for next input
                data = scanNextInput(input);
                continue;
            }

            if (data.contains(" ")) {
                // if input data has 2 parts, split into command & task number
                String[] parts = data.split(" ", 2);
                String command = parts[0];
                String task = parts[1];

                // if input == "find", find all tasks with the given word
                if (command.equals("find")) {
                    TaskList foundList = new TaskList();
                    for (int i = 0; i < tasks.size(); i++) {
                        Task currTask = tasks.get(i);
                        if (currTask.toString().contains(task)) {
                            foundList.add(currTask);
                        }
                    }
                    ui.printTaskList(foundList);
                    // wait for next input
                    data = scanNextInput(input);
                    continue;
                }

                // if input == "mark", mark the specified task as done
                if (command.equals("mark")) {
                    // mark task as done in array
                    int taskNumber = Integer.parseInt(task);
                    tasks.get(taskNumber - 1).isDone = true;

                    // print out changes
                    ui.printMarkMsg(tasks, taskNumber);

                    // save and wait for next input
                    try {
                        storage.saveData(tasks);
                        System.out.println("Tasklist saved!");
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    data = scanNextInput(input);
                    continue;
                }

                // if input == "unmark", mark the specified task as undone
                if (command.equals("unmark")) {
                    // mark task as undone in array
                    int taskNumber = Integer.parseInt(task);
                    tasks.get(taskNumber - 1).isDone = false;

                    // print out changes
                    ui.printUnmarkMsg(tasks, taskNumber);

                    // save and wait for next input
                    try {
                        storage.saveData(tasks);
                        System.out.println("Tasklist saved!");
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    data = scanNextInput(input);
                    continue;
                }

                // if input == "delete", delete the specified task
                if (command.equals("delete")) {
                    int taskNumber = Integer.parseInt(task);

                    // print out changes
                    ui.printDeleteMsg(tasks, taskNumber);

                    // delete task and fix indexing
                    tasks.remove(taskNumber - 1);

                    // save and wait for next input
                    try {
                        storage.saveData(tasks);
                        System.out.println("Tasklist saved!");
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    data = scanNextInput(input);
                    continue;
                }

                // differentiate between type of tasks and add to tasklist
                if (command.equals("todo")) {

                    // add to tasklist
                    tasks.add(new Todo(task));

                    // print out task added
                    ui.printTaskAdded(tasks);
                    System.out.println("Tasklist saved!");
                }

                // if task has "by", split into task and deadline
                if (task.contains("/by ")) {
                    String[] chunks = task.split("/by ");
                    if (command.equals("deadline")) {

                        // add to tasklist
                        String taskDesc = chunks[0];
                        String deadline = chunks[1];
                        try {
                            // attempt to parse the string into a LocalDateTime object
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                            LocalDateTime dateTimeDeadline = LocalDateTime.parse(deadline, formatter);
                            tasks.add(new Deadline(taskDesc, dateTimeDeadline));

                            // print out task added
                            ui.printTaskAdded(tasks);
                            System.out.println("Tasklist saved!");
                        } catch (Exception e) {
                            // handle the exception if the date or time is not the correct format
                            System.out.println("Oh no! That's not a correct date or time format!");
                        }
                    }
                }

                if (task.contains("/from ")) {
                    String[] sections = task.split("/from | /to ");
                    if (command.equals("event")) {
                        // add to tasklist
                        String taskDesc = sections[0];
                        String start = sections[1];
                        String end = sections[2];
                        try {
                            // attempt to parse the strings into a LocalDateTime object
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                            LocalDateTime dateTimeStart = LocalDateTime.parse(start, formatter);
                            LocalDateTime dateTimeEnd = LocalDateTime.parse(end, formatter);
                            tasks.add(new Event(taskDesc, dateTimeStart, dateTimeEnd));

                            // print out task added
                            ui.printTaskAdded(tasks);
                            System.out.println("Tasklist saved!");
                        } catch (Exception e) {
                            // handle the exception if the date or time is not the correct format
                            System.out.println("Oh no! That's not a correct date or time format!");
                        }
                    }
                }
                // save and wait for next input
                try {
                    storage.saveData(tasks);
                } catch (IOException e) {
                    System.out.println("Error!" + e.getMessage());
                }

                data = scanNextInput(input);
            }
        }
    }

    private String scanNextInput(Scanner input) {
        String data = "";
        if (input.hasNextLine()) {
            data = input.nextLine();
        }
        return data;
    }
}
