package jojo;

import exceptions.JojoException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A CLI chatbot with the ability to save tasks.
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
            System.out.println(ui.showWelcomeMessage());
            System.out.println(storage.printList());
            System.out.println(ui.showStartingQn());
            System.out.println(ui.breakLines());
            Scanner sc = new Scanner(System.in);
            String cmd = sc.nextLine();
            while (!cmd.equals("bye")) {
                getResponse(cmd);
                String store_str = storage.storeList(tasks);
                storage.save(store_str);
                System.out.println(ui.breakLines());
                cmd = sc.nextLine();
            }
            System.out.println(ui.showExitMessage());
            System.out.println(ui.breakLines());
    }

    public void saveTasks() throws JojoException {
        String store_str = storage.storeList(tasks);
        storage.save(store_str);
    }

    public String getResponse(String input) throws JojoException {
        String response = Parser.parse(input, ui, tasks, storage);
        System.out.println(response);
        return response;
    }

    public String getStartingMsg() throws JojoException {
        StringBuilder msg = new StringBuilder();
        msg.append(ui.showWelcomeMessage());
        msg.append(System.getProperty("line.separator"));
        msg.append(storage.printList());
        msg.append(System.getProperty("line.separator"));
        msg.append(ui.showStartingQn());
        return msg.toString();
    }

    public static void main(String[] args) {
        try {
            new Jojo("jojo.txt").run();
        } catch (JojoException e) {
            System.out.println(e.getMessage());
        }
    }
}

