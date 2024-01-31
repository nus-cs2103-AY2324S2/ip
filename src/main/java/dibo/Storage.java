package dibo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws DiboException {
        ArrayList<Task> taskLists = new ArrayList<>();

        try {
            File f = new File(this.filePath);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String taskDetails = sc.nextLine();
                String[] details = taskDetails.split("\\|");

                String type = details[0].trim();
                Task task;
                switch (type) {
                case "todo":
                    String description1 = details[2];
                    task = new ToDo(description1);
                    break;
                case "deadline":
                    String description2 = details[2];
                    String by = details[3].trim();
                    task = new Deadline(description2, convertDate(by));
                    break;
                case "event":
                    String description3 = details[2];
                    String start = details[3].trim();
                    String end = details[4].trim();
                    task = new Event(description3, convertDate(start), convertDate(end));
                    break;
                default:
                    throw new DiboException("Sorry sir! Unfortunately the loaded text file "
                            + "contains an invalid task type :O");
                }

                if (Integer.parseInt(details[1].trim()) == 1) {
                    task.markAsDone();
                }

                taskLists.add(task);
            }
        } catch (FileNotFoundException e) {
            File f = new File(this.filePath);
            if (f.mkdir()) {
                System.out.println("Hi sir! We have just created a new text file "
                        + "for you to store your task list :D");
            } else {
                throw new DiboException("Sorry sir! We tried a new text file "
                        + "for you to store your task list but was unable to do so.\n"
                        + "Please do us a favour and check the path name:D");
            }
        } catch (ParseException | DateTimeParseException e) {
            throw new DiboException("Oh no sir! The file seems to be corrupted :O "
                    + " The dates are not in the correct format. It ought to be: yyyy-mm-dd  :(");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DiboException("Oh no sir! The file seems to be corrupted :O "
                    + " You might want to take a look at the formatting of the text file :(");
        }

        return taskLists;
    }

    public void save(TaskList taskList) throws DiboException {
        try {
            String updatedData = taskList.getSaveFormat();
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(updatedData);
            fw.close();
        } catch (IOException e) {
            throw new DiboException("Oh no sir! We are unable to update the task lists.\n"
                    + "We are terminating the chatbot :(. Check the file again and restart.\n"
                    + "We will be waiting for you here :D.");
        }
    }

    private String convertDate(String date) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("MMM d yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObject = originalFormat.parse(date);
        return targetFormat.format(dateObject);

    }


}
