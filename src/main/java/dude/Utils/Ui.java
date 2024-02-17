package dude.Utils;

public class Ui {


    public void showWelcome() {
        String msg = "\t-----------------------------------\n" +
                "\t\tHello! I'm dude.Dude\n" +
                "\t\tWhat can I do for you?\n" +
                "\t-----------------------------------\n";
        System.out.println(msg);
    }

    public void showMessage(String msg) {

        String temp = msg;

        temp = temp.replaceAll("\n", "\n\t\t");
        temp = "\t-----------------------------------\n" +
                "\t\t" + temp + "\n" +
                "\t-----------------------------------";
        System.out.println(temp);
    }
}
