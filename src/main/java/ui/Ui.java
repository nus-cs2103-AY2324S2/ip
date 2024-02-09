package ui;
import msg.Msg;
import msg.StdMsgs;

import java.util.Scanner;

/** Class represents interacting with the user */
public class Ui {

    private Scanner input;
    public Ui() {
        input = new Scanner(System.in);
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

    public void deleteResponse(String deletedTask, int newTaskCount) {
        new Msg(
                "Got it. I've deleted this task:\n" +
                        deletedTask +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", newTaskCount)
        ).print();
    }
}
