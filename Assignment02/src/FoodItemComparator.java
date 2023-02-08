/**
 * Assessment: Assignment02
 * Student name: Harsh Bansal
 * Student number: 041005659
 * Section: CST8130-300-302
 * Profesor Name: James Mwangi
 * Due Date: July 18, 2021 Midnight and demo during the lab period
 */
import java.util.Comparator;

/**
 * 
 * @author hp
 *
 */
public class FoodItemComparator implements Comparator<FoodItem> {
	/**
	 * method to compare the foood item with the item that is in the arraylist.
	 */
	@Override
	public int compare(FoodItem rex, FoodItem input) {
		return rex.getItemCode() - input.getItemCode();
	}

}