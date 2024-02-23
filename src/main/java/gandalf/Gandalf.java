package gandalf;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for the Gandalf chatbot
 */
public class Gandalf {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Gandalf() {

    }

    /**
     * Takes in two paths as it uses two files for its store/load feature. One file is for loading any existing lists,
     * and another file is meant to be readable in a .txt file
     * @param filePathMeta
     * @param filePathRead
     */

    public Gandalf(String filePathMeta, String filePathRead) {
        ui = new Ui();
        storage = new Storage(filePathMeta, filePathRead);
        try {
            tasks = new TaskList(storage.load());
        } catch (GandalfException e) {
            //file does not exist, create new list
            tasks = new TaskList();
        }
    }
    public void find(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        int numOfFiltered = 0;
        for(int i = 0; i < tasks.getList().size(); i++) {
            Task action = tasks.getList().get(i);
            String nameOfTask = action.getNameOfTask();
            if(nameOfTask.contains(keyword)) {
                filteredList.add(numOfFiltered, action);
                numOfFiltered++;
            }
        }
        for(int i = 0; i < filteredList.size(); i++) {
            Task action = filteredList.get(i);
            System.out.println((i + 1) + ". " + action);
        }
    }
    /**
     * Function to run the chatbot, uses a while-loop to constantly allow the chatbot to receive new inputs
     * Also processes inputs to do various things depending on the command
     */
    public void run() {
        ui.welcome();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if(input.length() == 0) { //ignore accidental new lines from user
                continue;
            }
            Parser parser = new Parser(input);
            StringBuilder[] parsedInput = parser.interpret(); //parsedInput = {taskType, taskName, date1, date2}
            if(parsedInput[0].toString().trim().equals("bye")) {
                scanner.close();
                ui.bye();
                break;
            }
            else if(parsedInput[0].toString().trim().equals("list")) {
                for(int i = 0; i < tasks.getList().size(); i++){
                    Task action = tasks.getList().get(i);
                    System.out.println((i + 1) + ". " + action);
                }
                System.out.println("Total number of tasks so far: " + (tasks.getList().size()));
            }
            else if(parsedInput[0].toString().trim().equals("delete")){
                tasks.delete(parsedInput[1].toString().trim());
                storage.store(tasks.getList());
            }
            else if(parsedInput[0].toString().trim().equals("mark")) {
                int taskNumber = Integer.parseInt(parsedInput[1].toString());
                ui.marked();
                tasks.mark(taskNumber);
                storage.store(tasks.getList());
            }
            else if(parsedInput[0].toString().trim().equals("unmark")) {
                int taskNumber = Integer.parseInt(parsedInput[1].toString());
                ui.unmarked();
                tasks.unmark(taskNumber);
                storage.store(tasks.getList());
            } else if(parsedInput[0].toString().trim().equals("find")) {
                String keyword = parsedInput[1].toString().trim();
                find(keyword);
            }
            else {
                try {
                    tasks.add(parsedInput[0].toString().trim(), parsedInput[1].toString().trim(), parsedInput[2].toString().trim(), parsedInput[3].toString().trim());
                    storage.store(tasks.getList());
                    System.out.println("Total number of tasks so far: " + (tasks.getList().size()));
                }
                catch(GandalfException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public String getResponse(String input) {
        return "Gandalf heard: " + input;
    }
    public static void main(String[] args) {
        new Gandalf("docs/gandalfMeta.txt", "docs/gandalfRead.txt").run();
    }
}
