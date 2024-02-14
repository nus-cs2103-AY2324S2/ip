package quacky;

import java.time.LocalDate;

public class Parser {

    public static String parseCommand(String command, TaskList tasks, UI ui) throws QuackyException {
        String[] keywords = command.split(" ", 2);
        String commandWord = keywords[0];
        switch (commandWord.toLowerCase()) {
        case "list":
            return handleList(tasks,ui);
        case "find":
            String keyword = keywords[1];
            return handleFind(keyword,tasks,ui);
        case "mark":
            int taskToMark = Integer.parseInt(keywords[1]) - 1;
            return handleMark(taskToMark,tasks, ui);
        case "unmark":
            int taskToUnmark = Integer.parseInt(keywords[1]) - 1;
            return handleUnmark(taskToUnmark,tasks, ui);
        case "delete":
            int taskToDelete = Integer.parseInt(keywords[1]) - 1;
            return handleDelete(taskToDelete,tasks,ui);
        case "todo":
            try {
                if (command.trim().equals("todo")) {
                    throw new QuackyException("Quack? (Please provide a description for your task)");
                }
                Todo newTodo = new Todo(keywords[1]);
                return handleTasks(newTodo, tasks, ui);
            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        case "deadline":
            try {
                if (command.trim().equals("deadline")) {
                    throw new QuackyException("Quack? (Please provide a description for your task)");
                }
                String[] parts = command.substring(9).split(" /by ");
                Deadline newDeadline = new Deadline(parts[0], LocalDate.parse(parts[1]));
                return handleTasks(newDeadline, tasks, ui);
            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        case "event":
            try {
                if (command.trim().equals("event")) {
                    throw new QuackyException("Quack? (Please provide a description for your task)");
                }
                String[] parts = keywords[1].split(" /from | /to ");
                Event newEvent = new Event(parts[0], LocalDate.parse(parts[1]), LocalDate.parse(parts[2]));
                return handleTasks(newEvent, tasks, ui);

            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        case "bye":
            return handleBye(ui);
        default:
            throw new QuackyException("Quack? (In confusion)");
        }
    }
    public static String handleList(TaskList tasks, UI ui) {
        return ui.showList(tasks);
    }
    public static String handleFind(String keyword, TaskList tasks, UI ui) {
        TaskList tasksFound = tasks.findTasksByKeyword(keyword);
        assert tasksFound != null:  "This should always return a TaskList";
        if (tasksFound.taskNumber() == 0) {
            return ui.say("No tasks found with the keyword: " + keyword);
        } else {
            return ui.showList(tasksFound);
        }
    }

    public static String handleMark(int taskNumber, TaskList tasks, UI ui) {
        try {
            assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
            tasks.markCompleteTask(taskNumber);
            return ui.showMarkDone(tasks.printTask(taskNumber));
        } catch (QuackyException e) {
            return ui.showErrorMessage(e);
        }
    }

    public static String handleUnmark(int taskNumber, TaskList tasks, UI ui) {
        assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
        tasks.unmarkCompleteTask(taskNumber);
        return ui.showUnmarkDone(tasks.printTask(taskNumber));
    }
    public static String handleDelete(int taskNumber, TaskList tasks, UI ui) {
        assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
        tasks.deleteTask(taskNumber);
        return ui.showDeleteTask(tasks.taskNumber(), tasks.printTask(taskNumber));
    }
    public static String handleTasks(Task newTask,TaskList tasks, UI ui) {
        try {
            tasks.addTask(newTask);
            return ui.showAddTask(tasks.taskNumber(), newTask.toString());
        } catch (QuackyException e) {
            return ui.showErrorMessage(e);
        }
    }
    public static String handleBye(UI ui) {
        return ui.showFarewell();
    }
}
