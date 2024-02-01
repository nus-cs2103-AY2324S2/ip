package chatbot.cortana;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.Ui;


/**
 * Represents the chatbot Cortana.
 */
public class Cortana {

    private String name = "Cortana";
    private TaskList taskList;
    // get current file dir
    private final String BASE_DIR;
    private final String SAVE_DIR_PATH;
    private final static String DATA_FOLDER = "data";
    private final static String SAVE_FILENAME = "tasks.csv";  
    private Storage storage;

    public Cortana(String baseDir) {
        this.BASE_DIR = baseDir;
        this.SAVE_DIR_PATH = java.nio.file.Paths.get(BASE_DIR, DATA_FOLDER).toString();
        this.storage = new Storage(SAVE_DIR_PATH, SAVE_FILENAME);
        this.taskList = new TaskList();
    }

    public void run() {
        try {
            this.taskList = this.storage.loadTaskList();
        } catch (IOException e) {
            Ui.output(e.getMessage());
        }
        Ui.greet(this.name);
        echo();
        Ui.bye();
    }

    /**
     * Reads the user input and executes the command.
     */
    public void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Command command = Parser.parseCommand(input);
        ArrayList<Task> tasks; 
        int numTasks;
        Task curr_task;
        String response;
        while (command != Command.BYE) {
            try {
                Parser.validateInput(command, input, this.taskList);
                switch (command) {
                case TODO:
                    curr_task = Parser.parseTodoTask(input);
                    this.taskList.addTask(curr_task);
                    response = Ui.addTaskSuccess(curr_task, this.taskList.getNumTasks());  
                    break;
                case DEADLINE:
                    curr_task = Parser.parseDeadlineTask(input);
                    this.taskList.addTask(curr_task);
                    response = Ui.addTaskSuccess(curr_task, this.taskList.getNumTasks());
                    break;
                case EVENT:
                    curr_task = Parser.parseEventTask(input);
                    this.taskList.addTask(curr_task);
                    response = Ui.addTaskSuccess(curr_task, this.taskList.getNumTasks());
                    break;
                case MARK:
                    curr_task = this.taskList.markTask(Parser.parseIndex(command, input));
                    response = Ui.markTask(curr_task);
                    break;
                case UNMARK:
                    curr_task = this.taskList.unmarkTask(Parser.parseIndex(command, input));
                    response = Ui.unmarkTask(curr_task);
                    break;
                case DELETE:
                    curr_task = this.taskList.deleteTask(Parser.parseIndex(command, input));
                    response = Ui.deleteTask(curr_task, this.taskList.getNumTasks());
                    break;
                case LIST:
                    tasks = this.taskList.getTasks();
                    numTasks = this.taskList.getNumTasks();
                    response = Ui.listTasks(tasks, numTasks);
                    break;
                default:
                    response = "I'm sorry, but I don't know what that means :-(";
                }
                if (!(this.taskList.isSaved())) {
                    this.storage.saveTaskList(this.taskList);
                }
            } catch (IOException e) {
                response = e.getMessage();
            }
            Ui.output(response);
            input = sc.nextLine();
            command = Parser.parseCommand(input);
        }
        sc.close();
    }



}
