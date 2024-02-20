/**
 * This is the main method for the Balkan Bot Chat Bot.
 * The Balkan Bot is able to make and store various tasks such as ToDos, Deadlines and Events.
 * It is also able to Mark or Unmark them as complete.
 */

package storage;

import balkanBot.BalkanBotException;
import task.*;
import parser.Parser;

import java.io.*;

public class Storage {

    String filePath;

    enum TaskType {
        ToDo, Deadline, Event
    }

    public Storage(String filePath) {
        this.filePath = filePath;
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
     * @param task task being checked
     * @param completed boolean indicating whether task has been completed
     */
    public static void markCheck(Task task, boolean completed) {
        if (completed) {
            task.mark();
        }
    }

    public String cleanWhiteSpace(String word) {
        if (!word.isEmpty() && word.charAt(word.length() - 1) == ' ') {
            return word.substring(0, word.length() - 1);
        } else {
            return word;
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

        int counter = 1;
        int current = 0;
        for (String task : textInput) {
            if (task == null) {
                break;
            } else {
                TaskType taskType = null;
                boolean completed = false;
                boolean proceed = true;

                String taskNature = task.substring(0, 6);
                String[] details = task.substring(7).split("\\s+");
                String type = taskNature.substring(1, 2);
                String complete = taskNature.substring(4, 5);
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
                    default: {
                        System.out.println("Error with Task.Task: " + counter + "Type Unidentified Task.Task Found Save File. " +
                                "Task.Task will be skipped");
                        proceed = false;
                    }
                }

                if (complete.equals("X")) {
                    completed = true;
                } else if (!complete.equals(" ")) {
                    System.out.println("Completion status of task [" + counter + "] is invalid. Task.Task will be skipped");
                    proceed = false;
                }

                counter++;

                if (!proceed) {
                    continue;
                }

                switch (taskType) {
                    case ToDo: {
                        String taskDescription = String.join(" ", details);
                        try {
                            tasks[current] = new ToDo(taskDescription);
                            markCheck(tasks[current], completed);
                            current++;
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println(e);
                            break;
                        }
                    }
                    case Deadline: {
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

                        try {
                            tasks[current] = new Deadline(taskDescription.toString(), fixedDeadline);
                            markCheck(tasks[current], completed);
                            current++;
                            break;
                        } catch (InvalidInputException | InvalidDateException e) {
                            System.out.println(e);
                            break;
                        }
                    }
                    case Event: {
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
                            current++;
                            break;
                        } catch (InvalidInputException | InvalidDateException e) {
                            System.out.println(e);
                            break;
                        }
                    }
                }
            }
        }
        Parser.updateCurrent(current);
        return tasks;
    }
}