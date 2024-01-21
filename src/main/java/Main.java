public class Main {
    private static Naruto naruto;
    public static void main(String[] args) {
        naruto = new Naruto();
        while (naruto.hasNextAction()) {
            naruto.act();
            naruto.listen();
        }
    }
}
