/**
 * This is the main method for the Balkan Bot Chat Bot.
 * The Balkan Bot is able to make and store various tasks such as ToDos, Deadlines and Events.
 * It is also able to Mark or Unmark them as complete.
 */

package storage;

import balkan.BalkanBotException;
import task.*;
import parser.Parser;

import java.io.*;

public class Storage {

    String filePath;

    enum TaskType {
        ToDo, Deadline, Event
    }

    public Storage() {
        this.filePath = "balkanbot.txt";
    }

    /**
     * Saves the current tasks in the task list into a text file.
     *
     * @param tasks The task list containing all the tasks
     */
    public void save(TaskList tasks) {
        StringBuilder textOutput = new StringBuilder();
        for (Task task : tasks.getTaskList()) {
            if (task == null) {
                break;
            }
            textOutput.append(task).append("\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(textOutput.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Used in the Load method.
     * Marks a task if it has been completed already.
     *
     * @param task      task being checked
     * @param completed boolean indicating whether task has been completed
     */
    public static void markCheck(Task task, boolean completed) {
        if (completed) {
            task.mark();
        }
    }

    /**
     * Loads the input from text file as a task list.
     *
     * @throws BalkanBotException if the file cannot be found or cannot be read
     */
    public Task[] load() throws BalkanBotException {
        Task[] tasks = new Task[100];
        String[] textInput = new String[100];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                textInput[counter] = line;
                counter++;
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new BalkanBotException(e.toString());
        }

        int current = 0;
        for (String task : textInput) {
            if (task == null) {
                break;
            } else {
                tasks = addTask(tasks, task, current);
                current++;
            }
        }
        Parser.updateCurrent(current);
        return tasks;
    }

    private Task[] addTask(Task[] tasks, String task, int current) {
        boolean proceed = true;

        String taskNature = task.substring(0, 6);
        String[] details = task.substring(7).split("\\s+");
        String type = taskNature.substring(1, 2);
        String complete = taskNature.substring(4, 5);

        TaskType taskType = getTaskType(type);

        boolean[] checkCompleteResult = checkComplete(complete, proceed);
        boolean completed = checkCompleteResult[0];

        proceed = checkCompleteResult[1];
        if (taskType == null) {
            proceed = false;
        }

        if (proceed) {
            switch (taskType) {
                case ToDo: {
                    tasks = addToDoTask(tasks, current, details, completed);
                    break;
                }
                case Deadline: {
                    tasks = addDeadlineTask(tasks, current, details, completed);
                    break;
                }
                case Event: {
                    tasks = addEventTask(tasks, current, details, completed);
                    break;
                }
            }
        }

        return tasks;
    }

    private TaskType getTaskType(String type) {
        TaskType taskType = null;
        switch (type) {
            case "T": {
                taskType = TaskType.ToDo;
                break;
            }
            case "D": {
                taskType = TaskType.Deadline;
                break;
            }
            case "E": {
                taskType = TaskType.Event;
                break;
            }
        }

        return taskType;
    }

    private boolean[] checkComplete(String complete, boolean proceed) {
        boolean[] result = new boolean[2];

        if (complete.equals("X")) {
            result[0] = true;
            result[1] = proceed;
        } else if (complete.equals(" ")) {
            result[0] = false;
            result[1] = proceed;
        } else {
            result[1] = false;
        }

        return result;
    }

    private Task[] addToDoTask(Task[] tasks, int current, String[] details, boolean completed) {
        String taskDescription = String.join(" ", details);
        try {
            tasks[current] = new ToDo(taskDescription);
            markCheck(tasks[current], completed);
        } catch (InvalidInputException e) {
            System.out.println(e);
        }
        return tasks;
    }

    private Task[] addDeadlineTask(Task[] tasks, int current, String[] details, boolean completed) {
        StringBuilder taskDescription = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        boolean foundDeadline = false;
        for (String currentString : details) {
            if (foundDeadline) {
                deadline.append(currentString).append(" ");
            } else if (currentString.equals("(by:")) {
                foundDeadline = true;
            } else {
                taskDescription.append(currentString).append(" ");
            }
        }

        String fixedDeadline = deadline.substring(0, deadline.toString().length() - 2);
        boolean storage = true;

        try {
            tasks[current] = new Deadline(taskDescription.toString(), fixedDeadline, storage);
            markCheck(tasks[current], completed);
        } catch (InvalidInputException | InvalidDateException e) {
            System.out.println(e);
        }
        return tasks;
    }

    private Task[] addEventTask(Task[] tasks, int current, String[] details, boolean completed) {
        StringBuilder taskDescription = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        boolean foundFrom = false;
        boolean foundTo = false;
        for (String currentString : details) {
            if (foundTo) {
                to.append(currentString).append(" ");
            } else if (foundFrom) {
                if (currentString.equals("to:")) {
                    foundTo = true;
                } else {
                    from.append(currentString).append(" ");
                }
            } else if (currentString.equals("(from:")) {
                foundFrom = true;
            } else {
                taskDescription.append(currentString).append(" ");
            }
        }

        String fixedTo = to.substring(0, to.toString().length() - 2);
        try {
            tasks[current] = new Event(taskDescription.toString(), from.toString(),
                    fixedTo);
            markCheck(tasks[current], completed);
        } catch (InvalidInputException | InvalidDateException e) {
            System.out.println(e);
        }
        return tasks;
    }
}