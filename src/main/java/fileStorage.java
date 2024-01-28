import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                if (this.myFile.getParentFile().mkdirs()) {
                    System.out.println("Directory has been successfully created");
                } else {
                    System.out.println("Error creating directory...");
                }

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
                        // Format for Todo: T | 1/0 | read book
                        int isTaskCompleted = Integer.parseInt(segmentedText[1].trim());
                        String description = segmentedText[2].trim();
                        ToDos todo = new ToDos(description);
                        if (isTaskCompleted == 1) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } else if (segmentedText[0].trim().equals("deadline")) {
                        // Format for Deadline: D | 1/0 | read book | Date/Time
                        int isTaskCompleted = Integer.parseInt(segmentedText[1].trim());
                        String description = segmentedText[2].trim();
                        String deadlineTime = " " + segmentedText[3].trim();
                        Deadline deadline = new Deadline(description, deadlineTime);
                        if (isTaskCompleted == 1) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);

                    } else if (segmentedText[0].trim().equals("event")) {
                        // Format for Event: E | 1/0 | read book | Date/Time(from) | Date/Time(To)
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
                int isCompleted = t.isDone ? 1 : 0;
                if (t instanceof ToDos) {
                    String toWrite = "todo | " + isCompleted + " | " + t.description;
                    fw.write(toWrite + System.lineSeparator());
                } else if (t instanceof Deadline) {
                    String toWrite = "deadline | " + isCompleted + " | " + t.description + " | " + ((Deadline) t).by;
                    fw.write(toWrite + System.lineSeparator());
                } else if (t instanceof Events) {
                    String toWrite = "event | " + isCompleted + " | " + t.description + " | " + ((Events) t).start + " | " + ((Events) t).end;
                    fw.write(toWrite + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new myBotException("There is not file to be updated!");
        }

    }

}


