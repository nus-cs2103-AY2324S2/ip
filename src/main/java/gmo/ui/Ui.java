package gmo.ui;

import gmo.task.Task;
import gmo.util.TaskList;

/**
 * Ui class handles the user interface of the chatbot.
 * It contains methods to print messages to the user.
 */
public class Ui {

    /**
     * Returns a greeting message from the chatbot.
     */
    public String greet() {
        String greetPrint = "\nGMO chop! ヾ(⌐■_■)ノ♪\n"
                + "do you want to play video games?";
        return greetPrint;
    }

    /**
     * Returns a goodbye message from the chatbot.
     */
    public String salute() {
        String byePrint = "\nGMO chop! ヾ(⌐■_■)ノ♪\n"
                + "bye! see you next time";
        return byePrint;
    }

    /**
     * Returns a list of commands that the chatbot can understand.
     */
    public String printTutorial() {
        final String tutorialPrint = "command GMO with these keywords!\n"
                + "0. hi [greet GMO]\n"
                + "1. bye [shut GMO down]\n"
                + "2. log [view task log]\n"
                + "3. todo <task> [add todo task]\n"
                + "4. due <task> /by dd/MM/yyyy HHmm [add due task]\n"
                + "5. event <event> /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm [add event]\n"
                + "6. done <task number> [check task as done]\n"
                + "7. redo <task number> [uncheck task]\n"
                + "8. delete <task number> [delete task]\n"
                + "9. find <keyword> [search for task]\n"
                + "10. commands [view commands]\n"
                + "11. sort [sort task log]";
        return tutorialPrint;
    }

    /**
     * Returns a list of tasks in the task log.
     */
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

    /**
     * Returns a message to inform the user that the task log has been sorted.
     */
    public String printSortLog() {
        String sortLogPrint = "GMO has sorted your log (•̀ᴗ•́)و ̑̑";
        return sortLogPrint;
    }

    /**
     * Returns a message to inform the user that the task log is empty.
     */
    public String printSortEmptyLog() {
        String sortEmptyLogPrint = "GMO cannot sort an empty log (⊙_☉)\n"
                + "please add some tasks to your log first";
        return sortEmptyLogPrint;
    }

    /**
     * Returns a message to inform the user that storage is empty.
     */
    public String printEmptyStorage() {
        String emptyStoragePrint = "GMO cache has no saved tasks\n"
                + "your log is currently empty ᕦ(ò_óˇ)ᕤ";
        return emptyStoragePrint;
    }

    /**
     * Returns a message to inform the user that the task log has been added with a new task.
     */
    public String printAddTask(Task task, int size) {
        String taskAddPrint = "GMO has added the task (づ￣ ³￣)づ\n"
                + "added: " + task + "\n"
                + "you now have " + size + " tasks in the log";
        return taskAddPrint;
    }

    /**
     * Returns a message to inform the user that the task has been marked as done.
     */
    public String printDoneTask(TaskList tasks, int index) {
        String taskDonePrint = "GMO has marked the task as done (•̀ᴗ•́)و ̑̑\n"
                + "completed: " + tasks.get(index - 1);
        return taskDonePrint;
    }

    /**
     * Returns a message to inform the user that the task has been marked as undone.
     */
    public String printRedoTask(TaskList tasks, int index) {
        String taskRedoPrint = "GMO has marked the task as incomplete (⊙_☉)\n"
                + "incomplete: " + tasks.get(index - 1);
        return taskRedoPrint;
    }

    /**
     * Returns a message to inform the user that the task has been deleted.
     */
    public String printDeleteTask(String desc, int index) {
        String taskDeletePrint = "GMO has deleted the task (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧\n"
                + "deleted: " + desc + "\n"
                + "you now have " + index + " tasks in the log";
        return taskDeletePrint;
    }

    /**
     * Returns a message to inform the user that an invalid index has been provided.
     */
    public String printErrInvalidIndex() {
        String invalidIndexPrint = "you have provided GMO with an invalid task number!\n"
                + "please provide a valid one silly _(ツ)_/¯";
        return invalidIndexPrint;
    }

    /**
     * Returns a message to inform the user that the command is invalid.
     */
    public String printErrInvalidCommand() {
        String invalidCommandPrint = "GMO does not understand your command (╯°□°）╯︵ ┻━┻\n"
                + "type 'commands' to see the list of what BMO knows to do";
        return invalidCommandPrint;
    }

    /**
     * Returns a message to inform the user that the task command is invalid.
     */
    public String printErrInvalidTask() {
        String invalidTaskPrint = "GMO does not understand your task command ಠ_ಠ\n"
                + "here are examples of a valid task command:\n"
                + "todo play mario kart\n"
                + "due play mario kart /by 14/02/2024 1800\n"
                + "event play mario kart /from 14/02/2024 1800 /to 14/02/2024 2000";
        return invalidTaskPrint;
    }

    /**
     * Returns a message to inform the user that the command is useless.
     */
    public String printErrUselessCommand() {
        String uselessCommandPrint = "That command is useless silly ಠ╭╮ಠ\n"
                + "GMO has already done that for you";
        return uselessCommandPrint;
    }

    /**
     * Returns a message to inform the user that the date format is invalid.
     */
    public String printErrInvalidDate() {
        String invalidDatePrint = "GMO does not understand your date format (⊙_☉)\n"
                + "please provide a valid date format: dd/MM/yyyy HHmm";
        return invalidDatePrint;
    }

}
