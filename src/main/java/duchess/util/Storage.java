package duchess.util;

import duchess.tasks.Deadline;
import duchess.tasks.Event;
import duchess.tasks.Task;
import duchess.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DuchessException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println(myObj.getName() + " not found");
                System.out.println("File created: " + myObj.getName() + "\n");
                throw new DuchessException();
            } else {
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    String[] input = data.split("");
                    String type = input[1];
                    String isDone = input[4];
                    String description = "";

                    for (int i = 7; i < input.length; i++) {
                        description += input[i];
                    }

                    if (type.equals("T")) {
                        Task task = new ToDo(description);
                        if (isDone.equals("X")) {
                            task.markAsDone();
                        }
                        list.add(task);
                    }
                    else if (type.equals("D")) {
                        String[] descriptionArray = description.split(" \\(by: ");
                        String name = descriptionArray[0];
                        String by = LocalDate.parse(descriptionArray[1].substring(0, descriptionArray[1].length() - 1), DateTimeFormatter.ofPattern("MMM dd yyyy")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Task task = new Deadline(name, by);
                        if (isDone.equals("X")) {
                            task.markAsDone();
                        }
                        list.add(task);
                    }
                    else if (type.equals("E")) {
                        String[] descriptionArray = description.split(" \\(from: ");
                        String name = descriptionArray[0];
                        String[] fromTo = descriptionArray[1].split(" to: ");
                        String from = LocalDate.parse(fromTo[0], DateTimeFormatter.ofPattern("MMM dd yyyy")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        String to = LocalDate.parse(fromTo[1].substring(0, fromTo[1].length() - 1), DateTimeFormatter.ofPattern("MMM dd yyyy")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Task task = new Event(name, from, to);
                        if (isDone.equals("X")) {
                            task.markAsDone();
                        }
                        list.add(task);
                    }
                }
                myReader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }

    public void save(ArrayList<Task> list) throws IOException {
        FileWriter myWriter = new FileWriter(filePath);
        for (int i = 1; i <= list.size(); i++) {
            Task t = list.get(i - 1);
            System.out.println(t);
            myWriter.write(t + "\n");
        }
        myWriter.close();
        System.out.println("\t\tSuccessfully wrote changes to file: " + filePath);
    }
}
