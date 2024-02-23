package javassist.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javassist.exception.JavAssistException;
import javassist.task.Deadline;
import javassist.task.Event;
import javassist.task.Task;
import javassist.task.Todo;


/**
 * Represents a storage mechanism.
 */
public class Storage {
    private static final String SEPARATOR = " \\| ";
    private File taskFile;

    private File expenseFile;
    private FileWriter fw;
    private Scanner s;

    /**
     * Constructor to create an instance that perform IO operations on file in parent.
     *
     * @param file Name of file to write to.
     */
    public Storage(String file, String expenseFile) throws IOException {
        this.taskFile = new File(file);
        this.expenseFile = new File(expenseFile);
        Path taskPath = Paths.get(file);
        Path taskParentDir = taskPath.getParent();
        if (taskParentDir != null) {
            if (!Files.exists(taskParentDir)) {
                Files.createDirectories(taskParentDir);
            }
        }

        Path expensePath = Paths.get(expenseFile);
        Path expenseParentDir = expensePath.getParent();
        if (expenseParentDir != null) {
            if (!Files.exists(expenseParentDir)) {
                Files.createDirectories(expenseParentDir);
            }
        }
    }

    /**
     * Writes String of tasks to file.
     *
     * @param list List of tasks to be written.
     */
    public void writeToFile(TaskList list) {
        try {
            this.fw = new FileWriter(taskFile);
            String data = write(list);
            this.fw.write(data);
            this.fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns list of Tasks in readable format.
     *
     * @param taskList Holds the list of tasks.
     * @return Structured String of all Tasks in taskList.
     */
    private String write(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i < list.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Writes String of expenses to file.
     *
     * @param list List of expenses to be written.
     */
    public void writeExpensesToFile(ExpenseList list) {
        try {
            this.fw = new FileWriter(expenseFile);
            String data = list.toString();
            this.fw.write(data);
            this.fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns ArrayList of tasks read from file.
     *
     * @return ArrayList of Task.
     * @throws JavAssistException If file not found due to no existing data saved.
     */
    public ArrayList<Task> readFromFile() throws JavAssistException {
        ArrayList<Task> list = new ArrayList<Task>();
        if (taskFile.exists()) {
            try {
                this.s = new Scanner(this.taskFile);
                while (this.s.hasNext()) {
                    list.add(read(this.s.nextLine()));
                }
            } catch (FileNotFoundException e) {
                throw new JavAssistException(e.getMessage());
            }
        }
        return list;
    }

    /**
     * Converts String into corresponding instances of Task.
     *
     * @param s Describes a Task.
     * @return Task instance created based on s.
     */
    private Task read(String s) {
        String[] cols = s.split(SEPARATOR);
        assert (cols.length <= 2 || cols.length > 5) : "Line read from file is not in correct format";
        Task t = null;
        String description = cols[2];
        if (cols.length == 3) {
            t = new Todo(description);
        } else if (cols.length == 4) {
            t = new Deadline(description, cols[3]);
        } else if (cols.length == 5) {
            try {
                t = new Event(description, cols[3], cols[4]);
            } catch (JavAssistException e) {
                // skip this task
                e.getMessage();
            }
        }
        if (cols[1].equals("1")) {
            t.markAsDone();
        } else {
            t.markAsNotDone();
        }

        return t;
    }

    /**
     * Returns ExpenseList of expenses read from file.
     *
     * @return ExpenseList.
     * @throws JavAssistException If file not found due to no existing data saved.
     */
    public ExpenseList readFromExpenseFile() throws JavAssistException {
        if (expenseFile.exists()) {
            try {
                this.s = new Scanner(this.expenseFile);
                while (this.s.hasNext()) {
                    return readExpense(this.s.nextLine());
                }
            } catch (FileNotFoundException e) {
                throw new JavAssistException(e.getMessage());
            }
        }
        return new ExpenseList();
    }

    /**
     * Converts String into corresponding instances of ExpenseList.
     *
     * @param s Describes the expenses.
     * @return ExpenseList instance created based on s.
     */
    public ExpenseList readExpense(String s) {
        String[] amounts = s.split(SEPARATOR);
        return new ExpenseList(
                Float.parseFloat(amounts[0]),
                Float.parseFloat(amounts[1]),
                Float.parseFloat(amounts[2]),
                Float.parseFloat(amounts[3]),
                Float.parseFloat(amounts[4]),
                Float.parseFloat(amounts[5]),
                Float.parseFloat(amounts[6])
        );
    }
}
