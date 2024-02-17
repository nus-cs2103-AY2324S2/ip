package Utils;

public class Ui {


    public void showWelcome() {
        String msg = "\t-----------------------------------\n" +
                "\tHello! I'm Dude\n" +
                "\tWhat can I do for you?\n" +
                "\t-----------------------------------\n";
        System.out.println(msg);
    }

    public void showMessage(String msg) {
        msg = "\t-----------------------------------\n" +
                "\t" + msg + "\n" +
                "\t-----------------------------------";
        System.out.println(msg);
    }
}
