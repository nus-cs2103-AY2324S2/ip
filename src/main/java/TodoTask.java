public class TodoTask extends Task {

    TodoTask(String name) throws DukeException{

        super(name, Type.T);
        if (this.name.isBlank()) {
            String error_message = "\tTodo description cannot be empty!\n\tEx: todo return book\n";
            throw new DukeException(error_message);
        }
    }


}
