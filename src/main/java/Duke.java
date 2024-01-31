import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Duke {

    private TaskList taskList;
    private Ui userInterface;
    private Storage fileStorage;

    public Duke(String FilePath) throws  DukeException,IOException{
        this.userInterface = new Ui();
        this.fileStorage = new Storage(FilePath);
    }

    public void run() throws DukeException,IOException {
        userInterface.showWelcome();
        Scanner s = new Scanner(System.in);
        if (this.fileStorage.isOccupied){
            ArrayList<Task> loadedList = fileStorage.loadStorage();
            taskList = new TaskList(loadedList);
        } else {
            taskList = new TaskList();
        }
        boolean isExit = false;
        while(!isExit){
            try {
                Command c = Parser.commandParser(s.nextLine());
                c.execute(taskList, userInterface, fileStorage);
                isExit = c.isExit();
            } catch (DukeException e) {
                userInterface.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./data/tasks.txt").run();
    }
}
