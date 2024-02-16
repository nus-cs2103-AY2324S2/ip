package grizzly.contacts;

import java.util.Hashtable;
import java.util.stream.Stream;

import grizzly.exceptions.ContactCreationException;
import grizzly.exceptions.MissingInformationException;
import grizzly.exceptions.MissingParameterException;
import grizzly.utils.Parser;

/**
 * This class implements the Contact record to be saved by the bot
 */
public class Contact {

    private static final String[] REQUIRED_PARAMS = {"description", "email", "number"};

    private String name;
    private String email;
    private int number;

    /**
     * Creates a Contact object
     *
     * @param name
     * @param email
     * @param number
     */
    public Contact(String name, String email, int number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

    /**
     * Creates a Contact object given parameters in a Hashtable.
     *
     * @param params
     * @throws ContactCreationException
     * @throws MissingInformationException
     * @throws MissingParameterException
     */
    public static Contact contactParse(Hashtable<String, String> params)
            throws ContactCreationException, MissingInformationException, MissingParameterException {

        Parser.checkParams(params, REQUIRED_PARAMS);

        String[] filteredParams = Stream.of(REQUIRED_PARAMS).map(x -> params.get(x)).toArray(String[]::new);

        String name = filteredParams[0];
        String email = filteredParams[1];
        String numString = filteredParams[2];
        int number;

        try {
            number = Integer.parseInt(numString);
        } catch (NumberFormatException e) {
            throw new ContactCreationException("Invalid number input");
        }

        return new Contact(name, email, number);
    }

    @Override
    public String toString() {
        return this.name + " | " + this.email + " | " + this.number;
    }

    public String toSave() {
        return "[C]|" + this.name + "|" + this.email + "|" + this.number;
    }

}
