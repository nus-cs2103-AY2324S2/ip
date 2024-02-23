package duke;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.FileIOException;
import duke.exceptions.IllegalDateFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Deals in connecting with the user command.
 */
public class Parser {
    /**
     * Identifies the type of task.
     *
     * @param command User's inputted text.
     * @return The type of task the user wants to do.
     */
    public String identify(String command) {
        String[] strings = command.split(" ");
        assert strings.length != 0 : "Sorry Boss! The commands can not be empty.";
        return strings[0];
    }

    /**
     * Parses and executes user command to manage tasks.
     *
     * @param str      User's inputed String (Command).
     * @param io       IoHandler object for input and output
     * @param taskList List containing Tasks.
     * @param storage  Acts as a storage object for saving tasks.
     * @return boolean value depicting whether application should run or should be exited.
     */
    public String parse(String str, IOHandler io, TaskList taskList, Storage storage) {
        String command = identify(str);
        String result;
        try {
            switch (command) {
                case "deadline":
                    Deadline tempDeadline = taskList.setDeadline(str.substring(8));
                    taskList.addTask(tempDeadline);
                    result = io.echoAdd(tempDeadline, taskList);
                    storage.saveInFile(taskList);
                    break;
                case "event":
                    Event tempEvent = taskList.setEvent(str.substring(5));
                    taskList.addTask(tempEvent);
                    result = io.echoAdd(tempEvent, taskList);
                    storage.saveInFile(taskList);
                    break;
                case "todo":
                    Todo tempToDo = taskList.setToDo(str.substring(4));
                    taskList.addTask(tempToDo);
                    result = io.echoAdd(tempToDo, taskList);
                    storage.saveInFile(taskList);
                    break;
                case "delete":
                    Task tempDelete = taskList.deleteTask(str, taskList);
                    storage.saveInFile(taskList);
                    StringBuilder deleteResult = new StringBuilder();
                    deleteResult.append("Okay boss! Task removed.\n");
                    deleteResult.append(" ").append(tempDelete.toString()).append("\n");
                    deleteResult.append("Boss you have ").append(taskList.size()).append(" tasks in the list.");
                    result = deleteResult.toString();
                    break;
                case "mark":
                    Task tempMark = taskList.setDone(str, taskList);
                    StringBuilder markResult = new StringBuilder();
                    storage.saveInFile(taskList);
                    markResult.append("Okay Boss! Task marked as done\n");
                    markResult.append("  ").append(tempMark);
                    result = markResult.toString();
                    break;
                case "unmark":
                    Task tempUnmark = taskList.setUndone(str, taskList);
                    StringBuilder unmarkResult = new StringBuilder();
                    storage.saveInFile(taskList);
                    unmarkResult.append("Okay Boss! Task marked as undone\n");
                    unmarkResult.append("  ").append(tempUnmark);
                    result = unmarkResult.toString();
                    break;
                case "list":
                    result = io.display(taskList);
                    break;
                case "bye":
                    result = io.exit();
                    break;
                case "find":
                    ArrayList<Task> temp = taskList.find(str.substring(5));
                    result = io.displaySearchResults(temp);
                    break;
                case "view":
                    ArrayList<Task> tempList = taskList.checkSchedule(str.substring(5));
                    result = io.displayScheduledTasks(tempList);
                    break;
                default:
                    throw new DukeException(" Oopsie doodle! I can not understand what you mean.");
            }
        } catch (DukeException | IllegalDateFormatException | FileIOException e) {
            result = "ERROR : " + e.getMessage();
        }
        return result;
    }
}
