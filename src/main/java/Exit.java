public class Exit {
    private static final String EXIT_PATH = "./src/main/Exit.txt";

    public static void exit() {
        String exit = Util.getFile(EXIT_PATH);
        System.out.println((exit));
    }
}
