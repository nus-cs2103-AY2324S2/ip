/**
 * NoSuchTaskException alerts users when wrong task number is given.
 */
public class NoSuchTaskException extends Exception {
    public NoSuchTaskException() {
        super("Sorry, I cannot find this task from the list.");
    }
}
