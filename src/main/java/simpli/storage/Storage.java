package simpli.storage;

import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.tasks.Task;
import simpli.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private Parser parser;
    private TaskList taskList;
    private Interpreter intrpr;

    public Storage(Parser parser, Interpreter intrpr, TaskList taskList) {
        this.parser = parser;
        this.intrpr = intrpr;
        this.taskList = taskList;
    }

    public void saveTasksToFile(String path) throws IOException {
        File f = new File("./data/simpli.csv");

        if (!f.isFile() && f.getParentFile().mkdir() && !f.createNewFile()) {
            throw new IOException();
        }

        FileWriter fileWriter = new FileWriter(path, false);
        for (Task task : taskList.tasks()) {
            fileWriter.write(task.toCsv() + "\n");
        }
        fileWriter.close();
    }

    public void loadTasksFromFile(String path) throws IOException, ActionException, TaskException {

        File f = new File(path);

        if (!f.isFile() && f.getParentFile().mkdir() && !f.createNewFile()) {
            throw new IOException();
        }

        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String[] tokens = parser.parseCsv(scanner.nextLine());

            intrpr.interpret(tokens);
        }
    }
}
