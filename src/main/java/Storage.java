import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Storage {
    protected String path;
    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(this.path);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] words = input.split(" ");
                int isDone = Integer.parseInt(words[0]);
                String command = words[1];
                if (command.equals("todo")) {
                    String[] parts = input.split("todo", 2);
                    String description = parts[1].trim();
                    list.add(new ToDo(description, isDone));
                } else if (command.equals("deadline")) {
                    String[] parts = input.split("deadline", 2);
                    String description = parts[1].trim();
                    String[] deadlineParts = description.split("/by", 2);
                    description = deadlineParts[0].trim();
                    String deadline = deadlineParts[1].trim();
                    list.add(new Deadline(description, isDone, deadline));
                } else {
                    String[] parts = input.split("/from| /to");
                    String description = parts[0].trim().substring("0 event".length()).trim();
                    String start = parts[1].trim();
                    String end = parts[2].trim();
                    list.add(new Event(description, isDone, start, end));
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public void addToFile(String textToAdd) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(this.path, true));
        String line = "0 " + textToAdd;
        fw.write(line);
        fw.newLine();
        fw.close();
    }

    public void removeFromFile(int lineNumberToRemove) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.path));
        StringBuilder content = new StringBuilder();
        String line;
        int lineCounter = 1; // Keep track of the current line number

        // Read lines and skip the line to be deleted
        while ((line = reader.readLine()) != null) {
            if (lineCounter != lineNumberToRemove) {
                content.append(line).append(System.getProperty("line.separator"));
            }
            lineCounter++;
        }

        // Close the reader
        reader.close();

        // Write the modified content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));
        writer.write(content.toString());
        writer.close();
    }

    public void editLineInFile(int lineToEdit, int isDone) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.path));
        StringBuilder content = new StringBuilder();
        String line;
        int lineCounter = 1;

        // Read lines and modify the specified line
        while ((line = reader.readLine()) != null) {
            if (lineCounter == lineToEdit) {
                // Split the line by space
                String[] parts = line.split(" ", 2); // Split only once

                // Check if the line has at least two parts
                if (parts.length >= 2) {
                    parts[0] = String.valueOf(isDone);

                    // Reconstruct the modified line
                    String modifiedLine = String.join(" ", parts);

                    // Append the modified line to the content
                    content.append(modifiedLine).append(System.getProperty("line.separator"));
                }
            } else {
                content.append(line).append(System.getProperty("line.separator"));
            }
            lineCounter++;
        }

        // Close the reader
        reader.close();

        // Write the modified content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));
        writer.write(content.toString());
        writer.close();
    }
}
