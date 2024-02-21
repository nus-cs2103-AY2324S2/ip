package nicole.userrequests;

import nicole.nicoleexceptions.NicoleException;
import nicole.task.Task;
import nicole.taskstorage.TaskList;

import java.util.Arrays;

public class Request {
    private Task newTask;

    /**
     * Initialises a Request
     *
     * @param request the user request.
     * @throws NicoleException if the Parser throws this exception due to issues
     *                         with the request.
     */
    public Request(String request) throws NicoleException {
        if (request.equals("list")
                || request.contains("mark")
                || request.contains("unmark")
                || request.contains("help")
                || request.contains("delete")
                || request.contains("sort by date")
                || request.contains("find")
                || request.contains("update")) {
        } else {
            newTask = Parser.parseRequest(request);
        }
    }

    /**
     * Handles user requests.
     *
     * @param request the user request.
     * @return nicole's response to the request.
     * @throws NicoleException if there are issues from TaskList or Storage when handling the request.
     */
    public String handleRequest(String request) throws NicoleException {
        TaskList taskList = new TaskList();
        if (request.contains("unmark")) {
            try {
                int taskNumber = Integer.parseInt(request.substring(7));
                return taskList.unmarkTask(taskNumber);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("unmark [task number] pls...");
            }
        } else if (request.contains("mark")) {
            try {
                int taskNumber = Integer.parseInt(request.substring(5));
                return taskList.markTask(taskNumber);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("mark [task number] pls...");
            }
        } else if (request.contains("delete")) {
            try {
                int taskNumber = Integer.parseInt(request.substring(7));
                return taskList.deleteTask(taskNumber);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("delete [task number] pls...");
            }
        } else if (request.equals("help")) {
            return "I'm your task/deadline/event manager! I'm down with these requests,\n"
                    + "1. todo [task]\n"
                    + "2. deadline [task] by YYYY-MM-DD\n"
                    + "3. event [description] from YYYY-MM-DD at HH:MM:SS to YYYY-MM-DD at HH:MM:SS\n"
                    + "4. list\n"
                    + "5. mark [tasknumber]\n"
                    + "6. unmark [tasknumber]\n"
                    + "7. delete [tasknumber]\n"
                    + "8. sort by date\n"
                    + "9. find [keyword]\n"
                    + "10. update [tasknumber] [new task name]\n"
                    + "11. bye\n"
                    + "12. help";
        } else if (request.equals("sort by date")) {
            return taskList.sortTasksByDate();
        } else if (request.contains("find")) {
            try {
                String taskKeyWord = request.substring(6);
                return taskList.findTasks(taskKeyWord);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("find [keyword] pls...");
            }
        } else if (request.contains("update")) {
            try {
                int taskNumber = Integer.parseInt(request.substring(7, 8));
                String newTaskName = request.substring(9);
                return taskList.updateTask(newTaskName, taskNumber);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("update [task number] [name] pls...");
            }
        } else if (!request.equals("list")) {
            return taskList.addTask(newTask);
        } else {
            return taskList.listTasks();
        }
    }
}
