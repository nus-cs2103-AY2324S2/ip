package eueu;

import eueu.contacts.Contacts;

import java.io.FileNotFoundException;
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
    static final String FILE_NOT_FOUND = "sowwie babes file not found";
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
            res = addContact(command);
        } else if (command.startsWith("-d")) { // delete contact
            res = deleteContact(command);
        } else if (command.startsWith("ls")) {
            res = ls();
        } else if (command.equals("bye")) {
            res = bye();
        } else if(command.startsWith("clear ls")) {
            clearls();
        } else if (command.startsWith("group")) {
            res = group(command);
        } else if (command.startsWith("find group")) {
            res = findGroup(command);
        } else {
            res = CONTACT_INSTRUCTIONS;
        }
        return res;
    }

    /**
     * Adds a contact based on the input command.
     *
     * @param command the command to add a contact
     * @return a message indicating the result of the add operation
     */
    public String addContact(String command) {
        String res = "";
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
        return res;
    }

    /**
     * Deletes a contact with the specified name.
     *
     * @param command the command to delete a contact
     * @return a message indicating the result of the delete operation
     */
    public String deleteContact(String command) {
        String res = "";
        try {
            String str = command.substring(3);
            res = contactsList.deleteContact(str);
        } catch (StringIndexOutOfBoundsException e) {
            res = ENTER_CONTACT_NAME;
        }
        return res;
    }

    /**
     * Lists all contacts.
     *
     * @return a string containing the list of contacts
     * @throws FileNotFoundException if the file containing contacts is not found
     */
    public String ls() throws FileNotFoundException {
        return contactsList.contactls();
    }

    /**
     * Performs necessary cleanup operations before exiting the application.
     * Writes contacts to file and clears the list of contacts.
     *
     * @return a message indicating successful application exit
     * @throws IOException if an I/O error occurs while writing contacts to file
     */
    public String bye() throws IOException {
        String res = EXIT_MESSAGE;
        contactsList.write();
        contactsList.clearContacts();
        return res;
    }

    /**
     * Clears the list of contacts.
     *
     * @return a message indicating successful clearing of contacts
     * @throws IOException if an I/O error occurs while clearing contacts
     */
    public String clearls() throws IOException {
        FileWriter fw = new FileWriter("data/Contacts.txt", false);
        fw.close();
        return CLEAR_CONTACTS;
    }

    /**
     * Groups contacts based on the input command.
     * The command should be in the format: "group/GroupName/ContactIndex1/ContactIndex2/..."
     *
     * @param command the command to group contacts
     * @return a message indicating the result of the grouping operation
     */
    public String group(String command) {
        String res = "";
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
        return res;
    }

    /**
     * Finds contacts belonging to the specified group.
     *
     * @param command the command to find contacts by group
     * @return a string containing the list of contacts in the specified group
     */
    public String findGroup(String command) {
        String res = "";
        try {
            res = contactsList.getGroup(command.substring(11));
        } catch (StringIndexOutOfBoundsException e) {
            res = GROUP_NAME;
        } catch (NullPointerException e) {
            res = NO_GROUP_FOUND;
        } catch (FileNotFoundException e) {
            res = FILE_NOT_FOUND;
        }
        return res;
    }
}
