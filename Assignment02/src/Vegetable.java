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
 * This class also extends the FoodItem class which iintakes the farm name of the vegetables. The constructors 
 * and methods are called or invoked with the help of the super keyword.  
 * @author hp
 *
 */

public class Vegetable extends FoodItem {
	/*
	 * name of the farm from vegetable comes
	 */
	private String farmName;

	public Vegetable() {
		super();
		farmName = "";
	}

	/**
	 * Thos method adds the value of the farm name of the vegetable. Being the string type value entered so there is
	 * no need of exception handling, but yes the method returns true upon successful insertion of the variable.
	 */
	@Override
	public boolean addItem(Scanner rex, boolean fromFile) {
		if (super.addItem(rex, fromFile)) {
			if (!fromFile)
				System.out.print("Enter the name of the farm supplier: ");
			farmName = rex.next();
		}
		return true;
	}


	/**
	 * This method prints the details to the console. Rest of the fields printed are called and further mentioned.
	 */
	@Override
	public String toString() {
		String name = "";
		name = " farm supplier: " + farmName;
		return super.toString() + name;
	}

	/**
	 * outputting the method to the file.
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("v\n");
		super.outputItem(writer);
		writer.format("%s\n", farmName);
	}
}
//end of vegetable class