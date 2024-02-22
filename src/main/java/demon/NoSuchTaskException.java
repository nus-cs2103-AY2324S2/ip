package demon;

public class NoSuchTaskException extends DemonException{
    public NoSuchTaskException(char msg) {
        super("Sorry, I don't recognize this task " + msg);
    }

}
