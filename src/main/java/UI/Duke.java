package UI;

import Actions.Action;
import Actions.Greet;
import ExceptionHandling.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static Parser.Parser.parseCommand;


public class Duke {
    private String name;

    private TaskList taskList;
    private Storage storage;


    public Duke(String name) {
        this.name = name;
        this.taskList = new TaskList(new ArrayList<>());
        this.storage = new Storage();
    }

    public String getName() {
        return this.name;
    }

    public void exit() {
        this.storage.save(this.taskList);
        System.out.println("Bye. Hope to see you again soon!\n");
        System.exit(0);
    }

    public TaskList getTaskList() {
        return this.taskList;
    }


    public static void main(String[] args) {
        Duke chatbot = new Duke("Bob");
        chatbot.storage.load(chatbot);
        Greet g = new Greet();
        g.execute(chatbot);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            try {
                Action action = parseCommand(command);
                action.execute(chatbot);
            } catch (DukeException e) {
            System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
            System.out.println("Please enter a number as your index");
            }
        }
    }


}

