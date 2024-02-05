import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class TaskList {
        ArrayList<Task> taskList;
        boolean isDoneDefault = false;
        Parser parser;


    public TaskList() {
        this.taskList = new ArrayList<>();
        this.parser = new Parser();
    }

    public String addTask(String userInput) {
        String returnOutput;

        try {
            ArrayList<String> parsedInputArray = parser.parse(userInput);
            String taskType = parsedInputArray.get(0);
            String description = parsedInputArray.get(1);

            if (taskType.equalsIgnoreCase("todo")) {
                taskList.add(new Todo(isDoneDefault, description));

            } else if (taskType.equalsIgnoreCase("deadline")) {
                String deadlineBy = parsedInputArray.get(2);
                taskList.add(new Deadline(isDoneDefault, description, deadlineBy));

            } else if (taskType.equalsIgnoreCase("event")) {
                String eventFrom = parsedInputArray.get(2);
                String eventTo = parsedInputArray.get(3);
                taskList.add(new Event(isDoneDefault, description, eventFrom, eventTo));

            }

            Task taskObject = taskList.get(taskList.size() - 1);
            returnOutput = taskObject.toString();

        } catch (HALException | DateTimeParseException e) {
            System.out.println(e.getMessage());
            returnOutput = "error";
        }

        return returnOutput;
    }

    public String removeTask(int taskIndex) {
        Task taskObject = taskList.get(taskIndex);
        taskList.remove(taskIndex);

        return taskObject.toString();
    }

    public String markAsDone(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.markAsDone();
        return t.toString();
    }

    public String markAsUndone(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.markAsUndone();
        return t.toString();
    }

    public void listTasks() {
        System.out.println("Tasks:");

        for (int i = 0; i < taskList.size(); i++) {

            Task t = taskList.get(i);
            System.out.printf("%d. %s\n", i + 1, t.toString());
        }
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }


    // add previous list form storage into newly initialised tasklist
    public void initialisePrevTaskList(ArrayList<Task> prevTaskList) {
        System.out.println(prevTaskList);
        this.taskList = prevTaskList;
    }
}
