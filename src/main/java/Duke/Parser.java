package Duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static String parse(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (userInput.equals("bye") || userInput.equals("Bye")) {
                storage.saveTasksToFile(TaskList.getTasks(), TaskList.getTaskNum());
                ui.goodbyeMessage();
                return "1";
            } else if (userInput.equals("list") || userInput.equals("List")) {
                ui.showTasks(TaskList.getTasks(), TaskList.getTaskNum());
            } else if (userInput.startsWith("mark") || userInput.startsWith("Mark")) {
                int pos = userInput.indexOf(" ");
                if (pos != -1 && pos + 1 < userInput.length()) {
                    String taskStr = userInput.substring(pos + 1);

                    int taskNumber = Integer.parseInt(taskStr) - 1; // cause array
                    if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                        tasks.markTaskAsDone(taskNumber);
                        ui.showMarkedAsDone(TaskList.getTask(taskNumber));
                    } else {
                        throw new DukeException("Invalid task number >:((");
                    }
                }
            } else if (userInput.startsWith("unmark") || userInput.startsWith("Unmark")) {
                int pos = userInput.indexOf(" ");
                if (pos != -1 && pos + 1 < userInput.length()) {
                    String taskStr = userInput.substring(pos + 1);

                    int taskNumber = Integer.parseInt(taskStr) - 1;
                    if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                        tasks.markTaskAsNotDone(taskNumber);
                        ui.showMarkedAsNotDone(TaskList.getTask(taskNumber));
                    } else {
                        throw new DukeException("Invalid task number >:((");
                    }
                }
            } else if (userInput.startsWith("todo") || userInput.startsWith("Duke.Todo")) {
                int pos = userInput.indexOf(" ");
                if (pos != -1 && pos + 1 < userInput.length()) {
                    String taskStr = userInput.substring(pos + 1);

                    if (taskStr.isEmpty()) {
                        throw new DukeException("Are you gonna be doing nothing?");
                    }

                    Task newTask = new Todo(taskStr);
                    tasks.addTask(newTask);

                    ui.showAddedTask(newTask, TaskList.getTaskNum());
                } else {
                    throw new DukeException("Invalid command >:((");
                }
            } else if (userInput.startsWith("deadline") || userInput.startsWith("Duke.Deadline")) {
                int pos = userInput.indexOf(" ");
                int posBy = userInput.indexOf("/by");
                if (pos != -1 && pos + 1 < userInput.length() && posBy != -1 && posBy + 1 < userInput.length()) {
                    String taskStr = userInput.substring(pos + 1, posBy - 1);
                    String taskStrBy = userInput.substring(posBy + 4);

                    if (taskStr.isEmpty() || taskStrBy.isEmpty()) {
                        throw new DukeException("Invalid command >:((");
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(taskStrBy, formatter);

                    Task newTask = new Deadline(taskStr, deadlineDate);
                    tasks.addTask(newTask);

                    ui.showAddedTask(newTask, TaskList.getTaskNum());
                } else {
                    throw new DukeException("Invalid command >:((");
                }
            } else if (userInput.startsWith("event") || userInput.startsWith("Duke.Event")) {
                int pos = userInput.indexOf(" ");
                int posFrom = userInput.indexOf("/from");
                int posTo = userInput.indexOf("/to");

                if (pos != -1 && pos + 1 < userInput.length() && posFrom != -1 && posFrom + 1 < userInput.length() && posTo != -1 && posTo + 1 < userInput.length()) {
                    String taskStr = userInput.substring(pos + 1, posFrom - 1);
                    String taskStrFrom = userInput.substring(posFrom + 6, posTo - 1);
                    String taskStrTo = userInput.substring(posTo + 4);

                    if (taskStr.isEmpty() || taskStrFrom.isEmpty() || taskStrTo.isEmpty()) {
                        throw new DukeException("Invalid command >:((");
                    }

                    Task newTask = new Event(taskStr, taskStrFrom, taskStrTo);
                    tasks.addTask(newTask);

                    ui.showAddedTask(newTask, TaskList.getTaskNum());
                } else {
                    throw new DukeException("Invalid command >:(");
                }
            } else if (userInput.startsWith("delete") || userInput.startsWith("Delete")) {
                int pos = userInput.indexOf(" ");
                if (pos != -1 && pos + 1 < userInput.length()) {
                    String taskStr = userInput.substring(pos + 1);
                    int taskNumber = Integer.parseInt(taskStr) - 1;

                    if (taskStr.isEmpty()) {
                        throw new DukeException("You're deleting air");
                    }

                    if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                        tasks.removeTask(taskNumber);
                        ui.showDeleteTask(TaskList.getTask(taskNumber), taskNumber);
                    } else {
                        throw new DukeException("Invalid command >:(");
                    }
                }
            } else if (userInput.startsWith("On") || userInput.startsWith("on")) {
                int pos = userInput.indexOf(" ");
                if (pos != -1 && pos + 1 < userInput.length()) {
                    String dateStr = userInput.substring(pos + 1);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateToCheck = LocalDate.parse(dateStr, dateFormatter);

                    ui.showDeadlinesEventsOnDate(TaskList.getTasks(), TaskList.getTaskNum(), dateToCheck);
                } else {
                    throw new DukeException("Invalid command >:(");
                }
            } else if ((userInput.startsWith("Find") || userInput.startsWith("find"))) {
                String keyword = userInput.substring(userInput.indexOf(" ") + 1).trim();
                ui.showMatchingTasks(tasks, keyword);
            }else {
                throw new DukeException("Gurl I'm sorry, idk what that means :-(");
            }
        } catch (DukeException e) {
            throw e;
        }
        return "I'm sorry, but I don't understand that command.";
    }
}
