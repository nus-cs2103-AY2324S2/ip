package pingmebot;

import pingmebot.task.Deadline;
import pingmebot.task.Events;
import pingmebot.task.Task;
import pingmebot.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class fileStorage {
    protected File myFile;
    protected String filePath;
    public fileStorage(String filePath) throws myBotException {
        this.myFile = new File(filePath);
        this.filePath = filePath;
        if (!myFile.exists()) {
            try {
                if (this.myFile.createNewFile()) {
                    System.out.println("File has been successfully created");
                } else {
                    System.out.println("Error creating file...");
                }
            } catch (IOException e) {
                throw new myBotException(e.getMessage());
            }
        }
    }

    public ArrayList<Task> bootingUp() throws myBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (this.myFile.length() == 0) {
            tasks = new ArrayList<>();
        } else {
            try {
                Scanner sc = new Scanner(this.myFile);
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    String[] segmentedText = text.split("\\|");

                    if (segmentedText[0].trim().equals("todo")) {
                        int isTaskCompleted = Integer.parseInt(segmentedText[1].trim());
                        String description = segmentedText[2].trim();
                        ToDos todo = new ToDos(description);
                        if (isTaskCompleted == 1) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } else if (segmentedText[0].trim().equals("deadline")) {
                        int isTaskCompleted = Integer.parseInt(segmentedText[1].trim());
                        String description = segmentedText[2].trim();
                        String deadlineTime = segmentedText[3].trim();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
                        LocalDateTime parsedDateTime = LocalDateTime.parse(deadlineTime, formatter);
                        Deadline deadline = new Deadline(description, parsedDateTime);
                        if (isTaskCompleted == 1) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);

                    } else if (segmentedText[0].trim().equals("event")) {
                        int isTaskCompleted = Integer.parseInt(segmentedText[1].trim());
                        String description = segmentedText[2].trim();
                        String from = " " + segmentedText[3].trim();
                        String to = " " + segmentedText[4].trim();
                        Events event = new Events(description,from,to);
                        if (isTaskCompleted == 1) {
                            event.markAsDone();
                        }
                        tasks.add(event);

                    } else {
                        throw new myBotException("Weird expression found!");
                    }
                }

            } catch (FileNotFoundException e) {
                throw new myBotException(e.getMessage());
            }
        }
        return tasks;
    }

    public void updateFile(ArrayList<Task> tasks) throws myBotException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : tasks) {
                int isCompleted = t.hasCompleted();
                if (t instanceof ToDos) {
                    String toWrite = ((ToDos) t).updateToDoText(isCompleted);
                    fw.write(toWrite + System.lineSeparator());
                } else if (t instanceof Deadline) {
                    String toWrite = ((Deadline) t).updateDeadlineText(isCompleted);
                    fw.write(toWrite + System.lineSeparator());
                } else if (t instanceof Events) {
                    String toWrite = ((Events) t).updateEventText(isCompleted);
                    fw.write(toWrite + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new myBotException("There is not file to be updated!");
        }

    }

}


