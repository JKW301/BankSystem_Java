import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String clientId;
    private String name;
    private List<Account> accounts;

    public Client(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void saveToFile(String clientId, String name) {
        try (FileWriter writer = new FileWriter(clientId + ".txt")) {
            writer.write("Client ID: " + clientId + "\n");
            writer.write("Name: " + name + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the client.");
            e.printStackTrace();
        }
    }
}