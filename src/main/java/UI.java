import java.util.Scanner;

public class UI {
    private String greeting = "Hi, I'm Lighthouse.\nHow can I help you?";
    private String separatorLine = "__________________________________________________";
    private Scanner scanner;
    private String commandOutput = "";
    public UI(){
        scanner = new Scanner(System.in);
    }
    public void showWelcome() {
        System.out.println(separatorLine);
        System.out.println(greeting);
        System.out.println(separatorLine);
    }

    public void setCommandOutput(String outputtext) {
        this.commandOutput = outputtext;
    }
    public String readCommand(){
        return scanner.nextLine();
    }

    public void clearCommandOutput(){
        this.commandOutput = "";
    }
    public void showLine(){
        if (null != commandOutput && !"".equals(commandOutput)) {
            System.out.println(separatorLine);
            System.out.println(this.commandOutput);
            System.out.println(separatorLine);
        }
        this.clearCommandOutput();
    }

    public void showError(String errMessage) {
        this.clearCommandOutput();
        System.out.println(separatorLine);
        System.out.println(errMessage);
        System.out.println(separatorLine);
    }
}
