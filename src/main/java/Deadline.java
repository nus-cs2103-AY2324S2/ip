public class Deadline extends Task {
    String by;


    public Deadline(String taskName, String by) {
        this(taskName, by, false);
    }

    public Deadline(String taskName, String by, Boolean done) {
        super(taskName, done);
        super.identifier = "D";
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s by:%s", super.toString(), by);
    }


    @Override 
    public String[] encode() {
        String[] encodedDeadline = new String[4];
        String[] encodedTask = super.encode();

        for (int i = 0; i < encodedTask.length; i++) {
            encodedDeadline[i] = encodedTask[i];
        }

        encodedDeadline[3] = by;

        return encodedDeadline;
    }

    
}
