public class Event extends Task {
    public Event(String name) throws NicoleException {
        super();
        if (name.contains("null")) {
            throw new NicoleException("Describe your event like this: event [name] /from [starting] /to [ending]");
        }
        super.setName(name);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
