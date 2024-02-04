package simpli.storage;

import simpli.exceptions.ActionException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.tasks.Task;
import simpli.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final Parser parser;
    private final TaskList taskList;
    private final Interpreter intrpr;

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

    public void loadTasksfromFile(String path) throws IOException, ActionException {

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
