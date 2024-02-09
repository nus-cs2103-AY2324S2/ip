
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage() {
        this.filePath = "./data/tasks.txt";
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            File directory = new File("./data/");
            if(!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(filePath);
            Scanner whatToDo = new Scanner(file);

            while (whatToDo.hasNext()) {
                String readText = whatToDo.nextLine();
                char taskType = readText.charAt(1);
                char doneOrNot = readText.charAt(5);
                String description = readText.substring(9);

                Task task = null;

                if (taskType == 'T') {
                    task = new ToDo(description);
                    listOfTasks.add(task);
                } else if (taskType == 'D') {
                    try {
                        task = new Deadline(description);
                        listOfTasks.add(task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        task = new Event(description);
                        listOfTasks.add(task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (doneOrNot == '1') {
                    task.markComplete();
                }

            }
        } catch (FileNotFoundException e) {
            throw new DukeException(" No Saved Tasks Found. Let's start with an empty list!\n");
        }

        return listOfTasks;
    }



    public void saveToFile(ArrayList<Task> listOfTasks) throws DukeException {
        String toSave = "";

        for (int i = 0; i < listOfTasks.size(); i++) {
            toSave += listOfTasks.get(i).toSave() + "\n";
        }

        try {
            FileWriter file = new FileWriter(this.filePath);
            file.write(toSave);
            file.close();
        } catch (IOException e) {
            throw new DukeException(" Error while saving, please try again!\n");
        }
    }
}
