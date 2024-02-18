package bmo.ui;

import bmo.util.TaskList;
import bmo.task.Task;

public class Ui {

    public void showLine() {
        System.out.println("----------------------------------------------------------------------------------");
    }

    public void greet() {
        showLine();
        System.out.println("BMO chop! ヾ(⌐■_■)ノ♪");
        System.out.println("do you want to play video games?");
        showLine();
    }

    public void salute() {
        showLine();
        System.out.println("beep boop BMO shutting down...");
        showLine();
    }

    public void printTutorial() {
        final String TUTORIAL_PRINT = "command BMO with these keywords!\n"
                + "0. hi [greet BMO]\n"
                + "1. bye [shut BMO down]\n"
                + "2. log [view task log]\n"
                + "3. todo <task> [add todo task]\n"
                + "4. due <task> /by dd/MM/yyyy HHmm [add due task]\n"
                + "5. event <event> /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm [add event]\n"
                + "6. done <task number> [check task as done]\n"
                + "7. redo <task number> [uncheck task]\n"
                + "8. delete <task number> [delete task]\n"
                + "9. find <keyword> [search for task]\n"
                + "10. commands [view commands]";
        showLine();
        System.out.println(TUTORIAL_PRINT);
        showLine();
    }

    public void printLog(TaskList taskLog) {
        StringBuilder logPrint = new StringBuilder();
        if (taskLog.isEmpty()) {
            System.out.println("wow! your log is actually empty");
            System.out.println("let's play mario kart right now!! ᕦ(ò_óˇ)ᕤ");
            showLine();
            return;
        }
        for (int i = 0; i < taskLog.size(); i++) {
            Task currTask = taskLog.get(i);
            logPrint.append(i + 1).append(". ").append(currTask.getStatusIcon())
                    .append(" ").append(currTask.toString())
                    .append("\n");
        }
        showLine();
        System.out.println(logPrint.toString());
        showLine();
    }

    public void printEmptyStorage() {
        showLine();
        System.out.println("BMO cache has no saved tasks");
        System.out.println("your log is currently empty ᕦ(ò_óˇ)ᕤ");
        showLine();
    }

    public void printAddTask(Task task, int size) {
        showLine();
        System.out.println("BMO has added the task (づ￣ ³￣)づ");
        System.out.println("added: " + task);
        System.out.println("you now have " + size + " tasks in the list");
        showLine();
    }

    public void printDoneTask(TaskList tasks, int index) {
        showLine();
        System.out.println("BMO has marked the task as done (•̀ᴗ•́)و ̑̑");
        System.out.println("completed: " + tasks.get(index - 1));
        showLine();
    }

    public void printRedoTask(TaskList tasks, int index) {
        showLine();
        System.out.println("BMO has marked the task as incomplete (⊙_☉)");
        System.out.println("incomplete: " + tasks.get(index - 1));
        showLine();
    }

    public void printDeleteTask(String desc, int index) {
        showLine();
        System.out.println("BMO has deleted the task (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
        System.out.println("deleted: " + desc);
        showLine();
    }

    public void printErrInvalidIndex() {
        showLine();
        System.out.println("you have provided BMO with an invalid task number!");
        System.out.println("please provide a valid one silly _(ツ)_/¯");
        showLine();
    }

    public void printErrInvalidCommand() {
        showLine();
        System.out.println("BMO don't understand (╯°□°）╯︵ ┻━┻");
        System.out.println("type 'commands' to see the list of what BMO knows to do");
        showLine();
    }

    public void printErrInvalidTask() {
        showLine();
        System.out.println("BMO does not quite understand your task command ಠ_ಠ");
        System.out.println("here are examples of a valid task command:");
        System.out.println("todo play mario kart");
        System.out.println("due play mario kart /by 14/02/2024 1800");
        System.out.println("event play mario kart /from 14/02/2024 1800 /to 14/02/2024 2000");
        showLine();
    }

    public void printErrUselessCommand() {
        showLine();
        System.out.println("That command is useless silly ಠ╭╮ಠ");
        System.out.println("BMO has already done that for you");
        showLine();
    }

    public void printErrInvalidDate() {
        showLine();
        System.out.println("BMO does not understand your date format (⊙_☉)");
        System.out.println("please provide a valid date format: dd/MM/yyyy HHmm");
        showLine();
    }

}
