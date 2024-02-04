package nicole.task;

import nicole.nicoleexceptions.NicoleException;

public class Todo extends Task {

    /**
     * Initialises a Todo
     *
     * @param name the user request.
     * @throws NicoleException if the description is not in todo [task]
     */
    public Todo(String name) throws NicoleException {
        super();
        if (name == null) {
            throw new NicoleException("What exactly do you want me to note...tell me in the form of: todo [task]");
        }
        super.setName(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
