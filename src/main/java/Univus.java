public class Univus {
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Univus");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        Univus univus = new Univus();
        univus.greet();
        univus.bye();
    }
}
