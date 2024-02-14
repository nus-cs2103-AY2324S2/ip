package exceptions;

public class JojoTaskNoDescException extends JojoException {
    public JojoTaskNoDescException() {
        super("Hmm...the task description seems empty, please add some description :')");
    }
}
