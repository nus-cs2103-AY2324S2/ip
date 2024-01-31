package leto;

import static leto.ui.Ui.letoLogo;

public class Main {
    public static void main(String[] args) {
        letoLogo();
        CommandExecutor.readCommandAndExecute();


    }
}
