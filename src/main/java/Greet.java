public class Greet {
    private static final String LOGO_PATH = "./src/main/Snorlax.txt";
    private static final String GREETING_PATH = "./src/main/Greeting.txt";

    public static void greet() {
        String logo = Util.getFile(LOGO_PATH);
        String greeting = Util.getFile(GREETING_PATH);

        System.out.println(logo);
        System.out.println(greeting);
    }
}
