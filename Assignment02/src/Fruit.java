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
 * This class inherits the fields and methods from the parent class i.e. FoodItem. It satisfies the one condition of
 * food item being fruit. Here the user enters the orchard name from where the fruit is taken and calling the methods 
 * of parent class with the help of super(); 
 * @author hp
 *
 */
public class Fruit extends FoodItem {
	/*
	 * name of the orchard where the fruit is from
	 */
	private String orchardName;
	
	/**
	 * This method returns the string and prints the inventory to the console.
	 */
	public Fruit() {
		super();
		orchardName = "";
	}

	/**
	 * This method adds the item to the inventory. It adds the name of the orchard to the object of the food item. and
	 * then returns the method to be true.
	 */
	@Override
	public boolean addItem(Scanner rex, boolean fromFile) {
		if (super.addItem(rex, fromFile)) {
			if (!fromFile)

				System.out.print("Enter the name of the orchard supplier: ");
			orchardName = rex.next();
		}
		return true;
	}

	/*
	 * method toString
	 */
	@Override
	public String toString() {
		String name = "";
		name = " orchard supplier: " + orchardName;
		return super.toString() + name;
	}

	/*
	 * method outputItem
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("f\n");
		super.outputItem(writer);
		writer.format("%s\n", orchardName);
	}
}// end of fruit class