package ui;
import msg.Msg;
import msg.StdMsgs;
import tasklist.BarebonesTaskList;
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

    public String welcome() {
        StdMsgs.LOGO.print();
        StdMsgs.WELCOME.print();
        return StdMsgs.LOGO + "\n" + StdMsgs.WELCOME;
    }
    public String ArchivedResponse() {
        return "All your contacts have been archived. You can view them in archive/archived.txt";
    }
    public String bye() {
        StdMsgs.BYE.print();
        return StdMsgs.BYE.toString();
    }

    public String printTasks() {
        Msg response = new Msg(tasks.toString());
        response.print();
        return response.toString();
    }
    public String printTasks(BarebonesTaskList tasks) {
        Msg response = new Msg(tasks.toString());
        response.print();
        return response.toString();
    }

    public String addedResponse(String addedTask) {
        Msg response = new Msg(
                "Got it. I've added this task:\n" +
                        addedTask +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", tasks.getTaskCount())
        );
        response.print();
        return response.toString();
    }

    public String deleteResponse(String deletedTask) {
        Msg response = new Msg(
                "Got it. I've deleted this task:\n" +
                        deletedTask +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", tasks.getTaskCount())
        );
        response.print();
        return response.toString();
    }

    public String unmarkResponse(String unMarkedTask) {
        Msg response = new Msg(unMarkedTask);
        StdMsgs.UNMARK.print();
        response.print();
        return StdMsgs.UNMARK + "\n" + response.toString();
    }

    public String markResponse(String markedTask) {
        Msg response = new Msg(markedTask);
        StdMsgs.MARK.print();
        response.print();
        return StdMsgs.MARK + "\n" + response.toString();
    }
    /**
     * Wrapper to show error msg. This general function is used to catch exceptions
     * that I have not dealt with yet
     *
     * @param err
     */
    public String errorMsg(String err) {
        Msg response = new Msg(err);
        response.print();
        return response.toString();
    }
}
