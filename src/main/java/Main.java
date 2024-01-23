import exception.NarutoException;
import naruto.Naruto;

public class Main {
    public static void main(String[] args) throws NarutoException {
        new Naruto();
        while (Naruto.hasNextAction()) {
            Naruto.act();
            try {
                Naruto.listen();
            } catch (NarutoException err) {
                Naruto.handleException(err);
            }
        }
    }
}
