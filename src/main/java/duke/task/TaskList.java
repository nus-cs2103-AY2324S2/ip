package duke.task;

import duke.exception.HALException;
import duke.gui.Parser;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class TaskList {
        ArrayList<Task> taskList;
        boolean IS_DONE_DEFAULT = false;
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
                taskList.add(new Todo(IS_DONE_DEFAULT, description));

            } else if (taskType.equalsIgnoreCase("deadline")) {
                String deadlineBy = parsedInputArray.get(2);
                taskList.add(new Deadline(IS_DONE_DEFAULT, description, deadlineBy));

            } else if (taskType.equalsIgnoreCase("event")) {
                String eventFrom = parsedInputArray.get(2);
                String eventTo = parsedInputArray.get(3);
                taskList.add(new Event(IS_DONE_DEFAULT, description, eventFrom, eventTo));

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

    public void listMatchingTasks(String findKeyword) {
        System.out.println("Here are the matching tasks in your list:");

        int foundTaskNumber = 1;

        for (int i = 0; i < taskList.size(); i++) {

            Task t = taskList.get(i);
            String taskDescription = t.getDescription();

            int index = taskDescription.indexOf(findKeyword);

            if (index != -1) {
                System.out.printf("%d. %s\n", foundTaskNumber, t.toString());
                foundTaskNumber++;
            }
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
