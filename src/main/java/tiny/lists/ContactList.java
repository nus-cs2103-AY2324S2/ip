package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.Contact;

/**
 * Manages all the contacts.
 */
public class ContactList {
    protected ArrayList<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void delete(Integer ind) {
        contacts.remove(contacts.get(ind));
    }

    public Contact get(Integer ind) {
        return contacts.get(ind);
    }

    public Integer size() {
        return contacts.size();
    }

    /**
     * Lists out all the contacts in the list.
     *
     * @return String of all of the contacts.
     */
    public String list() {
        if (contacts.size() == 0) {
            return "You don't have any contacts!";
        }
        String output = "";
        for (int i = 0; i < contacts.size(); i++) {
            output += (i + 1) + ". " + contacts.get(i);
            output += "\n";
        }
        return output;
    }

    /**
     * Formats all the contacts into the correct format to save.
     *
     * @return ArrayList of contacts in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            toSave.add(contacts.get(i).formatToSave());
        }
        return toSave;
    }
}
