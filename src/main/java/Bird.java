

public class Bird {
    private Ui ui;
   
    public static void main(String[] args) { 
        new Bird();
    }

    public Bird() {
        greet();
        TaskList.create();
        Storage.init();
        this.ui = new Ui();
        ui.scan();
    }

    public static void greet() {
        System.out.println(" /\\_/\\");
        System.out.println("((@v@))");
        System.out.println("():::()");
        System.out.println(" VV-VV");
        System.out.println("What can I do for you?");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
