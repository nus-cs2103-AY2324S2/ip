package ui;

public class UI {

    public UI() {
    }

    public String formalities(String context) {
        if (context.equals("greet")) {
            this.showLine();
            System.out.println(" Wassup dawg, I'm Snoopy");
            System.out.println(" What can I do for you?");
            this.showLine();
            return " Wassup dawg, I'm Snoopy";
        } else if (context.equals("farewell")) {
            this.showLine();
            System.out.println(" Bye. Don't come back. jk!");
            this.showLine();
            return " Bye. Don't come back. jk!";
        }
        return context;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

}
