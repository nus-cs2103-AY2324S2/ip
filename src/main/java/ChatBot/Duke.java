package ChatBot;

import Actions.Action;
import ExceptionHandling.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

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

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\nWhat can I do for you?\n");
    }

    public void exit() {
        this.saveToDoList("src/main/duke.txt");
        System.out.println("Bye. Hope to see you again soon!\n");
        System.exit(1);
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public void saveToDoList(String filename){
        try{
            FileWriter dest = new FileWriter(filename);
            for (Task t : this.taskList.getList()) {
                dest.write(t.getCommand());
            }
            dest.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadToDoList(String filename){
        try {
            File f = new File(filename);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String command = reader.nextLine();
                String isDone = reader.nextLine();
                Action a = parseCommand(command);
                a.execute(this);
                if (Objects.equals(isDone, "true")) {
                    this.getTaskList().markTask(this.getTaskList().getListSize());
                }
            }
            System.out.println("Loading Done!");
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Duke chatbot = new Duke("Bob");
        chatbot.loadToDoList("src/main/duke.txt");
        chatbot.greet();
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

