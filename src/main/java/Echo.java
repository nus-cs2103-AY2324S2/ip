public class Echo extends Greetings{
    @Override
    public void response() {
        System.out.print("");
    }

    @Override
    public void response(String word) {
        System.out.println("  " + "added: " + word);
    }
}
