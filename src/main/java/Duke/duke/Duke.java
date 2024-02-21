package Duke.duke;

import Duke.UI.MainWindow;

public class Duke {
    public Duke() {
    }
    private void runApp() {
        boolean isTerminated = false;
        while (!isTerminated) {
            isTerminated = MainWindow.isTerminated();
        }
    }
    public static void main(String[] args) {
        new Duke().runApp();
    }
}




