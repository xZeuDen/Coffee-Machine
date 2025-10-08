import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class transaction {

    private static ArrayList<String> transactionLog = new ArrayList<>();

    //Method to complete a transaction
    public static void completeTransaction(int choice, ArrayList<MenuItem> items) {
        //Retrieve the selected item
        MenuItem item = items.get(choice - 1);
        System.out.printf("You have selected %s worth %.2f\n", item.getName(), item.getPrice());
        // Ask for payment method
        keyboard key = new keyboard();
        int paymentType = key.readInteger("Please choose payment method 1 (Cash) // 2 (Card):  ", "Error: Please type 1 or 2", 1, 2);

        String transactionEntry;
        //If payment is by cash
        if (paymentType == 1) {
            double tender = key.readDouble("Please enter the tender amount: ", "Error: Please enter a valid tender amount");
            double change = tender - item.getPrice();
            if (change < 0) {
                System.out.println("Insufficient funds. Payment declined.");
            } else {
                //Proccess cash payment
                System.out.printf("Thanks for the payment! Your change is %.2f \n", change);
                transactionEntry = createTransactionEntry(item, tender, change, null);
                transactionLog.add(transactionEntry);
                printReceipt(item, tender, change, null);
            }
        } else if (paymentType == 2) {
            //If payment is by card
            int cardType = key.readInteger("Please type 1 for Visa card or 2 for MasterCard: ", "Error: Please type 1 or 2: ", 1, 2);
            System.out.println("Thanks for your payment! See you later");
            String cardTypeName = (cardType == 1) ? "Visa" : "MasterCard";
            transactionEntry = createTransactionEntry(item, 0, 0, cardTypeName);
            transactionLog.add(transactionEntry);
            printReceipt(item, 0, 0, cardTypeName);
        }
    }

    //Method to create a transaction entry
    private static String createTransactionEntry(MenuItem item, double tender, double change, String cardType) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        if (cardType == null) {
            return formattedDateTime + ", " + item.getName() + ", Price: " + item.getPrice() + ", Tendered: " + tender + ", Change: " + change;
        } else {
            return formattedDateTime + ", " + item.getName() + ", Price:  " + item.getPrice() + ", Card: " + cardType;
        }
    }

    //Method to print a receipt
    public static void printReceipt(MenuItem item, double tender, double change, String cardType) {
        String receipt = generateReceipt(item, tender, change, cardType);
        System.out.println(receipt);
    }

    //Method to generate a receipt string
    private static String generateReceipt(MenuItem item, double tender, double change, String cardType) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        String receipt = 
        "\n----- RECEIPT -----\n" +
        "Date and Time: " + formattedDateTime + "\n" +
        "Item: " + item.getName() + "\n" +
        "Price: $" + item.getPrice() + "\n";
        if (tender > 0) {
            receipt += "Tendered: $" + tender + "\n";
            receipt += "Change: $" + change + "\n";
        }
        if (cardType != null) {
            receipt += "Payment method: " + cardType + "\n";
        }
        receipt += "-------------------\n\n";
        return receipt;
    }

    //Method to write transactions to a file
    public static void writeTransactionsToFile(String fileName) {
    double totalToday = calculateTotalToday();
    
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
        for (String transaction : transactionLog) {
            writer.println(transaction);
        }
        // Append total made today to the end of the file
        writer.printf("Total made today: %.2f\n", totalToday);
    } catch (IOException e) {
        System.out.println("Error writing to transaction file: " + e.getMessage());
    }
}

//Method to calculate the total amount made today
private static double calculateTotalToday() {
    double total = 0;
    for (String transaction : transactionLog) {
        String[] parts = transaction.split(", ");
        double price = Double.parseDouble(parts[2].substring(7)); // Extract the price from the transaction entry
        total += price;
    }
    return total;
}

}