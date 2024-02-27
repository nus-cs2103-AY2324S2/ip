package bmo.ui;

import bmo.task.Task;
import bmo.util.TaskList;

/**
 * Ui class handles the user interface of the chatbot.
 * It contains methods to print messages to the user.
 */
public class Ui {

    public String greet() {
        String greetPrint = "\nBMO chop! ヾ(⌐■_■)ノ♪\n"
                + "do you want to play video games?";
        return greetPrint;
    }

    public String salute() {
        String byePrint = "\nBMO chop! ヾ(⌐■_■)ノ♪\n"
                + "bye! see you next time";
        return byePrint;
    }

    public String printTutorial() {
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
        return TUTORIAL_PRINT;
    }

    public String printLog(TaskList taskLog) {
        StringBuilder logPrint = new StringBuilder();
        if (taskLog.isEmpty()) {
            String emptyLogPrint = "wow! your log is actually empty\n"
                    + "let's play mario kart right now!! ᕦ(ò_óˇ)ᕤ";
            return emptyLogPrint;
        }

        for (int i = 0; i < taskLog.size(); i++) {
            Task currTask = taskLog.get(i);
            logPrint.append(i + 1).append(". ").append(currTask.getStatusIcon())
                    .append(" ").append(currTask.toString())
                    .append("\n");
        }

        return logPrint.toString();
    }

    public String printSortLog() {
        String sortLogPrint = "BMO has sorted your log (•̀ᴗ•́)و ̑̑";
        return sortLogPrint;
    }

    public String printEmptyStorage() {
        String emptyStoragePrint = "BMO cache has no saved tasks\n"
                + "your log is currently empty ᕦ(ò_óˇ)ᕤ";
        return emptyStoragePrint;
    }

    public String printAddTask(Task task, int size) {
        String taskAddPrint = "BMO has added the task (づ￣ ³￣)づ\n"
                + "added: " + task + "\n"
                + "you now have " + size + " tasks in the list";
        return taskAddPrint;
    }

    public String printDoneTask(TaskList tasks, int index) {
        String taskDonePrint = "BMO has marked the task as done (•̀ᴗ•́)و ̑̑\n"
                + "completed: " + tasks.get(index - 1);
        return taskDonePrint;
    }

    public String printRedoTask(TaskList tasks, int index) {
        String taskRedoPrint = "BMO has marked the task as incomplete (⊙_☉)\n"
                + "incomplete: " + tasks.get(index - 1);
        return taskRedoPrint;
    }

    public String printDeleteTask(String desc, int index) {
        String taskDeletePrint = "BMO has deleted the task (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧\n"
                + "deleted: " + desc + "\n"
                + "you now have " + index + " tasks in the list";
        return taskDeletePrint;
    }

    public String printErrInvalidIndex() {
        String invalidIndexPrint = "you have provided BMO with an invalid task number!\n"
                + "please provide a valid one silly _(ツ)_/¯";
        return invalidIndexPrint;
    }

    public String printErrInvalidCommand() {
        String invalidCommandPrint = "BMO does not understand your command (╯°□°）╯︵ ┻━┻\n"
                + "type 'commands' to see the list of what BMO knows to do";
        return invalidCommandPrint;
    }

    public String printErrInvalidTask() {
        String invalidTaskPrint = "BMO does not understand your task command ಠ_ಠ\n"
                + "here are examples of a valid task command:\n"
                + "todo play mario kart\n"
                + "due play mario kart /by 14/02/2024 1800\n"
                + "event play mario kart /from 14/02/2024 1800 /to 14/02/2024 2000";
        return invalidTaskPrint;
    }

    public String printErrUselessCommand() {
        String uselessCommandPrint = "That command is useless silly ಠ╭╮ಠ\n"
                + "BMO has already done that for you";
        return uselessCommandPrint;
    }

    public String printErrInvalidDate() {
        String invalidDatePrint = "BMO does not understand your date format (⊙_☉)\n"
                + "please provide a valid date format: dd/MM/yyyy HHmm";
        return invalidDatePrint;
    }

}
