/**
 * Assessment: Assignment02
 * Student name: Harsh Bansal
 * Student number: 041005659
 * Section: CST8130-300-302
 * Profesor Name: James Mwangi
 * Due Date: July 18, 2021 Midnight and demo during the lab period
 */
import java.util.Scanner;

import java.util.Formatter;

/**
 * This class qualifies the third type of the food item i.e. the preserve or the drinks that are prepared on the 
 * cart at that time. Concept of inheritence is also obeyed as the Preserve class inherits or extends the FoodItem 
 * class.
 * @author hp
 *
 */
public class Preserve extends FoodItem {
	/*
	 * jar size in ml
	 */
	private int jarSize;

	public Preserve() {
		super();
		jarSize = 0;
	}

	/**
	 * Being an integer value the value of jar size is taken in such a way that the entered value should never be of 
	 * wrong data type and the jar size being an integer cannot be negative or zero. This is done with the help of do-
	 * while loop such that loop iterates while the input entered is valid. At the last the method returns true.
	 */
	@Override
	public boolean addItem(Scanner rex, boolean fromFile) {

		super.addItem(rex, fromFile);

		while (true) {
			try {
				if (!fromFile)
					System.out.print("Enter the size of the jar in milliliters: ");
				int size = Integer.parseInt(rex.next());
				this.jarSize = size;
				return true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid size");
			}
		}
	}

	/**
	 * method toString to print elements to console.
	 */
	@Override
	public String toString() {
		String name = "";
		name = " size: " + jarSize + "mL";
		return super.toString() + name;
	}

	/**
	 * method outputItem in the file
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("p\n");
		super.outputItem(writer);
		writer.format("%d\n", jarSize);
	}
}// end of preserve class
