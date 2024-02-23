package area;

import java.util.Scanner;

/**
 *
 * Main class.
 */
public class Area {
    public final String Storage = null;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Area object
     * 
     *
     *
     */
    public Area() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(tasks);
        this.storage.createFile();
        this.storage.createFolder();
        this.storage.loadTasks();
    }

    /**
     * Takes in an instruction and execute respective command. If instruction is not valid, function throws an exception.
     * @param instruction
     * @return
     */
    public String allotTasks(String instruction) {
        Parser parser = new Parser();
        String command = parser.parseCommand(instruction);
        String result = "";
        try {
            if (command.equals("bye")) {
                // command to end chat with chatbot
                result = ui.endChat();
            } else if (command.equals("list")) {
                storage.saveTask(instruction);
                result = ui.showList(tasks.getTaskList());
            } else if (command.equals("todo")) {
                storage.saveTask(instruction);
                result = tasks.addTask(instruction);
                storage.addInstruction(instruction);
            } else if (command.equals("deadline")) {
                storage.saveTask(instruction);
                result = tasks.addTask(instruction);
                storage.addInstruction(instruction);
            } else if (command.equals("event")) {
                storage.saveTask(instruction);
                result = tasks.addTask(instruction);
                storage.addInstruction(instruction);
            } else if (command.equals("mark")) {
                storage.saveTask(instruction);
                result = tasks.modifyTask(instruction);
                storage.addInstruction(instruction);
            } else if (command.equals("unmark")) {
                storage.saveTask(instruction);
                result = tasks.modifyTask(instruction);
                storage.addInstruction(instruction);
            } else if (command.equals("delete")) {
                storage.saveTask(instruction);
                result = tasks.modifyTask(instruction);
            } else if (command.equals("find")) {
                storage.saveTask(instruction);
                String keyword = parser.parseKeyword(instruction);
                result = tasks.findTask(keyword);
            } else if(command.equals("priority")){
                storage.saveTask(instruction);
                tasks.modifyTask(instruction);
            }
        } catch (Exception e) {
            storage.deleteIncorrectInstruction();
            return e.getMessage() + "\n";
        }
        return result;
    }

    /**
     * returns the greeting message as well as the list of tasks if any.
     * @return String of greeting
     */
    public String greetUser() {
        return ui.greetUser() + ui.showList(tasks.getTaskList());
    }

    /**
     * takes in an input which is an instruction that when run through function allotTasks will return the respective output.
     * @param input
     * @return the respective output based on input
     */
    String getResponse(String input) {
        assert input != null : "input cannot be null";
        return allotTasks(input);
    }

}
