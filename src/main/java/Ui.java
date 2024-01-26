import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Handles the main chat listening and parsing of messages
public class Ui {
    private Scanner sc = new Scanner(System.in);
    //Handles adding/removing to array of tasks
    private TaskList taskList = new TaskList();
    private String filePath = "data/task_lists.txt";
    //Handles the loading and saving of tasks to text file
    private Storage taskLoader = new Storage(filePath);

    public void chatListener() {
        //First attempt to load the file.
        try {
            ArrayList<Task> parsedTasks = this.taskLoader.parseAndLoadTasks();
            this.taskList = new TaskList(parsedTasks);
        } catch (RyanGoslingException | FileNotFoundException e) {
            MessagePrinter.errorPrinter(e);
            return;
        }
        CommandsParser commandsParser = new CommandsParser(this.taskList, this.filePath, this.taskLoader);

        //Begin parsing commands.
        while (true) {
            String task = sc.nextLine();
            int status = 0;
            try {
                status = commandsParser.parseCommands(task);
            } catch (Exception e){
                MessagePrinter.errorPrinter(e);
            } finally {
                if (status == 1) {
                    return;
                }
            }
        }
    }
}
