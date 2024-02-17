package duke;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;


/**
 * Main chatbot program
 */

public class Duke{
    private static final String FILE_PATH = "data/duke.txt";
    private Ui ui;
    //List<Task> list;
    private TaskList tlist;

    private Storage storage = new Storage();

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
    /**
     * Constructs an instance of Duke
     */

    public Duke() {
        ui = new Ui();
        tlist = new TaskList();

    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }

    /**
     * Runs Chatbot
     * @throws DukeException
     */
    private void run() throws DukeException{

        Ui Ui = new Ui();
        Scanner scanner = new Scanner(System.in);
        storage.load(tlist);


        Ui.greet();

        while (scanner.hasNextLine()) {
            String comd = scanner.nextLine();

            if (comd.equals("bye")) {
                break;
            }
            if (comd.equals("list")) {

                tlist.listTasks();
            }

            else if (comd.startsWith("todo ")) {
                if (comd.length() <= 5) {
                    throw new DukeException("Empty Description");
                }
                tlist.addTask(comd);

                try {
                    storage.addData(FILE_PATH, new Todo(comd.substring(5)).toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (comd.startsWith("deadline ")) {
                if (comd.length() <= 9) {
                    throw new DukeException("Empty Description");
                }

                tlist.addTask(comd);
                InputHandler handler = new InputHandler();
                String[] data = comd.split("/");

                LocalDate deadlineDate = handler.formatDeadline(data);

                String task = data[0].substring(9);
                Deadline d = new Deadline(task, deadlineDate);

                try {
                    storage.addData(FILE_PATH, d.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (comd.startsWith("event ")) {
                if (comd.length() <= 6) {
                    throw new DukeException("Empty Description");
                }
                tlist.addTask(comd);
                String[] data = comd.split("/");
                String task = data[0].substring(6);

                Event e = new Event(task, data[1].substring(5), data[2].substring(3));

                try {
                    storage.addData(FILE_PATH, e.toString() + System.lineSeparator());
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
            }

            else if(comd.startsWith("mark ")) {
                tlist.markTask(comd);

                try {
                    storage.write(FILE_PATH, tlist.getList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }

            else if (comd.startsWith("unmark ")) {
                tlist.unmarkTask(comd);

                try {
                    storage.write(FILE_PATH, tlist.getList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (comd.startsWith("delete ")) {
                tlist.deleteTask(comd);

                try {
                    storage.write(FILE_PATH, tlist.getList());
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
            }

            else if (comd.startsWith("find ")) {
                tlist.findTask(comd);
            }
            else {
                throw new DukeException("Invalid Command!");
            }

        }
        System.out.print("Bye. Hope to see you again soon!");
    }




}
