public class Greet {
    private static final String LOGO_PATH = "./src/main/Snorlax.txt";
    private static final String GREETING_PATH = "./src/main/Greeting.txt";

    public static void greet() {
        String logo = Utils.getFile(LOGO_PATH);
        String greeting = Utils.getFile(GREETING_PATH);


        System.out.println(logo);
        Utils.printLine();
        System.out.println(greeting);
    }
}
