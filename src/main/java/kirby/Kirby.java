package kirby;

import kirby.tasks.TaskList;

import java.util.Scanner;


public class Kirby {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public static void main(String[] args) {

        String message =
                "____________________________________________________________\nHiiiiii \uD83D\uDE00! I'm kirby.Kirby Yayyyyy \uD83C\uDF8C!\nWhat can I do for you?\n____________________________________________________________\n";

        Scanner sc = new Scanner(System.in);

        TaskList inputs;

        inputs = Storage.returnSave();

        System.out.println(message);

        Ui.receiveInput(sc, inputs);

        Storage.save(inputs);

    }
}