package duke;

public class Duke {
    public Duke() {
    }

    public void launch() {
        System.out.println(Ui.logo());
        System.out.println(Ui.greet());
        System.out.println(Ui.exit());
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.launch();
    }
}