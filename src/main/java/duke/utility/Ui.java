package duke.utility;

import java.util.ArrayList;

import duke.task.Task;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello! I'm Pengu\n" + "What can I do for you?\n" +
                "\nDid you know that the noise penguins make are called \"honks\"");
        this.showLine();
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon! **HONK HONK**");
        this.showLine();
    }

    private void showLine() {
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    public void showTaskListContents(ArrayList<Task> taskList) {
        int storageSize = taskList.size();
        if (storageSize == 0) {
            System.out.println("*Honk!* You currently have no tasks");
        } else {
            System.out.println("*Honk!* Pengu has listed your current tasks below:");
            for (int k = 0; k < storageSize; k++) {
                int curr = k + 1;
                Task currTask = taskList.get(k);
                System.out.println(curr + ". " + currTask.toString());
            }
        }
        this.showLine();
    }

    public void showMarkedTask(Task markedTask) {
        System.out.println("*Honk!* Good Job!, Pengu has marked this task as done:\n" + markedTask.toString());
        this.showLine();
    }
    public void showUnmarkedTask(Task unmarkedTask) {
        System.out.println("*Honk!* Pengu has marked this task as not done yet:\n" + unmarkedTask.toString());
        this.showLine();
    }
    public void showDeletedTask(Task deletedTask, int taskStoreSize) {
        System.out.println(String.format("*Honk* Pengu has removed the following task:\n" + deletedTask.toString()
                + "\nNow you have %s tasks left", taskStoreSize));
        this.showLine();
    }
    public void showError(String errMessage) {
        System.out.println(errMessage);
        this.showLine();
    }

    public void showAddedTask(Task addedTask, int taskStoreSize) {
        System.out.println(String.format("*Honk! Honk!* Pengu has added this task:\n" + addedTask.toString()
                + "\nGet back to work! you have %s tasks in the list\n"
                + "―――――――――――――――――――――――――――――――――――", taskStoreSize));
    }

    public void showFilteredTask(ArrayList<Task> filteredList, String keyword) {
        if (filteredList.size() == 0) {
            System.out.println(String.format("*Honk! no tasks with %s keyword found! "
                    + "Maybe try looking at the list command", keyword));
        } else {
            System.out.println(String.format("*Honk! Pengu has found the following tasks containing "
                    + "the %s keyword:", keyword));
            for (int k = 0; k < filteredList.size(); k++) {
                int curr = k + 1;
                Task currTask = filteredList.get(k);
                System.out.println(curr + ". " + currTask.toString());
            }
            this.showLine();
        }
    }
}
