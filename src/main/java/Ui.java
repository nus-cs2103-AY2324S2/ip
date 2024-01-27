import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private String hLine = "________________________________________________";
    private String logo = "                            ╱|、\n" +
            "                          (˚ˎ 。7  \n" +
            "                           |、˜〵          \n" +
            "                          じしˍ,)ノ\n";
    private String greetMsg = "Hello! I'm Hatsune Miku!\n"
            + " What can I do for you?";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(logo);
        System.out.println(hLine);
        System.out.println(greetMsg);
        System.out.println(hLine);
    }

    protected String readCommand() {
        return sc.nextLine();
    }

    protected void showLoadingError() {
        System.out.println(hLine);
        System.out.println("Error in loading file from specified folder! Creating one.");
        System.out.println(hLine);
    }

    protected void showLine() {
        System.out.println(hLine);
    }

    protected void echo(String msg) {
        System.out.println(msg);
        System.out.println(hLine);
    }
}
