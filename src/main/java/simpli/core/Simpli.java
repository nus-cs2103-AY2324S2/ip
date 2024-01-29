package simpli.core;

import simpli.actions.Action;
import simpli.configs.SimpliConfiguration;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.tasks.Deadline;
import simpli.tasks.Event;
import simpli.tasks.Task;
import simpli.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Simpli {
    private final ArrayList<Task> tasks;
    private final Parser parser;
    private final Interpreter intrpr;

    public Simpli() {
        this.tasks = new ArrayList<>();
        this.parser = new Parser();
        this.intrpr = new Interpreter(this);
    }
    public void start() {
        // welcome message
        greet();

        try {
            loadSavedTasksfromFile();
        } catch (IOException e) {
            respond("The file is corrupted :(");
        } catch (ActionException e) {
            respond("There is something wrong with the data/simpli.csv file :(");
        }

        Scanner scanner = new Scanner(System.in);
        String userIn = scanner.nextLine();

        // Performing actions from user input
        while (!userIn.equals("bye")) {
            String[] tokens = parser.parseCommand(userIn);

            try {
                intrpr.interpret(tokens);
            } catch (TaskException e) {
                respond("Invalid task parameters, cannot simp :(");
            } catch (IndexOutOfBoundsException e) {
                respond("Please enter a valid task number for me to simp :(");
            }
            catch (ActionException e) {
                respond("Command is missing parameters, unable to simp :(");
            } catch (IllegalArgumentException e) {
                respond("No such command to simp for :(");
            }

            userIn = scanner.nextLine();
        }

        try {
            saveTasktoFile();
        } catch (IOException e) {
            respond("File is corrupted :(");
        }

        // goodbye message
        bye();
    }

    public void saveTasktoFile() throws IOException {
        File f = new File("./data/simpli.csv");

        if (!f.isFile() && f.getParentFile().mkdir() && !f.createNewFile()) {
            throw new IOException();
        }

        FileWriter fileWriter = new FileWriter("./data/simpli.csv", false);
        for (Task task : tasks) {
            fileWriter.write(task.toCsv() + "\n");
        }
        fileWriter.close();
    }

    public void loadSavedTasksfromFile() throws IOException, ActionException{
        File f = new File("./data/simpli.csv");

        if (!f.isFile() && f.getParentFile().mkdir() && !f.createNewFile()) {
            throw new IOException();
        }

        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String[] tokens = parser.parseCsv(scanner.nextLine());

            intrpr.interpret(tokens);
        }

    }

    public void list() {
        StringBuilder strItems = new StringBuilder();
        for (int i = 0; i < tasks.size() - 1; i++) {
            strItems.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        if (!tasks.isEmpty()) {
            strItems.append(String.format("%d. %s", tasks.size(), tasks.getLast()));
        }

        respond(strItems.toString());
    }

    public void mark(int taskNum) {
        int itemIndex = taskNum - 1;
        tasks.get(itemIndex).done();

        respond(
                "Nice! I've marked this task as done:\n\t" +
                        tasks.get(itemIndex)
        );
    }

    public void unmark(int taskNum) {
        int itemIndex = taskNum - 1;
        tasks.get(itemIndex).undone();

        respond(
                "OK, I've marked this task as not done yet:\n\t" +
                        tasks.get(itemIndex)
        );
    }

    public void addTodo(String[] tokens) {
        Todo todo = new Todo(tokens[1].equals("1"), tokens[2]);
        tasks.add(todo);
        respond(
                "Got it. I've added this task:\n\t" +
                        todo + "\n" +
                        "Now you have " + tasks.size() + " task(s) in the list."
        );
    }

    public void addDeadline(String[] tokens) {
        Deadline deadline = new Deadline(tokens[1].equals("1"), tokens[2], tokens[4]);
        tasks.add(deadline);
        respond(
                "Got it. I've added this task:\n\t" +
                        deadline + "\n" +
                        "Now you have " + tasks.size() + " task(s) in the list."
        );
    }

    public void addEvent(String[] tokens) {
        Event event = new Event(tokens[1].equals("1"), tokens[2], tokens[4], tokens[6]);
        tasks.add(event);
        respond(
                "Got it. I've added this task:\n\t" +
                        event + "\n" +
                        "Now you have " + tasks.size() + " task(s) in the list."
        );
    }

    public void deleteTask(int taskNum) {
        Task removedTask = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        respond(
                "Noted. I've removed this task:\n" +
                        removedTask + "\n" +
                        "Now you have " + tasks.size() + " task(s) in the list."
        );
    }

    public void respond(String content) {
        String msg = String.format(SimpliConfiguration.PLACEHOLDER, content.replace("\n", "\n\t\t"));

        System.out.println(msg);
    }

    public void greet() {
        respond(
                "Hello! I'm SIMP-LI\n" +
                        "How can I simp-lify your life?"
        );
    }

    public void bye() {
        respond("Bye. Hope to simp for you again soon!");
    }

    @Override
    public String toString() {
        return "SIMP-LI";
    }
}
