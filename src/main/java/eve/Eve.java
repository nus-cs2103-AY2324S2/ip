package eve;

import eve.parser.Commands;
import eve.parser.Parser;
import eve.fileStorage.Storage;
import eve.TaskList;
import eve.tasks.Task;
import java.util.ArrayList;


public class Eve{
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Parser parser;
    private Ui ui;

    public Eve(){
        this.storage = new Storage();

        try {
            storage.loadFileContents(tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.parser = new Parser(this);
    }

    public void sayHello() {
        Commands.commandHello();
    }
 

    //need to implement getResponse
    public String getResponse(String input) {
        try{
            String response = parser.parseAndExecute(input);
            //save tasks to file
            Storage.writeToFile(tasks);
            return response;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
