package Martin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> startUpSequence() {
        File martinFile;
        try {
            martinFile = new File(filePath);
            if (!martinFile.exists()) {
                System.out.println("File does not exist. Creating a new file.");
                if (martinFile.createNewFile()) {
                    System.out.println("File created: " + martinFile.getName());
                }
                FileWriter fw = new FileWriter(martinFile);
                fw.write("T | 1 | dummy offset\n");
                fw.close();
            }
            return loadFromFile(martinFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Task> loadFromFile(File martinTxt) {
        ArrayList<Task> todoList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(martinTxt));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("line: " + line);
                String[] lineArray = line.split(" \\| ", 3);
                System.out.println(Arrays.toString(lineArray));
                String taskType = lineArray[0];
                boolean isDone = lineArray[1].equals("1");
                String taskDescription = lineArray[2];
                switch (taskType) {
                    case "T":
                        Todo todo = new Todo(taskDescription);
                        if (isDone) {
                            todo.markAsDone();
                        }
                        todoList.add(todo);
                        break;
                    case "E":
                        String[] eventArray = taskDescription.split(" \\| ");
                        String eventDescription = eventArray[0];
                        String[] eventTime = eventArray[1].split("-");
                        String startTime = eventTime[0];
                        String endTime = eventTime[1];
                        Event event = new Event(eventDescription, startTime, endTime);
                        if (isDone) {
                            event.markAsDone();
                        }
                        todoList.add(event);
                        break;
                    case "D":
                        String[] deadlineArray = taskDescription.split(" \\| ");
                        String deadlineDescription = deadlineArray[0];
                        LocalDate deadlineTime = LocalDate.parse(deadlineArray[1]);
                        Deadline deadline = new Deadline(deadlineDescription, deadlineTime);
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        todoList.add(deadline);
                        break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return todoList;
    }

    public void rewriteFile(TaskList taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("T | 1 | dummy offset\n");
            ArrayList<Task> todoList = taskList.getTodoList();
            for (int i = 0; i < todoList.size(); i++) {
                fw.write(todoList.get(i).toFileString());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void appendToFile(String line) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
