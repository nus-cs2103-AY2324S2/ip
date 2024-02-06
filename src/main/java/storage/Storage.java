package storage;

import exceptions.FileAccessError;
import exceptions.FileError;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Storage {
    private final String fileName;

    public Storage(String fileName) {

        this.fileName = fileName;
    }

    public void write(ArrayList<Task> data) throws FileError {
        try {
            FileWriter writer = new FileWriter(fileName, false);
            writer.write(convertArrayToStr(data) + "\n");
            writer.close();
        } catch (IOException e) {
            throw new FileError("Problem writing to file!");
        }
    }

    public ArrayList<Task> read() throws FileError, FileAccessError {
        ArrayList<String> contents = new ArrayList<>();
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                contents.add(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new FileError("Problem reading from file!");
        }
        String[] data = contents.toArray(new String[0]);
        return convertStrToArray(data);
    }

    private ArrayList<Task> convertStrToArray(String[] data) throws FileAccessError {
        ArrayList<Task> taskListArray = new ArrayList<>();
        for (String datum : data) {
            try {
                String[] splitData = datum.split(" \\| ");
                String type = splitData[0];
                Boolean isMarked = Objects.equals(splitData[1], "1");
                String description = splitData[2];
                Task toAdd;
                switch (type) {
                case "T":
                    toAdd = new ToDo(description, isMarked);
                    break;
                case "D":
                    toAdd = new Deadline(description, isMarked, splitData[3]);
                    break;
                case "E":
                    toAdd = new Event(description, isMarked, splitData[3], splitData[4]);
                    break;
                default:
                    throw new FileAccessError("Error reading from data.txt");
                }
                taskListArray.add(toAdd);
            } catch (IndexOutOfBoundsException e) {
                throw new FileAccessError("Error reading from data.txt");
            }
        }
        return taskListArray;
    }

    private String convertArrayToStr(ArrayList<Task> tasks)  {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(tasks.get(i).prepareForStorage());
            if (i < tasks.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
