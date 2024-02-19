import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() throws StorageFileLoadingException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        //Solution below inspired by https://www.w3schools.com/java/java_files_read.asp
        try {
            File storageFile = new File(filePath);
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNextLine()) {
                String existingLine = scanner.nextLine();
                Task existingTask = creatTask(existingLine); //modified later
                taskList.add(existingTask);
            }
        } catch (FileNotFoundException e) {
            throw new StorageFileLoadingException();
        }
        return taskList;
    }

    //Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
    void createFile() {
        try {
            File storageFile = new File(filePath);
            if (storageFile.createNewFile()) {
                System.out.println("File: " + storageFile + " is created");
            }
        } catch (IOException e) {
            System.out.println("Some IOException happens");
        }
    }

    //Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
    void writeFile(TaskList tasks) {
        createFile();
        try {
            FileWriter writer = new FileWriter(filePath, false);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Some IOException happens");
        }
    }

    Task creatTask(String existingString) {
        char character = existingString.charAt(1);
        boolean isDone = existingString.charAt(4) == 'X';
        if (character == 'T') {
            String description = existingString.substring(7);
            return new ToDo(description, isDone);
        }
        String description = existingString.substring(7)
                .split(" \\(")[0];
        if (character == 'D') {
            //Solution below inspired by  https://howtodoinjava.com/java/date-time/localdate-parse-string/
            String dateString = existingString.split("\\(by: ")[1]
                    .split("\\)")[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);
            return new Deadline(description, isDone, date);
        }
        String startTime = existingString.split("\\(from: ")[1]
                .split(" to: ")[0];
        String endTime = existingString.split("\\(from: ")[1]
                .split(" to: ")[1].split("\\)")[0];
        return new Event(description, isDone, startTime, endTime);
    }
}
