package Gops;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private Parser parser = new Parser();
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void uiCode() {
        Scanner inputTaker = new Scanner(System.in);
        TaskList taskList = new TaskList();
        int taskCount = 0;

        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File txtFile = new File(dataFolder,"Gops.Gops.txt");
        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            taskList = Storage.readFromHardDisk(txtFile);
            taskCount = taskList.numberOfTasks();
        }
        System.out.println("Hello! I'm Gops");
        System.out.println("What can I do for you?");
        String userReply = inputTaker.nextLine();
        parser.parse(userReply, taskList, txtFile, inputTaker);
        if (userReply.equals("bye")) {
            System.out.print("Bye. Hope to see you again soon!");
        }
    }
}
