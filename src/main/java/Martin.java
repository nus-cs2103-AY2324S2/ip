public class Martin {
    static String NAME = "Martin";
    public static void main(String[] args) {
        sayGreeting();
        sayBye();
    }

    public static void sayGreeting() {
        System.out.println("Hello from " + NAME);
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("Bye from " + NAME);
    }
}
