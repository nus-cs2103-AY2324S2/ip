package baron.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import baron.database.Database;
import baron.enums.Argument;
import baron.models.Client;
import baron.utils.StringUtils;
/**
 * Manages Client database operations.
 * Input format: client /sn A12345678X /email peterparker@u.nus.edu /cn 99801234 /name Peter.
 */
public class ClientDao extends BaseDao {
    public static final String NAME = "client";

    public ClientDao() {
        super(NAME);
    }

    /**
     * Adds a new client.
     *
     * @return The newly-created client
     */
    public Client add(Client client) {
        File table = Database.getTable(this.name);
        String data = toDataString(client);
        long id = Database.create(table.toPath(), data);
        client.setId((int) id);
        return client;
    }
    /**
     * Deletes the client by ID
     *
     * @param id index of the line to delete
     */
    public void delete(long id) {
        File table = Database.getTable(this.name);
        Database.delete(table.toPath(), id);
    }

    @Override
    public Client fromInputString(String input) {
        String studentNumber = getStudentNumber(input);
        String email = getEmail(input);
        String name = getName(input);
        String contactNumber = getContactNumber(input);
        return new Client(studentNumber, email, name, contactNumber);
    }
    @Override
    public Client fromDataString(String input) {
        String[] segments = input.split("\\s*\\|\\s*");
        int id = Integer.parseInt(segments[0]);
        String studentNumber = segments[1];
        String email = segments[2];
        String name = segments[3];
        String contactNumber = segments[4];
        Client client = new Client(id, studentNumber, email, name, contactNumber);
        return client;
    }

    public String toDataString(Client c) {
        return c.getStudentNumber() + " | " + c.getEmail() + " | " + c.getName() + " | " + c.getContactNumber();
    }

    @Override
    public Client getFrom(String input) {
        return null;
    }
    @Override
    public List<Client> getItems() {
        File table = Database.getTable(this.name);
        List<Client> models = new ArrayList<>();
        try {
            Files.lines(table.toPath()).forEach(line -> {
                Client t = fromDataString(line);
                models.add(t);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return models;
    }

    private String getStudentNumber(String input) {
        return StringUtils.getValueOfCommand(
                input,
                Argument.STUDENT_NUMBER.getArg(),
                Argument.EMAIL.getArg());
    }

    private String getEmail(String input) {
        return StringUtils.getValueOfCommand(
                input,
                Argument.EMAIL.getArg(),
                Argument.NAME.getArg());
    }
    private String getName(String input) {
        return StringUtils.getValueOfCommand(
                input,
                Argument.NAME.getArg(),
                Argument.CONTACT_NUMBER.getArg());
    }
    private String getContactNumber(String input) {
        return StringUtils.getValueOfCommand(
                input,
                Argument.CONTACT_NUMBER.getArg(),
                null);
    }
}
