import java.util.Scanner;

public class Duke {

    public enum TaskType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static void main(String[] args) {

        // Ui
        Ui ui = new Ui();

        // Storage
        Storage storage = new Storage();

        // Input reader
        Scanner inputReader = new Scanner(System.in);

        // Greet
        ui.greet();

        // Perform task
        while (true) {
            String task = inputReader.nextLine();
            String[] splitedTask = task.split(" ");
            TaskType taskType = TaskType.UNKNOWN;

            // Empty command handler
            try {
                taskType = TaskType.valueOf(splitedTask[0].toUpperCase());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.print("Sorry, we are not sync enough to communicate through empty command.");
                continue;
            } catch (IllegalArgumentException e) {
                ui.print("Syntax error, unknown command.");
                continue;
            }

            boolean isEnd = false;

            try {
                switch (taskType) {
                    case BYE: {
                        // Incorrect command syntax handler
                        if (splitedTask.length > 1) {
                            throw new DukeException("bye");
                        }

                        isEnd = true;
                        break;
                    }
                    case LIST: {
                        // Incorrect command syntax handler
                        if (splitedTask.length > 1) {
                            throw new DukeException("list");
                        }

                        ui.list(storage);
                        break;
                    }
                    case MARK: {
                        // Incorrect command syntax handler
                        if (splitedTask.length != 2) {
                            throw new DukeException("mark");
                        }
                        int index = 0;
                        try {
                            index = Integer.parseInt(splitedTask[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("mark");
                        }

                        // Index out of bound handler
                        if (index >= storage.getItems().size()) {
                            ui.print("The index of task cannot be larger than number of task.");
                            continue;
                        } else if (index < 0) {
                            ui.print("The index of task must be positive integer.");
                            continue;
                        }

                        storage.markDone(index);
                        ui.mark(storage.getItem(index));
                        break;
                    }
                    case UNMARK: {
                        // Incorrect command syntax handler
                        if (splitedTask.length != 2) {
                            throw new DukeException("unmark");
                        }
                        int index = 0;
                        try {
                            index = Integer.parseInt(splitedTask[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("unmark");
                        }

                        // Index out of bound handler
                        if (index >= storage.getItems().size()) {
                            ui.print("The index of task cannot be larger than number of task.");
                            continue;
                        } else if (index < 0) {
                            ui.print("The index of task must be positive integer.");
                            continue;
                        }

                        storage.unmarkDone(index);
                        ui.mark(storage.getItem(index));
                        break;
                    }
                    case TODO: {
                        // Incorrect command syntax handler
                        if (splitedTask.length == 1) {
                            throw new DukeException("todo");
                        }

                        Task newTask = new Todo(task);
                        storage.add(newTask);
                        ui.add(newTask, storage);
                        break;
                    }
                    case DEADLINE: {
                        // Incorrect command syntax handler
                        if (splitedTask.length == 1) {
                            throw new DukeException("deadline");
                        }

                        // Split the string with /by
                        String[] splitedBy = task.split(" /by ");

                        // Incorrect command syntax handler
                        if (splitedBy.length != 2 || splitedBy[0].length() <= 9
                                || splitedBy[0].substring(9).isBlank()
                                || splitedBy[1].isBlank()) {
                            throw new DukeException("deadline");
                        }

                        // Create deadline task
                        Task newTask = new Deadline(splitedBy[0].substring(9), splitedBy[1]);
                        storage.add(newTask);
                        ui.add(newTask, storage);
                        break;
                    }
                    case EVENT: {
                        // Incorrect command syntax handler
                        if (splitedTask.length == 1) {
                            throw new DukeException("event");
                        }

                        // Split the string with /from
                        String[] splitedFrom = task.split(" /from ");

                        // Incorrect command syntax handler
                        if (splitedFrom.length != 2 || splitedFrom[0].length() <= 6
                                || splitedFrom[0].substring(6).isBlank()
                                || splitedFrom[1].isBlank()) {
                            throw new DukeException("deadline");
                        }

                        // Split the string with /to
                        String[] splitedTo = splitedFrom[1].split(" /to ");

                        // Incorrect command syntax handler
                        if (splitedTo.length != 2 || splitedTo[0].isBlank() || splitedTo[1].isBlank()) {
                            throw new DukeException("deadline");
                        }

                        // Create event task
                        Task newTask = new Event(splitedFrom[0].substring(6), splitedTo[0], splitedTo[1]);
                        storage.add(newTask);
                        ui.add(newTask, storage);
                        break;
                    }
                    case DELETE: {
                        // Incorrect command syntax handler
                        if (splitedTask.length != 2) {
                            throw new DukeException("delete");
                        }
                        int index = 0;
                        try {
                            index = Integer.parseInt(splitedTask[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("delete");
                        }

                        // Index out of bound handler
                        if (index >= storage.getItems().size()) {
                            ui.print("The index of task cannot be larger than number of task.");
                            continue;
                        } else if (index < 0) {
                            ui.print("The index of task must be positive integer.");
                            continue;
                        }

                        ui.delete(storage.getItem(index), storage);
                        storage.delete(index);
                        break;
                    }
                    default: {
                        ui.print("Syntax error, unknown command.");
                    }
                }
            } catch (DukeException e) {
                ui.print(e.handle());
            }

            // Loop breaker check
            if (isEnd) {
                break;
            }
        }

        // Exit
        ui.exit();
    }
}
