package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.Client;

public class ClientList {
    protected ArrayList<Client> clients = new ArrayList<>();

    public void add(Client client) {
        clients.add(client);
    }

    public void delete(Integer ind) {
        clients.remove(clients.get(ind));
    }

    public Client get(Integer ind) {
        return clients.get(ind);
    }

    public Integer size() {
        return clients.size();
    }

    /**
     * Lists out all the clients in the list.
     *
     * @return String of all of the clients.
     */
    public String list() {
        if (clients.size() == 0) {
            return "You don't have any clients!";
        }
        String output = "";
        for (int i = 0; i < clients.size(); i++) {
            output += (i + 1) + ". " + clients.get(i);
            output += "\n";
        }
        return output;
    }

    /**
     * Formats all the clients into the correct format to save.
     *
     * @return ArrayList of clients in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < clients.size(); i++) {
            toSave.add(clients.get(i).formatToSave());
        }
        return toSave;
    }    
}
