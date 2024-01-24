public class Intro extends Greetings {
    @Override
    public void response() {
        System.out.println(" Hello I'm NoisyChatter");
        System.out.println(" What can I do for you?");
    }

    @Override
    public void response(String word) {
        System.out.print("");
    }
}
