package com.example.artemis;

public class Parser {
    public static void parseInput(String input, TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0].toLowerCase();

        switch (command) {
        case "bye":
            ui.showGoodbyeMessage();
            storage.save(tasks.getTasks());
            System.exit(0);
            break;
        case "list":
            ui.showTaskList(tasks.getTasks());
            break;
        case "mark":
            handleMarkAsDone(tokens, tasks, ui);
            break;
        case "unmark":
            handleMarkAsNotDone(tokens, tasks, ui);
            break;
        case "todo":
            handleTodoTask(tokens, tasks, ui);
            break;
        case "deadline":
            handleDeadlineTask(input, tasks, ui);
            break;
        case "event":
            handleEventTask(input, tasks, ui);
            break;
        case "delete":
            handleDeleteTask(tokens, tasks, ui);
            break;
        default:
            ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleMarkAsDone(String[] tokens, TaskList tasks, Ui ui) {
        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            task.markAsDone();
            ui.showTaskMarkedAsDone(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! Please provide a valid task number.");
        }
    }

    private static void handleMarkAsNotDone(String[] tokens, TaskList tasks, Ui ui) {
        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            task.markAsNotDone();
            ui.showTaskMarkedAsNotDone(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! Please provide a valid task number.");
        }
    }

    private static void handleTodoTask(String[] tokens, TaskList tasks, Ui ui) {
        try {
            String description = tokens[1].trim();
            if (description.isEmpty()) {
                throw new ArtemisException("OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.addTask(new Todo(description));
            ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException | IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void handleDeadlineTask(String input, TaskList tasks, Ui ui) {
        try {
            String[] tokens = input.split("/by");
            if (tokens.length < 2) {
                throw new ArtemisException("OOPS!!! Invalid deadline format. " +
                        "Please use: deadline [description] /by [dd-mm-yyyy hhmm]");
            }

            String description = tokens[0].replace("deadline ", "").trim();
            String by = tokens[1].trim();
            tasks.addTask(new Deadline(description, by));
            ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleEventTask(String input, TaskList tasks, Ui ui) {
        try {
            String[] tokens = input.split("/from");
            if (tokens.length < 2) {
                throw new ArtemisException("OOPS!!! Invalid event format. " +
                        "Please use: event [description] /from [dd-mm-yyyy hhmm] /to [dd-mm-yyyy hhmm]");
            }

            String description = tokens[0].replace("event ", "").trim();
            String[] fromTo = tokens[1].split("/to");
            if (fromTo.length < 2) {
                throw new ArtemisException("OOPS!!! Invalid event format. " +
                        "Please use: event [description] /from [dd-mm-yyyy hhmm] /to [dd-mm-yyyy hhmm]");
            }

            String from = fromTo[0].trim();
            String to = fromTo[1].trim();
            tasks.addTask(new Event(description, from, to));
            ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleDeleteTask(String[] tokens, TaskList tasks, Ui ui) {
        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            tasks.deleteTask(taskIndex);
            ui.showTaskDelete(task, tasks.getTasks().size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! Please provide a valid task number.");
        }
    }
}
