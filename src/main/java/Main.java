import naruto.Naruto;
/**
 * The Main class represents the entry point of the Naruto chatbot.
 * It contains the main method that starts the execution of the program.
 */
public class Main {
    public static void main(String[] args) {
        new Naruto();
        while (Naruto.hasNextAction()) {
            Naruto.act();
            Naruto.listen();
        }
    }
}
