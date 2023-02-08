/**
 * Assessment: Assignment01
 * Student name: Harsh Bansal
 * Student number: 041005659
 * Section: CST8130-300-302
 * Profesor Name: James Mwangi
 * Due Date: June 13, 2021 Midnight and demo during the lab period
 */
import java.util.Scanner;

import java.util.Formatter;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
/**
 * This class is the main classas it holds all the elements of the inventory array that are the objextss of the fooditem
 * class. 
 * @author hp
 *
 */
public class Inventory {
  /**
   * list of the items that displays inventory
   */
	private ArrayList<FoodItem> inventory;		//declaration of the arraylist 
	private int numItems;
	
	/**
	 * default constructor for initializatiojn of the array
	 */
	public Inventory() {
		inventory = new ArrayList<FoodItem>(20);	//putting the default length of the arraylist which is 20
	}
	/**
	 * The method to finally add all the objects to the inventory array by differentiating into the food, vegetable or preserve
	 * @param rex  takes in the scanner object
	 * @return  returns the boolean true
	 */
	public boolean addItem(Scanner rex, boolean fromFile) {
		boolean valid = false;
		FoodItem item = null;
		while (!valid) {
			/*
			 * if not from a file
			 */
			if (!fromFile)
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			if (rex.hasNext(Pattern.compile("[fFvVpP]"))) {
				String choice = rex.next();
				switch (choice.toLowerCase()) {
				case "f":
					item =  new Fruit();	//creating a fruit
					break;
				case "v":
					 item =  new Vegetable();	//creating a vegetable
					break;
				case "p":	
					item = new Preserve();			//creating the preserve
					break;
				default: 
					item = new FoodItem();
					break;
				}
				valid = true;
			} else {
				//System.out.println("Invalid entry");
				rex.next();
				valid = false;
			}
		}
		
		/**
		 * if item is already exists, then prompt an error message that item is existing already.....
		 */
		if (item.inputCode(rex, fromFile)) {
			if (alreadyExists(item) < 0) {
				if (item.addItem(rex, fromFile)) {	//calling the method from the food item object
					insertItem(item);	//calling the insert item method
					return true;
				}
				return false;
			} else {
				System.out.println("Item code already exists");
				return false;
			}
		}
		return true;
	}
	
/**
 * method binary search for searching the elemnet the in the whole arraylist. 
 */
	private int binarySearch(int itemCode, int firstnumber, int lastnumber) {
		int middlenumber = (firstnumber + lastnumber) / 2;
		if (lastnumber < firstnumber)
			return -1;
		if (inventory.isEmpty())
			return -1;
		if (inventory.get(middlenumber).getItemCode() < itemCode)
			return binarySearch(itemCode, middlenumber + 1, lastnumber);
		if (inventory.get(middlenumber).getItemCode() == itemCode)
			return middlenumber;
		if (inventory.get(middlenumber).getItemCode() > itemCode)
			return binarySearch(itemCode, firstnumber, middlenumber - 1);
		return -1;
	}
	/**
	 * method alreadyExists testify if the item is already existing in the arraylist.
	 */
		public int alreadyExists(FoodItem item) {
			return binarySearch(item.getItemCode(), 0, inventory.size() - 1);
		}
/**
 *  to insert a item using the comparartor.....
 */
	private void insertItem(FoodItem item) {

		FoodItemComparator x = new FoodItemComparator();		//creating the comparator object
		for (int i = 0; inventory.size() > i; i++) {

			if (x.compare(inventory.get(i), item) >= 0) {
				inventory.add(i, item);
				return;
			}
		}
		inventory.add(item);
	}
	/**
	 * method update Quantity. It calls the metthods from the FoodItem class so that the quantity is added or removed on the
	 * pressing of the command.
	 */		
			public boolean updateQuantity(Scanner rex, boolean buyOrSell) {
				
				if (inventory.isEmpty())
					return false;

				FoodItem temp = new FoodItem();
				temp.inputCode(rex, false);
				int index = alreadyExists(temp);
				if (index != -1) {
					String buyerorSell = buyOrSell ? "buy" : "sell";
					System.out.print("Enter valid quantity to " + buyerorSell + ": ");
					if (rex.hasNextInt()) {
						int amount = rex.nextInt();
						if (amount > 0) {
							return inventory.get(index).updateItem(buyOrSell ? amount : amount * -1);
						} else {
							System.out.println("Invalid quantity...");
						}
					} else {
						System.out.println("Invalid quantity...");
					}
				}
				return false;
			}
			/**
			 * method readFromFile from the file whose name will be specified.
			 * @param scanner object 
			 */
				public void readFromFile(Scanner scanner) {
					try {
						System.out.print("Enter the filename to read from: ");
						String nameofthefile = scanner.next();
						//creating the file
						File input = new File(nameofthefile);
						if (input.exists()) {	//testing of the file already exists
							Scanner fileReader = new Scanner(input);		//in the file reader mode
							fileReader.useDelimiter("[\\r\\n]+");
							while (fileReader.hasNext()) {
								if (!addItem(fileReader, true)) {	//error generated if the item already exists in file 
									System.out.println("Error Encountered while reading the file, aborting...");
									break;
								}
							}
						} else {
							System.out.println("File Not Found, ignoring...");
						}
					} catch (Exception e) {
						System.err.println("Invalid");
					}
				}
				/**
				 * method to save a file to the file whose name is speciifed
				 * @param scannner object 
				 */
					public void saveToFile(Scanner scanner) {
						try {
							System.out.print("Enter the filename to save to: ");
							String nameofthefile = scanner.next();
							//creating the file to write all the input.
							File input = new File(nameofthefile);
							input.createNewFile();
							input.setWritable(true);
							Formatter writer = new Formatter(input);	//writing to the file
							ListIterator<FoodItem> iter = inventory.listIterator();	//using the list iterator class
							while (iter.hasNext()) {	//reading the word
								iter.next().outputItem(writer);
							}
							writer.flush(); //calling flush method to flush out all the input
							writer.close();	//closing the scanner
						} catch (IOException e) {
							System.out.println("Could not create file, " + e.getMessage());
						} catch (Exception e) {
							System.err.println("Invalid");
						}
					}
			/**
			 * method searchForItem
			 * @param the scanner object which will be used in the whole method........
			 */
				public void searchForItem(Scanner rex) {
					FoodItem itemToSearchFor = new FoodItem();
					itemToSearchFor.inputCode(rex, false); //searching for the item using the method of binary search
					int index = binarySearch(itemToSearchFor.getItemCode(), 0, inventory.size() - 1);
					if (index == -1)
						System.out.println("Code not found in inventory...");
					else
						System.out.println(inventory.get(index).toString());
				}
				/**
				 * method toString that will return the complete string......
				 * @return type of the method is string
				 */
					@Override
					public String toString() {
						String returnString = "Inventory:\n";
						ListIterator<FoodItem> items = inventory.listIterator();
						while (items.hasNext())	//printing the items to the console
							returnString = returnString + items.next().toString() + "\n";
						return returnString;
					}

					




}