package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import duke.contacts.Contacts;

/**
 * Handles contact information
 * Retrieval (listing), query (finding), editing?
 * adding, deleting
 */
public class ContactsList {
    private Storage storage;
    public ContactsList(Storage storage) {
        this.storage = storage;
    }

    public ContactsList() {

    }

    ArrayList<Contacts> contactList = new ArrayList<Contacts>();
    HashMap<String, ContactsList> groupings = new HashMap<>();
    HashMap<String, Integer> hm = new HashMap<>();
    public String addContacts(Contacts contact) {
        contactList.add(contact);
        hm.put(contact.getName(), contactList.size()-1);

        return "Contact Added: \n" + contact.contacting();
    }

//    public void findContact() {
//        for(int i = 0; i < contactList.size(); i++) {
//
//        }
//    }

    public String deleteContact(String name) {
        int index = hm.get(name);
        Contacts contact = contactList.get(index);
        contactList.remove(index);
        return "Contact deleted: \n" + contact.contacting();
    }



//    public void pinContact(Contacts contact) {
//        if (contactList.contains(contact)) {
//            deleteContact(contact);
//        }
//        contactList.add(0, contact);
//    }
    // add a isPinned method in Contact to track number of pinned chats

    public String contactls() throws FileNotFoundException {
        String str = "Contact list: \n" + storage.getContactsContent(contactList) + "\n"
                        + "Recently Added: \n";
        for (int i = 0; i < contactList.size(); i++) {
            str += i+1 + ". " + contactList.get(i).contacting() + "\n";
        }
        return str;
    }

    public String ls() {
        String str = "";
        for (int i = 0; i < contactList.size(); i++) {
            str += i+1 + ". " + contactList.get(i).contacting() + "\n";
        }
        return str;
    }

    public void clearContacts() {
        contactList.clear();
        hm.clear();
    }

    // groups contacts together
    public String group(String gname, int[] numbers) {
        ContactsList group = new ContactsList();
        for (int i = 0; i < numbers.length; i++) {
            group.addContacts(contactList.get(i));
        }
        groupings.put(gname, group);
        return "Group " + gname + " created! :)";
    }

    public String getGroup(String gname) throws FileNotFoundException {
        return "Group " + gname + ": \n" +
                groupings.get(gname).ls();
    }

    public void write() throws IOException {
        for (int i = 0; i < contactList.size(); i++) {
            contactList.get(i).writeToFile(storage.getFile());
        }
    }

    public void clearCurrentTasks() {
        contactList.clear();
    }


}
