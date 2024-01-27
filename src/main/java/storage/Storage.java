package storage;

import ui.UI;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Storage {
    private String filePath;
    private File file;
    private Path path;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.path = Path.of(this.filePath);
    }

    public List<String> retrieveLines() {
        List<String> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            UI.print("Invalid file path provided, session will not be saved.");
        }
        return result;
    }

    public void writeLine(String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true));
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            UI.print(e.getMessage());
        }
    }

    public String readLine(int index) {
        try {
            Scanner scanner = new Scanner(this.file);
            int i = 0;
            String line = "";
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (i == index) {
                    return line;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            UI.print(e.getMessage());
        }
        return null;
    }

    public void updateLine(int index, String data) {
        try {
            List<String> lines = Files.readAllLines(this.path);
            lines.set(index, data);
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, false));
            writer.write("");
            writer = new BufferedWriter(new FileWriter(this.file, true));
            for (String str : lines) {
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            UI.print(e.getMessage());
        }
    }

    public void deleteLine(int index) {
        try {
            List<String> lines = Files.readAllLines(this.path);
            lines.remove(index);
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, false));
            writer.write("");
            writer = new BufferedWriter(new FileWriter(this.file, true));
            for (String str : lines) {
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            UI.print(e.getMessage());
        }
    }

}
