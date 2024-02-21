package jojo;

import exceptions.JojoException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chatbot with the ability to save tasks.
 */
public class Jojo {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Jojo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JojoException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the program.
     * @throws JojoException when an invalid command is given.
     */
    public void run() throws JojoException {
            System.out.println(getStartingMsg());
            System.out.println(ui.breakLines());
            Scanner sc = new Scanner(System.in);
            String cmd = sc.nextLine();
            while (!cmd.equals("bye")) {
                try {
                    getResponse(cmd);
                } catch(JojoException e){
                    System.out.println(e.getMessage());
                }
                saveTasks();
                System.out.println(ui.breakLines());
                cmd = sc.nextLine();
            }
            System.out.println(ui.showExitMessage());
            System.out.println(ui.breakLines());
    }

    /**
     * Saves the tasks to the hardcoded file
     * @throws JojoException when an invalid command is given.
     */
    public void saveTasks() throws JojoException {
        String store_str = storage.storeList(tasks);
        storage.save(store_str);
    }

    /**
     * Returns the response by Jojo
     * @param input from the user
     * @return String
     * @throws JojoException when an invalid command is given.
     */
    public String getResponse(String input) throws JojoException {
        String response = Parser.parse(input, ui, tasks);
        System.out.println(response);
        return response;
    }

    /**
     * Returns the starting message
     * @return String
     * @throws JojoException when an invalid command is given.
     */
    public String getStartingMsg() throws JojoException {
        StringBuilder msg = new StringBuilder();
        msg.append(ui.showWelcomeMessage());
        msg.append(System.getProperty("line.separator"));
        msg.append(storage.printList());
        msg.append(System.getProperty("line.separator"));
        msg.append(ui.showStartingQn());
        return msg.toString();
    }

    public static void main(String[] args) throws JojoException{
        new Jojo("jojo.txt").run();
    }
}

