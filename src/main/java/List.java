import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class List {
    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) throws IOException {
        tasks.add(task);
        String task_s = tasks.size() == 1 ? " task " : " tasks ";
        System.out.println("__________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + tasks.size() + task_s + "in the list.\n"
                + "__________________________________________________________\n");
        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/duke.txt", true));
        writer.write("\n" + tasks.size() + "." + task.toString());
        writer.close();
    }

    public void displayTasks() {
        System.out.println("__________________________________________________________\n"
                + "Here are the tasks in your list:"
        );
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("__________________________________________________________\n");
    }

    public void deleteTask(int taskNum) throws IOException {
        String task_s = tasks.size() - 1 == 1 ? " task " : " tasks ";
        System.out.println("__________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + tasks.get(taskNum).toString()
                + "\nNow you have " + (tasks.size() - 1) + task_s + "in the list.\n"
                + "__________________________________________________________\n");
        BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"));
        StringBuilder newFileContent = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String currLine = reader.readLine().split("\\.")[1];
            if (i < taskNum) {
                newFileContent.append(i + 1).append(".").append(currLine).append("\n");
            } else if (i > taskNum) {
                newFileContent.append(i).append(".").append(currLine).append("\n");
            }
        }
        newFileContent.setLength(newFileContent.length() - 1);
        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/duke.txt"));
        writer.write(newFileContent.toString());
        writer.close();
        tasks.remove(taskNum);
    }

    public boolean validTaskNum(int taskNum) {
        return taskNum > 0 && taskNum <= tasks.size();
    }

    public void markTask(int taskNum) throws IOException {
        tasks.get(taskNum).mark();
        BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"));
        StringBuilder newFileContent = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String currLine = reader.readLine();
            if (i == taskNum) {
                String newLine = currLine.split("\\[")[0] + "["
                        + currLine.split("\\[")[1] + "[" + "X" + "]"
                        + currLine.split("]")[2];
                newFileContent.append(newLine).append("\n");
            } else {
                newFileContent.append(currLine).append("\n");
            }
        }
        newFileContent.setLength(newFileContent.length() - 1);
        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/duke.txt"));
        writer.write(newFileContent.toString());
        writer.close();
    }

    public void unmarkTask(int taskNum) throws IOException {
        tasks.get(taskNum).unmark();
        BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"));
        StringBuilder newFileContent = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String currLine = reader.readLine();
            if (i == taskNum) {
                String newLine = currLine.split("\\[")[0] + "["
                        + currLine.split("\\[")[1] + "[" + " " + "]"
                        + currLine.split("]")[2];
                newFileContent.append(newLine).append("\n");
            } else {
                newFileContent.append(currLine).append("\n");
            }
        }
        newFileContent.setLength(newFileContent.length() - 1);
        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/duke.txt"));
        writer.write(newFileContent.toString());
        writer.close();
    }

    public int getListSize() {
        return tasks.size();
    }

//    public void saveTasks() throws IOException {
//        String fileName = "./data/duke.txt";
//        Path filePath = Paths.get(fileName);
//        File f = new File(fileName);
//        if (!f.isFile()) {
//            Files.createFile(filePath);
//        }
//        for (Task task: tasks) {
//
//        }
//    }

    public void loadTasks() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"));
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            String taskType = currLine.split("]")[0].split("\\[")[1];
            boolean isDone = Objects.equals(currLine.split("\\[")[2].split("]")[0], "X");
            switch (taskType) {
            case "T":
                String description = currLine.split("] ")[1];
                tasks.add(new ToDo(description));
                break;
            case "D":
                description = currLine.split(" \\(by:")[0].split("] ")[1];
                String dueDate = currLine.split("\\(by: ")[1].split("\\)")[0];
                tasks.add(new Deadline(description, LocalDateTime.parse(dueDate,
                        DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))));
                break;
            case "E":
                description = currLine.split(" \\(from:")[0].split("] ")[1];
                String startDate = currLine.split("\\(from: ")[1].split(" to:")[0];
                String endDate = currLine.split("to: ")[1].split("\\)")[0];
                tasks.add(new Event(description, startDate, endDate));
                break;
            }
            if (isDone) {
                tasks.get(tasks.size() - 1).setToDone();
            }
        }
    }
}
