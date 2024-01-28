package userrequests;

import task.Task;

import nicoleexceptions.NicoleException;

import taskstorage.TaskList;

import java.io.IOException;

public class Request {
    public static boolean needPriorityTasking = false;
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
            int taskNumber = Integer.parseInt(name.substring(7));
            taskList.unmarkTask(taskNumber);
        } else if (name.contains("mark")) {
            int taskNumber = Integer.parseInt(name.substring(5));
            taskList.markTask(taskNumber);
        } else if (name.contains("delete")) {
            int taskNumber = Integer.parseInt(name.substring(7));
            taskList.deleteTask(taskNumber);
        } else if (name.equals("help")) {
            System.out.println("Nicole: " +
                    "I'm your task/deadline/event manager! I'm down with these requests,\n"
                    + "1. todo [task]\n"
                    + "2. deadline [task] by YYYY-MM-DD\n"
                    + "3. event [name] from YYYY-MM-DD at HH-MM-SS to YYY-MM-DD at HH-MM-SS\n"
                    + "4. list\n"
                    + "5. priority\n"
                    + "6. bye\n"
                    + "7. help"
            );
        } else if (name.equals("priority")) {
            Request.needPriorityTasking = true;
        } else if (name.contains("find")) {
            String taskKeyWord = name.substring(6);
            taskList.findTasks(taskKeyWord);
        } else if (!name.equals("list")) {
            taskList.addTask(newTask);
        } else {
            taskList.listTasks();
        }
    }
}
