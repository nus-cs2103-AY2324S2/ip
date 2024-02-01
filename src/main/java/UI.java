public class UI {

    public UI() {
    }

    public void formalities(String context) {
        if (context.equals("greet")) {
            this.showLine();
            System.out.println(" Wassup dawg, I'm Snoopy");
            System.out.println(" What can I do for you?");
            this.showLine();
        } else if (context.equals("farewell")) {
            this.showLine();
            System.out.println(" Bye. Don't come back. jk!");
            this.showLine();
        }
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }


}
