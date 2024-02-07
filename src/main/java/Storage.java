import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {
    private final File file;

    Storage(String fileFolder, String fileName) throws DukeException {
        File directory = new File(fileFolder);
        if (!directory.exists()) {
            boolean isDirMade = directory.mkdirs();
            if (!isDirMade) {
                throw new DukeException("Oops! Something is wrong with directory creation!");
            }
        }
        file = new File(fileFolder + "/" + fileName);
        if (!file.exists()) {
            try {
                boolean isCreated = file.createNewFile();
                if (!isCreated) {
                    throw new DukeException("Oops! Something is wrong with file creation!");
                }
            } catch (IOException e) {
                System.out.println("Oops! Something is wrong with file creation!");
            }
        }
    }

    ArrayList<String> load() throws DukeException {
        ArrayList<String> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                result.add(line);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new DukeException("Oops! No file in the directory!");
        }
    }

    void editLine(int num, Task task) {
        try {
            int i = 1;
            ArrayList<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String newLine = sc.nextLine();
                if (i != num) {
                    lines.add(newLine);
                } else {
                    lines.add(task.taskToLine());
                }
                i++;
            }
            FileWriter fw = new FileWriter(file);
            for (String line : lines) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! Something is wrong with the file management!");
        }
    }

    void deleteLine(int num) {
        try {
            int i = 1;
            ArrayList<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String newLine = sc.nextLine();
                if (i != num) {
                    lines.add(newLine);
                }
                i++;
            }
            FileWriter fw = new FileWriter(file);
            for (String line : lines) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! Something is wrong with the file management!");
        }
    }

    void addTask(Task task) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(task.taskToLine() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! unable to write to the file!");
        }
    }
}
