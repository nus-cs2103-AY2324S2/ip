public class MissingTodoException extends DukeException {
    public MissingTodoException() {
        super("Your todo's description must not be empty!");
    }
}
