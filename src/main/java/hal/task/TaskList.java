package hal.task;

import hal.exception.HALException;
import hal.gui.Parser;

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
            returnOutput = "Give me clearer instructions. I cannot do that.";
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

    public String listTasks() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);

            stringBuilder.append(String.format("%d. ", i + 1))
                    .append(String.format("%s\n", t.toString()));
        }

        return stringBuilder.toString();
    }

    public String listMatchingTasks(String findKeyword) {

        StringBuilder stringBuilder = new StringBuilder();
        int foundTaskNumber = 1;

        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String taskDescription = t.getDescription();
            int index = taskDescription.indexOf(findKeyword);

            if (index != -1) {
                stringBuilder.append(String.format("%d. ", foundTaskNumber))
                        .append(String.format("%s\n", t.toString()));

                foundTaskNumber++;
            }
        }

        return stringBuilder.toString();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    
    public void initialisePrevTaskList(ArrayList<Task> prevTaskList) {
        System.out.println(prevTaskList);
        this.taskList = prevTaskList;
    }
}
