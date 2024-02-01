package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.*;
import java.util.Scanner;

public class Storage {
    private File file;
    private String path;

    public Storage(String path) {
        this.path = path;
        this.file = new File(path);

        // set-up infrastructure
        try {
            File dir = new File(file.getParent());
            if (!dir.exists()) { dir.mkdir(); }
            if (!file.exists()) { file.createNewFile(); }
        } catch (IOException e) {
            throw new DukeException("File unable to be created in specified directory");
        }
    }

    public TaskList loadTasks() {
        try {
            TaskList tasks = new TaskList();

            Scanner s = new Scanner(this.file);
            while (s.hasNextLine()) {
                String[] taskInput = s.nextLine().split("\\s*\\|\\s*");
                String cmd = taskInput[0];

                if (cmd.equals("D")) {
                    Deadline dl = new Deadline(taskInput[2], taskInput[3]);
                    if (taskInput[1].equals("1")) {dl.markAsDone();}
                    tasks.addTask(dl);
                } else if (cmd.equals("E")) {
                    Event evt = new Event(taskInput[2], taskInput[3], taskInput[4]);
                    if (taskInput[1].equals("1")) {evt.markAsDone();}
                    tasks.addTask(evt);
                } else if (cmd.equals("T")) {
                    Todo td = new Todo(taskInput[2]);
                    if (taskInput[1].equals("1")) {td.markAsDone();}
                    tasks.addTask(td);
                }
            }
            s.close();
            return tasks;

        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }
    }

    public void writeTasks(TaskList tasks) {
        try (BufferedWriter reset = new BufferedWriter(new FileWriter(this.path))){
            reset.write("");
            tasks.getTasks().forEach((task) -> {
                try (BufferedWriter out = new BufferedWriter(new FileWriter(this.path, true))){
                    out.write(task.writeContent());
                    out.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
