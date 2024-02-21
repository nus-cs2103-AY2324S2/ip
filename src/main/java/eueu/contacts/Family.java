package eueu.contacts;

public class Family extends Contacts {

    private String relation;
    public Family(String name, int number, String relation, String notes) {
        super(name, number, notes);
        this.relation = relation;
    }

    @Override
    public String contacting() {
        return  this.getNotes().isEmpty()
                ? "[" + relation + "] " + this.getName() + " | " + getNumber()
                : "[" + relation + "] " + this.getName() + " | " + getNumber()
                    + "\n" + this.getNotes();
    }
}
