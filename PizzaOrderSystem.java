class AccountService {
    public boolean checkAccount(String cardNumber) {
        // Simulate account checking
        System.out.println("Checking account for card number: " + cardNumber);
        return true; // Assume account exists
    }
}

class SecurityService {
    public boolean validatePin(String cardNumber, String pin) {
        // Simulate PIN validation
        System.out.println("Validating PIN for card number: " + cardNumber);
        return pin.equals("1234"); // Assume "1234" is the correct PIN
    }
}

class BalanceService {
    public boolean checkBalance(String cardNumber, double amount) {
        // Simulate balance check
        System.out.println("Checking balance for card number: " + cardNumber);
        return amount <= 500; // Assume max balance is $500
    }

    public void deductAmount(String cardNumber, double amount) {
        // Simulate balance deduction
        System.out.println("Deducting " + amount + " from card number: " + cardNumber);
    }
}

class LedgerService {
    public void makeEntry(String cardNumber, double amount, String operationType) {
        // Simulate ledger entry
        System.out.println("Making ledger entry for card number: " + cardNumber + " | Amount: " + amount + " | Operation: " + operationType);
    }
}

class NotificationService {
    public void sendNotification(String cardNumber, String message) {
        // Simulate sending notification
        System.out.println("Sending notification to card number: " + cardNumber + " | Message: " + message);
    }
}

class PizzaOrderFacade {
    private AccountService accountService;
    private SecurityService securityService;
    private BalanceService balanceService;
    private LedgerService ledgerService;
    private NotificationService notificationService;

    public PizzaOrderFacade() {
        accountService = new AccountService();
        securityService = new SecurityService();
        balanceService = new BalanceService();
        ledgerService = new LedgerService();
        notificationService = new NotificationService();
    }

    public void orderPizza(String cardNumber, String pin, double amount, String operationType) {
        System.out.println("Starting pizza order process...");

        if (!accountService.checkAccount(cardNumber)) {
            System.out.println("Account verification failed.");
            return;
        }

        if (!securityService.validatePin(cardNumber, pin)) {
            System.out.println("Invalid PIN.");
            return;
        }

        if (!balanceService.checkBalance(cardNumber, amount)) {
            System.out.println("Insufficient balance.");
            return;
        }

        balanceService.deductAmount(cardNumber, amount);
        ledgerService.makeEntry(cardNumber, amount, operationType);
        notificationService.sendNotification(cardNumber, "Your pizza order of $" + amount + " has been successfully placed.");

        System.out.println("Pizza order completed successfully!");
    }
}

public class PizzaOrderSystem {
    public static void main(String[] args) {
        PizzaOrderFacade pizzaOrderFacade = new PizzaOrderFacade();

        // Order a pizza using the facade
        pizzaOrderFacade.orderPizza("1234567890123456", "1234", 25.0, "Debit");
    }
}
