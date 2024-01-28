import java.io.IOException;

public class Request {
    public static boolean priorityTasking = false;
    private Task newTask;
    public Request(String name) throws NicoleException, IOException {
        if (name.equals("list") ||
                name.contains("mark") ||
                name.contains("unmark") ||
                name.contains("help") ||
                name.contains("delete") ||
                name.contains("priority")) {
        } else {
            this.newTask = Parser.parseRequest(name);
        }
        this.handleRequest(name);
    }

    private void handleRequest(String name) throws NicoleException, IOException {
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
            System.out.println(Nicole.botName + ": " +
                    "I'm your task/deadline/event manager! I'm down with these requests,\n" +
                    "1. todo [task]\n" +
                    "2. deadline [task] by YYYY-MM-DD\n" +
                    "3. event [name] from YYYY-MM-DD at HH-MM-SS to YYY-MM-DD at HH-MM-SS\n" +
                    "4. list\n" +
                    "5. priority\n" +
                    "6. bye\n" +
                    "7. help"
            );
        } else if (name.equals("priority")) {
            Request.priorityTasking = true;
        } else if (!name.equals("list")) {
            taskList.addTask(newTask);
        } else {
            taskList.listTasks();
        }
    }
}
