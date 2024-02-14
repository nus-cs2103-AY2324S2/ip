package quacky;

import java.time.LocalDate;

public class Parser {

    public static String parseCommand(String command, TaskList tasks, UI ui) throws QuackyException {
        String[] keywords = command.split(" ", 2);
        String commandWord = keywords[0];
        switch (commandWord.toLowerCase()) {
        case "list": {
            return ui.showList(tasks);
        }
        case "find": {
            String keyword = keywords[1];
            TaskList tasksFound = tasks.findTasksByKeyword(keyword);
            assert tasksFound != null:  "This should always return a tasklist";
            if (tasksFound.taskNumber() == 0) {
                return ui.say("No tasks found with the keyword: " + keyword);
            } else {
                return ui.showList(tasksFound);
            }
        }
        case "mark": {
            int taskNumber = Integer.parseInt(keywords[1]) - 1;
            try {
                assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
                tasks.markCompleteTask(taskNumber);
                return ui.showMarkDone(tasks.printTask(taskNumber));
            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        }

        case "unmark": {
            int taskNumber = Integer.parseInt(keywords[1]) - 1;
            assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
            tasks.unmarkCompleteTask(taskNumber);
            return ui.showUnmarkDone(tasks.printTask(taskNumber));
        }

        case "delete": {
            int taskNumber = Integer.parseInt(keywords[1]) - 1;
            assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
            tasks.deleteTask(taskNumber);
            return ui.showDeleteTask(tasks.taskNumber(), tasks.printTask(taskNumber));
        }

        case "todo": {
            try {
                if (command.trim().equals("todo")) {
                    throw new QuackyException("Quack? (Please provide a description for your task)");
                }
                Task newTask = new Todo(keywords[1]);
                tasks.addTask(newTask);
                return ui.showAddTask(tasks.taskNumber(), newTask.toString());

            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        }

        case "deadline": {
            try {
                if (command.trim().equals("deadline")) {
                    throw new QuackyException("Quack? (Please provide a description for your task)");
                }
                String[] parts = command.substring(9).split(" /by ");
                Task newTask = new Deadline(parts[0], LocalDate.parse(parts[1]));
                tasks.addTask(newTask);
                return ui.showAddTask(tasks.taskNumber(), newTask.toString());

            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        }
        case "event": {
            try {
                if (command.trim().equals("event")) {
                    throw new QuackyException("Quack? (Please provide a description for your task)");
                }
                String[] parts = keywords[1].split(" /from | /to ");
                Task newTask = new Event(parts[0], LocalDate.parse(parts[1]), LocalDate.parse(parts[2]));
                tasks.addTask(newTask);
                return ui.showAddTask(tasks.taskNumber(), newTask.toString());

            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        }
        case "bye": {
            return ui.showFarewell();
        }
        default: {
            throw new QuackyException("Quack? (In confusion)");
        }
        }
    }
}