package userrequests;

import java.io.IOException;

import nicoleexceptions.NicoleException;
import task.Task;
import taskstorage.TaskList;

public class Request {
    private Task newTask;

    /**
     * Initialises a Request
     *
     * @param name the user request.
     * @throws NicoleException if the Parser throws this exception due to issues
     *                         with the request.
     * @throws IOException if the TaskList throws this exception due to issues with
     *                     posting and retrieving data from hard drive.
     */
    public Request(String name) throws NicoleException, IOException {
        if (name.equals("list")
                || name.contains("mark")
                || name.contains("unmark")
                || name.contains("help")
                || name.contains("delete")
                || name.contains("priority")
                || name.contains("find")) {
        } else {
            this.newTask = Parser.parseRequest(name);
        }
        this.handleRequest(name);
    }

    private void handleRequest(String name) throws NicoleException {
        TaskList taskList = new TaskList();
        if (name.contains("unmark")) {
            try {
                int taskNumber = Integer.parseInt(name.substring(7));
                taskList.unmarkTask(taskNumber);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("unmark [task number] pls...");
            }
        } else if (name.contains("mark")) {
            try {
                int taskNumber = Integer.parseInt(name.substring(5));
                taskList.markTask(taskNumber);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("mark [task number] pls...");
            }
        } else if (name.contains("delete")) {
            try {
                int taskNumber = Integer.parseInt(name.substring(7));
                taskList.deleteTask(taskNumber);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("delete [task number] pls...");
            }
        } else if (name.equals("help")) {
            System.out.println("Nicole: " +
                    "I'm your task/deadline/event manager! I'm down with these requests,\n"
                    + "1. todo [task]\n"
                    + "2. deadline [task] by YYYY-MM-DD\n"
                    + "3. event [name] from YYYY-MM-DD at HH-MM-SS to YYY-MM-DD at HH-MM-SS\n"
                    + "4. list\n"
                    + "5. priority\n"
                    + "6. find [keyword]\n"
                    + "7. bye\n"
                    + "8. help"
            );
        } else if (name.equals("priority")) {
            taskList.sortTasksByPriority();
        } else if (name.contains("find")) {
            try {
                String taskKeyWord = name.substring(6);
                taskList.findTasks(taskKeyWord);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                throw new NicoleException("find [keyword] pls...");
            }
        } else if (!name.equals("list")) {
            taskList.addTask(newTask);
        } else {
              taskList.listTasks();
        }
    }
}
