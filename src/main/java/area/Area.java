package area;

import java.util.Scanner;

/**
 * Represents the bot. An Area object corresponds to the chatbot.
 */
public class Area {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * creates a Duke object with the filepath as a parameter
     * 
     * @param filePath
     * @throws Exception
     */
    public Area(String filePath) throws Exception {
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
        boolean isChatting = true;
        Parser parser = new Parser();
        while (isChatting) {
            instruction = sc.nextLine();
            String command = parser.parseCommand(instruction);
            try {
                if (command.equals("bye")) {
                    storage.saveTasks();
                    // command to end chat with chatbot
                    ui.endChat();
                    isChatting = false;
                    break;
                } else if (command.equals("list")) {
                    ui.showList(tasks.getTaskList());
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
                }else if (command.equals("find")){
                    String keyword = parser.parseKeyword(instruction);
                    tasks.findTask(keyword);
                }
            } catch (Exception e) {
                System.out.println(e.toString() + "\n");
            }
        }
        sc.close();

    }

    /**
     * main to start chatbot program
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new Area("./data/duke.txt").run();
    }

}
