public class Exit {
    private static final String EXIT_PATH = "./src/main/Exit.txt";

    public static void exit() {
        String exit = Utils.getFile(EXIT_PATH);
        Utils.encaseLines(exit);
    }
}
