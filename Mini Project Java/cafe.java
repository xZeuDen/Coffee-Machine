import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class cafe{
	public static void readInventoryFile(String fileName, ArrayList<MenuItem> items){
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				//Using split method to allow us to separate coffe and price with a " , "
				String tokens[] = line.split(",");
				String name = tokens[0];
				double price = Double.parseDouble(tokens[1]);
				items.add(new MenuItem(name, price));
			}
			br.close();
		}
		catch(IOException e) {
			System.out.println("Error - cannot read from file" + fileName);
		}
	}

	public static void main(String[] args) {

		//User choice
		int choice;
		//Menu data
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

		//Read inventory.txt and create menu items
		readInventoryFile("inventory.txt", menuItems);

		//Exit option
		int EXIT = menuItems.size() + 1 ;
		//Create a keyboard object for input validation
		keyboard key = new keyboard();


		menu.displayMenu(menuItems);

		//Setup scanner
		Scanner in = new Scanner(System.in);

		//Get choice from user
		choice = key.readInteger("Enter choice: ", "Error: invalid input", 1, EXIT);

		//Menu loop
		while (choice != EXIT ){
			//Check choice value
			transaction.completeTransaction(choice,menuItems);
			//Display menu
			menu.displayMenu(menuItems);
			//Get choice from user
			choice = key.readInteger("Enter choice: ","Error: invalid input",1,EXIT);
		}
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            transaction.writeTransactionsToFile("transactions.txt");
        }));
		System.out.println("Thanks, See you later!");
	}
}

