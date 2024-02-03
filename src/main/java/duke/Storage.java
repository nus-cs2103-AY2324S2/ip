package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import duke.action.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


class Storage {
    private File taskFile;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    // from txt to tasklist
    public TaskList loadFromFile() throws FileNotFoundException {
        TaskList tasks = new TaskList();

        try (Scanner sc = new Scanner(this.taskFile)) {
            while (sc.hasNext()) {
                String taskLine = sc.nextLine();
                String[] token = taskLine.split("\\|");
                String taskType = token[0];
                String status = token[1];
                String description = token[2];
                switch (taskType) {
                case "T":
                    ToDo todoTask = new ToDo(description);
                    if (status.equals("X")) {
                        todoTask.mark();
                    }
                    tasks.addTask(todoTask);
                    break;
                case "D":
                    Deadline deadlineTask = new Deadline(description,
                            LocalDate.parse(token[3]));
                    if (status.equals("X")) {
                        deadlineTask.mark();
                    }
                    tasks.addTask(deadlineTask);
                    break;

                case "E":
                    Event eventTask = new Event(description, LocalDate.parse(token[3]),
                            LocalDate.parse(token[4]));
                    if (status.equals("X")) {
                        eventTask.mark();
                    }
                    tasks.addTask(eventTask);
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected task type: " + taskType);
                }
            }
        } catch (FileNotFoundException e) {
            handleFileNotFound();
        }

        return tasks;
    }

    // from tasklist to txt

    public void writeToFile(TaskList tasks) throws IOException {
        try (FileWriter fw = new FileWriter(this.taskFile)) {
            for (Task t : tasks) {
                String icon = t.getType();
                String status = t.getStatusIcon();
                String description = t.getDescription();
                String taskLine;

                switch (icon) {
                case "T":
                    taskLine = String.format("%s|%s|%s\n", icon, status, description);
                    break;

                case "D":
                    taskLine = String.format("%s|%s|%s|%s\n", icon, status, description, ((Deadline) t).getBy());
                    break;

                case "E":
                    taskLine = String.format("%s|%s|%s|%s|%s\n", icon, status,
                            description, ((Event) t).getFrom(), ((Event) t).getTo());
                    break;

                default:
                    throw new IllegalArgumentException("Invalid task type: " + icon);
                }

                fw.write(taskLine);
            }

            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleFileNotFound() {
        System.err.println("File not found: " + this.taskFile.getAbsolutePath());
        System.err.println("Creating the file...");

        File dataDirectory = new File("././data/");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        try {
            this.taskFile.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
