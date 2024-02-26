package panna;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Encapsulates all the file methods of PannaBot
 */
public class Storage {

    /**
     * Reads in the tasks from the file
     * @param filePath
     * @param tasks
     * @throws PannaException
     */
    public static void read(String filePath, TaskList tasks) throws PannaException {
        Parser df = new Parser("yyyy-MM-dd");

        try {
            File f = new File(filePath);

            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String task = s.nextLine();
                String k = s.nextLine();
                boolean done = k.equals("true");
                String desc = s.nextLine();
                Task t;

                if (task.equals("T")) {
                    t = new Todo(desc);
                    t.setDone(done);
                } else if (task.equals("D")) {
                    String deadline = s.nextLine();
                    LocalDate dt = df.parse(deadline);
                    t = new Deadline(desc, dt);
                    t.setDone(done);
                } else if (task.equals("E")) {
                    String start = s.nextLine();
                    String deadline = s.nextLine();
                    LocalDate startDate = df.parse(start);
                    LocalDate endDate = df.parse(deadline);
                    t = new Event(desc, startDate, endDate);
                    t.setDone(done);
                } else {
                    throw new PannaException("Something went wrong in panna.txt!"
                            + "Please check and make sure everything is okay");
                }
                tasks.add(t);
            }
        } catch (IOException e) {
            throw new PannaException("There is an IO Error! Be careful!");

        }

    }

    /**
     * Writes into the file after execution of a command
     * @param filePath
     * @param tasks
     * @throws PannaException
     */
    public static void write(String filePath, TaskList tasks) throws PannaException {

        try {
            FileWriter fw = new FileWriter(filePath);


            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                fw.write(t.taskType + "\n");
                fw.write(t.getDone() ? "true" : "false");
                fw.write("\n" + t.taskName + "\n");
                if (t.taskType == 'D') {
                    fw.write(t.getDeadline() + "\n");
                } else if (t.taskType == 'E') {
                    fw.write(t.getStart() + "\n");
                    fw.write(t.getEnd() + "\n");
                }
            }
            fw.close();


        } catch (IOException e) {
            throw new PannaException("Something has gone wrong with the file! Please check :D");
        }
    }
}
