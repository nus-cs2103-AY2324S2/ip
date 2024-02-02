public class Main {
    static final String FOLDER_PATH = "./data";
    static final String FILE_PATH = FOLDER_PATH + "/jimmy";

    /**
     * Main method to run the bot.
     *
     * @param args The arguments passed in.
     */
    public static void main(String[] args) {
        new Jimmy(FILE_PATH).run();
    }
}
