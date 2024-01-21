public class Deadline extends Task {
    public Deadline(String name) throws NicoleException {
        super();
         if (name.contains("null")) {
             throw new NicoleException("Describe your deadline like this: deadline [task] /by [datetime]");
         }
         super.setName(name);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
