import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        AccountManager accountManager = new AccountManager();
        Scanner scanner = new Scanner(System.in);

        // Login
        System.out.println("Are you a manager or a client? (Enter 'manager' or 'client')");
        String userType = scanner.nextLine();

        if (userType.equalsIgnoreCase("manager")) {
            handleManagerOptions(accountManager, scanner);
        } else if (userType.equalsIgnoreCase("client")) {
            handleClientOptions(accountManager, scanner);
        } else {
            System.out.println("Invalid user type");
        }

        scanner.close();
    }

    private static void handleManagerOptions(AccountManager accountManager, Scanner scanner) {
        while (true) {
            System.out.println("Manager Options: ");
            System.out.println("1. Create Client");
            System.out.println("2. Create Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter client ID:");
                    String clientId = scanner.nextLine();
                    System.out.println("Enter client name:");
                    String name = scanner.nextLine();
                    accountManager.createClient(clientId, name);
                    break;
                case 2:
                    System.out.println("Enter account number to create:");
                    String accountNumber = scanner.nextLine();
                    accountManager.createAccount(accountNumber);
                    break;
                case 3:
                    System.out.println("Enter account number to delete:");
                    accountNumber = scanner.nextLine();
                    accountManager.deleteAccount(accountNumber);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void handleClientOptions(AccountManager accountManager, Scanner scanner) {
        System.out.println("Enter your client ID:");
        String clientId = scanner.nextLine();
        Client client = accountManager.getClient(clientId);

        if (client != null) {
            System.out.println("Client: " + client.getName());
            // Assuming getAccounts() method exists in Client class
            for (Account account : client.getAccounts()) {
                account.displayAccountDetails();
            }
        } else {
            System.out.println("Client not found");
        }
    }
}