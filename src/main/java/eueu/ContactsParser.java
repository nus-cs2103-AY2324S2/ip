package eueu;

import eueu.contacts.Contacts;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a parser that parses user's String input for managing contact information
 * to a command for ContactsList to execute.
 */

public class ContactsParser {

    private ContactsList contactsList;
    static final String ENTER_CONTACT_INFO = "Enter contact information babez";
    static final String ENTER_CONTACT_NAME =  "Enter contact name babez";
    static final String EXIT_MESSAGE = "byeee love uu ttyl ok!";
    static final String CLEAR_CONTACTS = "Contacts cleared! :)";
    static final String GROUP_INDEX = "Enter contact index of the people to put in this group babez";
    static final String GROUP_NAME = "Enter group name babez";
    static final String NO_GROUP_FOUND = "No such group name found babez";
    static final String CONTACT_INSTRUCTIONS = "ENTER (CONTACT) INSTRUCTIONS";
    public ContactsParser(ContactsList contactsList) {
        this.contactsList = contactsList;
    }

    /**
     * Parses user's String input to commands that ContactList executes.
     * (i.e. -a, -d, ls, group, find group).
     *
     * @param command String input from user.
     * @throws IOException When file cannot be found.
     * @throws ArrayIndexOutOfBoundsException When user does not specify contact information.
     * @throws StringIndexOutOfBoundsException When user does not specify the contact name.
     * @throws NullPointerException When no group is found.
     * @return ChatBot's reply to user input.
     */
    public String parsing(String command) throws IOException, ArrayIndexOutOfBoundsException,
            StringIndexOutOfBoundsException, NullPointerException {
        String res = "";

        if (command.startsWith("-a")) { // add contact
            try {
                String str = command.substring(3);
                String[] arr = str.split("/");
                if (arr.length == 3) {
                    Contacts contact = new Contacts(arr[0], Integer.parseInt(arr[1]), arr[2]);
                    res = contactsList.addContacts(contact);
                } else {
                    Contacts contact = new Contacts(arr[0], Integer.parseInt(arr[1]));
                    res = contactsList.addContacts(contact);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                res = ENTER_CONTACT_INFO;
            } catch (StringIndexOutOfBoundsException e) {
                res = ENTER_CONTACT_NAME;
            }

        } else if (command.startsWith("-d")) { // delete contact
            try {
                String str = command.substring(3);
                res = contactsList.deleteContact(str);
            } catch (StringIndexOutOfBoundsException e) {
                res = ENTER_CONTACT_NAME;
            }
        } else if (command.startsWith("ls")) {
            res = contactsList.contactls();
        } else if (command.equals("bye")) {
            res = EXIT_MESSAGE;
            contactsList.write();
            contactsList.clearContacts();
        } else if(command.startsWith("clear ls")) {
            FileWriter fw = new FileWriter("data/Contacts.txt", false);
            fw.close();
            res = CLEAR_CONTACTS;
        } else if (command.startsWith("group")) {
            try {
                String str = command.substring(6);
                String[] arr = str.split("/");
                int[] num = new int[arr.length-1];
                for(int i = 0; i < arr.length-1; i++) {
                    num[i] = Integer.parseInt(arr[i+1]);
                }
                res = contactsList.group(arr[0], num);
            } catch (ArrayIndexOutOfBoundsException e) {
                res = GROUP_INDEX;
            } catch (StringIndexOutOfBoundsException e) {
                res = GROUP_NAME;
            }
        } else if (command.startsWith("find group")) {
            try {
                res = contactsList.getGroup(command.substring(11));
            } catch (StringIndexOutOfBoundsException e) {
                res = GROUP_NAME;
            } catch (NullPointerException e) {
                res = NO_GROUP_FOUND;
            }
        } else {
            res = CONTACT_INSTRUCTIONS;
        }

        return res;
    }


}
