import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.*;


public class Duke {
    private String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(filePath);
    }

    public void run() {
        String line = "------------------------------";
        Scanner obj = new Scanner(System.in);
        System.out.println("\n Hello! I'm Leo\n" +
                " What can I do for you?");
        System.out.println(line);

        while(obj.hasNextLine()){
            String res = obj.nextLine();
            if (res.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            }

            Parser parser = new Parser(res);
            parser.execute(tasks, ui);


        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
