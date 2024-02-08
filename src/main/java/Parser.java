import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

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
                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
            }

            // if input == "bye", print chatbot bye message
            if (data.equals("bye")) {
                ui.printByeMsg();
                break;
            }

            // if input == "list", return tasklist
            if (data.equals("list")) {
                ui.printTaskList(taskList);
                // wait for next input
                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
                continue;
            }

            if (data.contains(" ")) {
                // if input data has 2 parts, split into command & task number
                String[] parts = data.split(" ", 2);
                String command = parts[0];
                String task = parts[1];

                // if input == "mark", mark the specified task as done
                if (command.equals("mark")) {
                    // mark task as done in array
                    int taskNumber = Integer.parseInt(task);
                    taskList.get(taskNumber - 1).isDone = true;

                    // print out changes
                    ui.printMarkMsg(taskList, taskNumber);
                    // save and wait for next input
                    try {
                        storage.saveData(taskList);
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // if input == "unmark", mark the specified task as undone
                if (command.equals("unmark")) {
                    // mark task as undone in array
                    int taskNumber = Integer.parseInt(task);
                    taskList.get(taskNumber - 1).isDone = false;

                    // print out changes
                    ui.printUnmarkMsg(taskList, taskNumber);
                    // save and wait for next input
                    try {
                        storage.saveData(taskList);
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // if input == "delete", delete the specified task
                if (command.equals("delete")) {
                    int taskNumber = Integer.parseInt(task);
                    // print out changes
                    ui.printDeleteMsg(taskList, taskNumber);
                    // delete task and fix indexing
                    taskList.remove(taskNumber - 1);
                    // save and wait for next input
                    try {
                        storage.saveData(taskList);
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // differentiate between type of tasks and add to tasklist
                if (command.equals("todo")) {
                    // add to tasklist
                    taskList.add(new Todo(task));
                    // print out task added
                    ui.printTaskAdded(taskList);
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
                            taskList.add(new Deadline(taskDesc, dateTimeDeadline));
                            // print out task added
                            ui.printTaskAdded(taskList);
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
                            taskList.add(new Event(taskDesc, dateTimeStart, dateTimeEnd));
                            // print out task added
                            ui.printTaskAdded(taskList);
                        } catch (Exception e) {
                            // handle the exception if the date or time is not the correct format
                            System.out.println("Oh no! That's not a correct date or time format!");
                        }
                    }
                }
                // save and wait for next input
                try {
                    storage.saveData(taskList);
                } catch (IOException e) {
                    System.out.println("Error!" + e.getMessage());
                }

                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
            }
        }
    }
}
