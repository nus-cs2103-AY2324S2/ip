package duke;

public class Bird {
    private Ui ui;
   
    public static void main(String[] args) { 
        new Bird();
    }

    public Bird() {
        TaskList.create();
        Storage.init();
        this.ui = new Ui();
        ui.run();
    }
}
