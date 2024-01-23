public class Gulie {
    private final static String name = "Güliedistodiez";
    private final static String line = "____________________________________________________________";

    public static void main(String[] args) {
        Gulie gulie = new Gulie();
    }

    public Gulie() {
        this.greet();
        this.exit();
    }

    private void greet() {
        System.out.println(line);
        System.out.println(String.format(" Hello! I'm %s\n What can I do for you?", name));
        System.out.println(line);
    }

    private void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
