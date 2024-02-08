import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws Exception {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath,
                tasks);
        this.storage.createFile();
        this.storage.createFolder();
        this.storage.loadTasks();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        // instruction for the chatbot to follow
        String instruction = "";
        ui.greetUser();
        boolean chatting = true;
        Parser parser = new Parser();
        while (chatting) {
            instruction = sc.nextLine();
            String command = parser.parseCommand(instruction);
            try {
                if (command.equals("bye")) {
                    storage.saveTasks();
                    // command to end chat with chatbot
                    ui.endChat();
                    chatting = false;
                    break;
                } else if (command.equals("list")) {
                    ui.showList(tasks);
                } else if (command.equals("todo")) {
                    tasks.addTask(instruction);
                    storage.addInstruction(instruction);
                } else if (command.equals("deadline")) {
                    tasks.addTask(instruction);
                    storage.addInstruction(instruction);
                } else if (command.equals("event")) {
                    tasks.addTask(instruction);
                    storage.addInstruction(instruction);
                } else if (command.equals("mark")) {
                    tasks.modifyTask(instruction);
                    storage.addInstruction(instruction);
                } else if (command.equals("unmark")) {
                    tasks.modifyTask(instruction);
                    storage.addInstruction(instruction);
                } else if (command.equals("delete")) {
                    tasks.modifyTask(instruction);
                }
            } catch (Exception e) {
                System.out.println(e.toString() + "\n");
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new Duke("./data/duke.txt").run();
    }

}
