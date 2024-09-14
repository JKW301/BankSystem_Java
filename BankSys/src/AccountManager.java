import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<String, Client> clients;
    private Map<String, Account> accounts;


    public AccountManager() {
        clients = new HashMap<>();
    }

    public Client createClient(String clientId, String name) {
        Client client = new Client(clientId, name);
        clients.put(clientId, client);
        System.out.println("Client created: " + name);
        client.saveToFile(clientId, name); // Appel à saveToFile après la création du client
        return client;
    }

    public Client getClient(String clientId) {
        Client client = clients.get(clientId);
        if (client == null) {
            client = loadClientFromFile(clientId);
            if (client != null) {
                clients.put(clientId, client);
            }
        }
        return client;
    }

    public Account createAccount(String accountNumber) {
        Account account = new Account(accountNumber);
        accounts.put(accountNumber, account);
        System.out.println("Account created: " + accountNumber);
        account.saveToFile(); // Appel à saveToFile après la création du compte
        return account;
    }

    public void deleteAccount(String accountNumber) {
        Account account = accounts.remove(accountNumber);
        if (account != null) {
            System.out.println("Account deleted: " + accountNumber);
        } else {
            System.out.println("Account not found: " + accountNumber);
        }
    }

    private Client loadClientFromFile(String clientId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(clientId + ".txt"))) {
            String line;
            String name = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    name = line.substring(6);
                    break;
                }
            }
            if (name != null) {
                return new Client(clientId, name);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the client.");
            e.printStackTrace();
        }
        return null;
    }
}