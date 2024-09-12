package someboty.managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import someboty.tasks.Task;
import someboty.expenses.Expense;


/**
 * fileManager handles the fetching and storing of task and expense lists
 * to/from "tasks.csv" and "expenses.csv" files respectively.
 */
public class FileManager {

    private String rootPath;

    /**
     * Constructor for fileManager.
     * 
     * @param rootPath Path to create, read, and write the "data/tasks.csv" and "data/expenses.csv" file.
     */
    public FileManager(String rootPath) {
        this.rootPath = rootPath;
    }

    private Scanner getFileScanner(String fileName) {
        File infile = new File(this.rootPath 
                    + File.separator + "data"
                    + File.separator + fileName);

        Scanner scanner;
        try {
            // Creates new directory if it does not exist.
            new File(this.rootPath + File.separator + "data").mkdir(); 
            
            // Creates new file if it does not exist.
            infile.createNewFile(); 

            scanner = new Scanner(infile);
            
        } catch (IOException e) { // not supposed to happen.
            e.printStackTrace();
            return null;
        }

        return scanner;
    }

    private FileWriter getFileWriter(String fileName) {
        FileWriter outfile;

        try {  // create/open given csv file.
            outfile = new FileWriter(this.rootPath
                        + File.separator + "data"
                        + File.separator + fileName);

        } catch (IOException e) { // for devs debugging
            System.out.println(String.format("Error. Unable to create fileWriter for %s.", fileName));
            e.printStackTrace();
            return null;
        }

        return outfile;
    }

    private boolean closeFileWriter(FileWriter file) {
        try {
            file.flush();
            file.close();
            
        } catch (IOException e) { // for devs debugging
            System.out.println("Error. Unable to close file.");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Read saved tasks from the tasks.csv file and converts it into a list.
     * 
     * @return A list of tasks.
     */
    protected ArrayList<Task> fetchTasks() {
        Scanner scanner = getFileScanner("tasks.csv");
        Task currentTask;
        ArrayList<Task> taskList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            currentTask = Task.csvToTask(scanner.nextLine());
            taskList.add(currentTask);
        }

        scanner.close();
        return taskList;
    }

    /**
     * Takes in a list of task and overwrites it into the tasks.csv file.
     * Note: This method does not append to the file, but overwrites it instead.
     * 
     * @param taskList A list of tasks to be saved into file.
     */
    protected boolean storeTasks(ArrayList<Task> taskList) {
        FileWriter outfile = getFileWriter("tasks.csv");
        assert outfile != null : "FileManager::storeTasks  Null outfile created.";

        try { // write all the tasks in the task list into the csv file.
            for (Task task: taskList) {
                outfile.write(task.taskToCsv());
            }

        } catch (IOException e) { // for devs debugging
            System.out.println("Error. Unable to save tasks to file.");
            e.printStackTrace();
        }

        return closeFileWriter(outfile);
    }


    /**
     * Read saved expenses from the expenses.csv file and converts it into a list.
     * 
     * @return A list of expenses.
     */
    protected ArrayList<Expense> fetchExpenses() {
        Scanner scanner = getFileScanner("expenses.csv");
        Expense currentExpense;
        ArrayList<Expense> expenseList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            currentExpense = Expense.csvToExpense(scanner.nextLine());
            expenseList.add(currentExpense);
        }

        scanner.close();
        return expenseList;
    }

    /**
     * Takes in a list of expenses and overwrites it into the expenses.csv file.
     * Note: This method does not append to the file, but overwrites it instead.
     * 
     * @param expenseList A list of expenses to be saved into file.
     */
    protected boolean storeExpenses(ArrayList<Expense> expenseList) {
        FileWriter outfile = getFileWriter("expenses.csv");
        assert outfile != null : "FileManager::storeExpenses  Null outfile created.";

        try { // write all the expenses in the list into the csv file.
            for (Expense expense: expenseList) {
                String csvLine = expense.expenseToCsv();
                System.out.println(csvLine);
                outfile.write(csvLine);
            }

        } catch (IOException e) { // for devs debugging
            System.out.println("Error. Unable to save tasks to file.");
            e.printStackTrace();
            return false;
        }

        return closeFileWriter(outfile);
    }


}
