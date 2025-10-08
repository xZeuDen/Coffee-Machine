import java.util.ArrayList;
public class menu{	
	public static void displayMenu(ArrayList<MenuItem> items){
		//Display menu
		System.out.println("Coffee Menu");
		System.out.println("=======================");
		//Loop through options and items
		for(int i = 0; i < items.size(); i++){
			MenuItem item = items.get(i);
			System.out.printf("%d. %s \t %.2f \n ", i + 1, item.getName(), item.getPrice());
		}
		//Print the exit option
		System.out.printf("%d. Exit\n ",items.size() + 1);
		System.out.println("=======================");
	}
}