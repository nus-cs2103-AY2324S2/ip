package eueu;

import eueu.contacts.Contacts;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Represents the contactlist and executes the tasks given
 * (i.e. list, write, addContact, delete, group).
 * Contains an ArrayList<Contact> contactlist and Storage s.
 */
public class ContactsList {
    private Storage storage;
    ArrayList<Contacts> contactList = new ArrayList<Contacts>();
    HashMap<String, ContactsList> groupings = new HashMap<>();
    HashMap<String, Integer> hm = new HashMap<>();
    public ContactsList() {}
    public ContactsList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Adds a contact to the contact list and updates the internal mapping.
     *
     * @param contact The contact to be added.
     * @return A confirmation message indicating that the contact has been added.
     */
    public String addContacts(Contacts contact) {
        contactList.add(contact);
        hm.put(contact.getName(), contactList.size()-1);

        return "Contact Added: \n" + contact.contacting();
    }

    /**
     * Deletes a contact from the contact list based on the name and updates the internal mapping.
     *
     * @param name The name of the contact to be deleted.
     * @return A confirmation message indicating that the contact has been deleted.
     */
    public String deleteContact(String name) {
        int index = hm.get(name);
        Contacts contact = contactList.get(index);
        contactList.remove(index);
        return "Contact deleted: \n" + contact.contacting();
    }

    /**
     * Retrieves a formatted list of contacts along with recently added contacts.
     *
     * @return A string containing the formatted contact list.
     * @throws FileNotFoundException if the contact list file is not found.
     */
    public String contactls() throws FileNotFoundException {
        String str = "Contact list: \n" + storage.getContactsContent(contactList) + "\n"
                        + "Recently Added: \n";
        for (int i = 0; i < contactList.size(); i++) {
            str += i + 1 + ". " + contactList.get(i).contacting() + "\n";
        }
        return str;
    }

    /**
     * Retrieves a formatted list of contacts.
     *
     * @return A string containing the formatted contact list.
     */
    public String ls() {
        String str = "";
        for (int i = 0; i < contactList.size(); i++) {
            str += i + 1 + ". " + contactList.get(i).contacting() + "\n";
        }
        return str;
    }

    /**
     * Clears all contacts from the contact list and the internal mapping.
     */
    public void clearContacts() {
        contactList.clear();
        hm.clear();
    }

    /**
     * Creates a group of contacts with the given name and specified numbers.
     *
     * @param gname The name of the group to be created.
     * @param numbers The array of indices of contacts to be added to the group.
     * @return A confirmation message indicating that the group has been created.
     */
    public String group(String gname, int[] numbers) {
        ContactsList group = new ContactsList();
        for (int i = 0; i < numbers.length; i++) {
            group.addContacts(contactList.get(i));
        }
        groupings.put(gname, group);
        return "Group " + gname + " created! :)";
    }

    /**
     * Retrieves a formatted list of contacts belonging to the specified group.
     *
     * @param gname The name of the group.
     * @return A string containing the formatted contact list for the specified group.
     * @throws FileNotFoundException if the contact list file is not found.
     */
    public String getGroup(String gname) throws FileNotFoundException {
        return "Group " + gname + ": \n" +
                groupings.get(gname).ls();
    }

    /**
     * Writes all contacts to the contact list file.
     *
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void write() throws IOException {
        for (int i = 0; i < contactList.size(); i++) {
            contactList.get(i).writeToFile(storage.getFile());
        }
    }

}
