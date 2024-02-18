package duke;

import duke.contacts.Contacts;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a parser that parses user's String input
 * for managing contact information
 * to a command for ContactsList to execute.
 */

//ADD STATIC FINAL FOR MAGIC STRINGS
    // ADD JAVADOCS
public class ContactsParser {

    private ContactsList contactsList;

    public ContactsParser(ContactsList contactsList) {
        this.contactsList = contactsList;
    }

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
                res = "I think you need to enter contact information babez";
            } catch (StringIndexOutOfBoundsException e) {
                res = "I think you need to enter contact name babez";
            }

        } else if (command.startsWith("-d")) { //delete contact
            try {
                String str = command.substring(3);
                res = contactsList.deleteContact(str);
            } catch (StringIndexOutOfBoundsException e) {
                res = "I think you need to enter contact name babez";
            }
//        } else if (command.startsWith("pin")) { //pins contact
//
        } else if (command.startsWith("ls")) {
            res = contactsList.contactls();
        } else if(command.startsWith("clear ls")) {
            FileWriter fw = new FileWriter("data/Contacts.txt", false);
            fw.close();
            res = "Contacts cleared! :)";
        } else if (command.startsWith("group")) { //groups contacts by index in contactlist
            try {
                String str = command.substring(6);
                String[] arr = str.split("/");
                int[] num = new int[arr.length-1];
                for(int i = 0; i < arr.length-1; i++) {
                    num[i] = Integer.parseInt(arr[i+1]);
                }
                res = contactsList.group(arr[0], num);
            } catch (ArrayIndexOutOfBoundsException e) {
                res = "I think you need to enter contact index of the people to put in this group babez";
            } catch (StringIndexOutOfBoundsException e) {
                res = "I think you need to enter group name babez";
            }

        } else if (command.startsWith("find group")) { //finds groups with names
            try {
                res = contactsList.getGroup(command.substring(11));
            } catch (StringIndexOutOfBoundsException e) {
                res = "I think you need to enter group name babez";
            } catch (NullPointerException e) {
                res = "No such group name found babez";
            }
        } else {
            res = "ENTER (CONTACT) INSTRUCTIONS";
        }

        return res;
    }


}
