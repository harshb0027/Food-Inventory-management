/**
 * Assessment: Assignment02
 * Student name: Harsh Bansal
 * Student number: 041005659
 * Section: CST8130-300-302
 * Profesor Name: James Mwangi
 * Due Date: July 18, 2021 Midnight and demo during the lab period
 */
import java.util.Scanner;

import java.util.regex.Pattern;

/**
 * This vlass has the main method that displays the index and finally run the subsequent methods as per the commands
 * @author hp
 *
 */
public class Assign02 {
	/**
	 * Static method that displays only the menu.
	 */
	public static void displayMenu() {
		System.out.println("Please select one of the following:");
		System.out.println("1: Add Item to Inventory");
		System.out.println("2: Display Current Inventory");
		System.out.println("3: Buy Item(s)");
		System.out.println("4: Sell Item(s)");
		System.out.println("5: Search for Item");
		System.out.println("6: Save Inventory to File");
		System.out.println("7: Read Inventory from File");
		System.out.println("8: To Exit");
		System.out.print("> ");
	}

	/**
	 * Main method that will call all the appropriate method form the previous classes.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner rex = new Scanner(System.in);
		rex.useDelimiter(Pattern.compile("[\\r\\n]+"));
		Inventory inventory = new Inventory();
		int choice = 0;
		while (choice != 8) {
			try {
				displayMenu();	//calling the display menu method
				if (rex.hasNext(Pattern.compile("[1-8]"))) {	
					choice = rex.nextInt();
					switch (choice) {
					case 1: 		//if the option is 1
						if (!inventory.addItem(rex, false))
							System.out.println("Error...could not add item");
						break;
					case 2: 		//if the option is 2 to print the item
						System.out.println(inventory);
						break;
					case 3: 		//if the option is 3 to update the quantitty
						if (!inventory.updateQuantity(rex, true))
							System.out.println("Error...could not buy item");
						break;
					case 4: 		//if option is 4 to sell the item
						if (!inventory.updateQuantity(rex, false))
							System.out.println("Error...could not sell item");
						break;
					case 5:			//to search for the item
						inventory.searchForItem(rex);
						break;
					case 6:			//to save to the file
						inventory.saveToFile(rex);
						break;
					case 7:			//to read from the file
						inventory.readFromFile(rex);
						break;
					case 8: 		//to exit the method
						System.out.println("Exiting...");
						break;
					default: 		//if the option is invalid
						System.out.println("Invalid");
						break;
					}
				} else {
					System.out.println("Invalid");
					rex.next();
				}
			} catch (Exception e) {  //catch block
				System.out.println("Error Occurred: " + e.getMessage());
			}//end catch
		}
		rex.close();
	}//end main
}