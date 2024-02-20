package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.loans.Loan;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Handles data storage using file IO.
 */
public class Storage {
    private String taskFilePath = "./data/tasks.txt";
    private String loanFilePath = "./data/loans.txt";
    private FileWriter data;

    /**
     * Constructs a new <code>Storage</code> that stores tasks and loans from specified file.
     *
     * @param specifiedTaskFilePath Task data file to be written.
     * @param specifiedLoanFilePath Loan data file to be written.
     */
    protected Storage(String specifiedTaskFilePath, String specifiedLoanFilePath) {
        taskFilePath = specifiedTaskFilePath;
        loanFilePath = specifiedLoanFilePath;
    }

    /**
     * Loads, parses and returns task data from specified tasks file.
     *
     * @return <code>ArrayList</code> of tasks.
     * @throws IOException When <code>Scanner</code> does not find the file.
     */
    protected ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();
        File tasksFile = new File(taskFilePath);
        if (!tasksFile.exists()) {
            tasksFile.getParentFile().mkdirs();
            tasksFile.createNewFile();
        }
        Scanner sc = new Scanner(tasksFile);
        while (sc.hasNext()) {
            String[] token = sc.nextLine().split("\\|");
            if (token[0].equals("T")) {
                arr.add(new Todo(token[1], Boolean.parseBoolean(token[2])));
            } else if (token[0].equals("D")) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                arr.add(new Deadline(token[1], Boolean.parseBoolean(token[2]), LocalDate.parse(token[3], formatter)));
            } else if (token[0].equals("E")) {
                arr.add(new Event(token[1], Boolean.parseBoolean(token[2]), token[3], token[4]));
            }
        }
        sc.close();
        return arr;
    }

    /**
     * Loads, parses and returns loan data from specified loans file.
     *
     * @return <code>ArrayList</code> of loans.
     * @throws IOException When <code>Scanner</code> does not find the file.
     */
    protected ArrayList<Loan> loadLoans() throws IOException {
        ArrayList<Loan> arr = new ArrayList<>();
        File loansFile = new File(loanFilePath);
        if (!loansFile.exists()) {
            loansFile.getParentFile().mkdirs();
            loansFile.createNewFile();
        }
        Scanner sc = new Scanner(loansFile);
        while (sc.hasNext()) {
            String[] token = sc.nextLine().split("\\|");
            boolean taken = (token[2].equals("given")) ? false : true;
            arr.add(new Loan(new BigDecimal(token[0]), token[1],
                    taken, Boolean.parseBoolean(token[3])));
        }
        sc.close();
        return arr;
    }

    /**
     * Saves current tasks in instance to specified file.
     *
     * @param taskList <code>ArrayList</code> of tasks to store.
     * @throws DukeException If there is error in IO.
     */
    protected void saveTasks(ArrayList<Task> taskList) throws DukeException {
        try {
            data = new FileWriter(taskFilePath);
            for (Task task : taskList) {
                if (task instanceof Todo) {
                    data.write(String.format("T|%s|%s\n", task.getTaskName(), task.isDone()));
                } else if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    data.write(String.format("D|%s|%s|%s\n", d.getTaskName(), d.isDone(),
                            d.getBy().format(DateTimeFormatter.ISO_LOCAL_DATE)));
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    data.write(String.format("E|%s|%s|%s|%s\n",
                            e.getTaskName(), e.isDone(), e.getFrom(), e.getTo()));
                }
            }
            data.close();
        } catch (IOException ie) {
            throw new DukeException("Unable to save! Reason: " + ie.getMessage());
        }
    }

    /**
     * Saves current loans in instance to specified file.
     *
     * @param loanRecord <code>ArrayList</code> of loans to store.
     * @throws DukeException If there is error in IO.
     */
    protected void saveLoans(ArrayList<Loan> loanRecord) throws DukeException {
        try {
            data = new FileWriter(loanFilePath);
            for (Loan loan : loanRecord) {
                data.write(String.format("%s|%s|%s|%s\n",
                        loan.getAmount(), loan.getDetails(), loan.getType(), loan.getReturnStatus()));
            }
            data.close();
        } catch (IOException ie) {
            throw new DukeException("Unable to save! Reason: " + ie.getMessage());
        }
    }
}
