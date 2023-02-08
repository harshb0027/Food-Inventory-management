/**
 * Assessment: Assignment02
 * Student name: Harsh Bansal
 * Student number: 041005659
 * Section: CST8130-300-302
 * Profesor Name: James Mwangi
 * Due Date: July 18, 2021 Midnight and demo during the lab period
 */
import java.util.Formatter;
import java.util.Scanner;

/**
 * 
 * @author hp
 *
 */
public class FoodItem {
	/*
	 * itemCode for food item
	 */
	private int itemCode;
	/*
	 * itemCost for food item
	 */
	private float itemCost;
	/*
	 * itemName for food item
	 */
	private String itemName;
	/*
	 * itemPrice for food item
	 */
	private float itemPrice;
	/*
	 * itemQuantityInStock for food item
	 */
	private int itemQuantityInStock;
	
	/**
	 * Constructor method
	 */
	public FoodItem() {
		itemCode = 0;
		itemName = "";
		itemPrice = 0.0f;
		itemCost = 0.0f;
		itemQuantityInStock = 0;
	}

	/**
	 * This method displays the data to the console. It displays the final inventory information.
	 * @return returns the String type object.
	 * method toString
	 */
	public String toString() {
		String name = "";
		String Price = String.format(" price: $%.2f", itemPrice);
		name = "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " " + "price:$" + itemPrice
				+ " Cost: $" + itemCost;
		return name;
	}

	/**
	 * This method helps the user to enter the items after testing the item code that if it is present in the array.
	 * @param rex  scanner object
	 * @return	the boolean type true is the method successfully returns true otherwise returns false.
	 */
	public boolean addItem(Scanner rex, boolean fromFile) {
		boolean input = false;

		if (!fromFile)
			System.out.print("Enter the name for the item: ");
		itemName = rex.next();
		// Input quantity
		while (!input) {
			if (!fromFile)
				System.out.print("Enter the quantity for the item: ");
			if (rex.hasNextInt()) {
				itemQuantityInStock = rex.nextInt();
				if (itemQuantityInStock < 0) {
					input = false;
					System.out.println("Invalid input");
					itemQuantityInStock = 0;
				} else
					input = true;
			} else {
				System.out.println("Invalid input");
				rex.next();
				input = false;
			}
		}

		// Input the cost
		input = false;
		while (!input) {
			if (!fromFile)
				System.out.print("Enter the cost of the item: ");
			if (rex.hasNextFloat()) {
				itemCost = rex.nextFloat();
				if (itemCost < 0) {
					input = false;
					System.out.println("Invalid input");
					itemCost = 0;
				} else
					input = true;
			} else {
				System.out.println("Invalid input");
				rex.next();
				input = false;
			}
		}

		// Input the price
		input = false;
		while (!input) {
			if (!fromFile)
				System.out.print("Enter the sales price of the item: ");
			if (rex.hasNextFloat()) {
				itemPrice = rex.nextFloat();
				if (itemPrice < 0) {
					input = false;
					System.out.println("Invalid input");
					itemPrice = 0;
				} else
					input = true;
			} else {
				System.out.println("Invalid input");
				rex.next();
				input = false;
			}
		}
		return true;
	}

	public int getItemCode() {
		return itemCode;
	}
	
	/**
	 * This method tests whether the item code of the item being tested in the parameter and the basic code of the
	 * iem are exactly the same.  
	 * @param item  object of which item's item code is tested
	 * @return a boolean type true if it successfully executes the code and false if it is not.
	 */
	public boolean isEqual(FoodItem item) {
		return itemCode == item.itemCode;
	}

	/**
	 * 
	 * @param amount
	 * @return the boolean true or false.
	 */
	public boolean updateItem(int amount) {
		if (amount < 0) {
			if (itemQuantityInStock >= Math.abs(amount)) {
				itemQuantityInStock = itemQuantityInStock + amount;
				return true;
			} else {
				return false;
			}
		} else {
			itemQuantityInStock = itemQuantityInStock + amount;
			return true;
		}

	}

	/**
	 * This method exclusively prompts the user to enter the item code entered for calling the alreadyExists() method
	 * to match both the objects' item code. It takes in an integer value and returns true if the method successfully
	 * runs the code
	 * @param rex  scanner object as the parameter
	 * @return returns the boolean type value true if code successfully executed otherwise false
	 */
	public boolean inputCode(Scanner rex, boolean fromFile) {
		boolean input = false;
		while (!input) {
			if (!fromFile)
				System.out.print("Enter the code for the item: ");
			if (rex.hasNextInt()) {
				itemCode = rex.nextInt();
				input = true;
			} else {
				System.out.println("Invalid code");

				rex.next();
			}
		}
		return input;
	}

	/**
	 * Outputting the items to the file.
	 * @param writer -formatter for output
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n", itemCode);
		writer.format("%s\n", itemName);
		writer.format("%d\n", itemQuantityInStock);
		writer.format("%.2f\n", itemCost);
		writer.format("%.2f\n", itemPrice);
	}
}