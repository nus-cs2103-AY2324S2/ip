package ui;
import msg.Msg;
import msg.StdMsgs;
import tasklist.TaskList;

import java.util.Scanner;

/** Class represents interacting with the user */
public class Ui {

    private Scanner input;

    private TaskList tasks;
    public Ui() {
        input = new Scanner(System.in);
    }

    public Ui(TaskList tasks) {
        input = new Scanner(System.in);
        this.tasks = tasks;
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void showLoadingError() {
        new Msg("My apologies, it seems as though I have trouble loading the data").print();
    }

    public void loadingDone() {
        StdMsgs.LOADINGDONE.print();
    }

    public void welcome() {
        StdMsgs.LOGO.print();
        StdMsgs.WELCOME.print();
    }

    public void bye() {
        StdMsgs.BYE.print();
    }

    public void printTasks() {
        new Msg(tasks.toString()).print();
    }

    public void addedResponse(String addedTask) {
        new Msg(
                "Got it. I've added this task:\n" +
                        addedTask +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", tasks.getTaskCount())
        ).print();
    }

    public void deleteResponse(String deletedTask) {
        new Msg(
                "Got it. I've deleted this task:\n" +
                        deletedTask +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", tasks.getTaskCount())
        ).print();
    }

    public void unmarkResponse(String unMarkedTask) {
        StdMsgs.UNMARK.print();
        new Msg(unMarkedTask).print();
    }

    public void markResponse(String markedTask) {
        StdMsgs.MARK.print();
        new Msg(markedTask).print();
    }
    /**
     * Wrapper to show error msg. This general function is used to catch exceptions
     * that I have not dealt with yet
     *
     * @param err
     */
    public void errorMsg(String err) {
        new Msg(err).print();
    }
}
