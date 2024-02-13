package duke;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main chatbot program
 */

public class Duke{
    private static final String FILE_PATH = "data/duke.txt";
    private Ui ui;
    //List<Task> list;
    TaskList tlist;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));







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
        File f = new File(FILE_PATH);

        try {
            if (!f.exists()) {

                if (f.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("Unable to create file");
                }
            } else {
                System.out.println("Loading data...");
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }

        try {
            tlist.retrieveData(f);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
                    addData(FILE_PATH, new Todo(comd.substring(5)).toString() + System.lineSeparator());
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
                    addData(FILE_PATH, d.toString() + System.lineSeparator());
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
                    addData(FILE_PATH, e.toString() + System.lineSeparator());
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
            }

            else if(comd.startsWith("mark ")) {
                tlist.markTask(comd);

                try {
                    write(FILE_PATH, tlist.getList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }

            else if (comd.startsWith("unmark ")) {
                tlist.unmarkTask(comd);

                try {
                    write(FILE_PATH, tlist.getList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (comd.startsWith("delete ")) {
                tlist.deleteTask(comd);

                try {
                    write(FILE_PATH, tlist.getList());
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

    private static List<Task> retrieveData(File file) throws IOException {
        List<Task> loadedList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while(fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                loadedList.add(Task.parser(taskData));
            }
        }

        if (loadedList.isEmpty()) {
            loadedList = new ArrayList<>();
        }

        return loadedList;
    }

    private static void write(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            fw.write(t.toString() + System.lineSeparator());

        }
        fw.close();
    }

    private static void addData(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
