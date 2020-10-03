package app;

/**
 * Item based on the data proveded in the SQL Item table.
 * @author Trevor Colton & Austin Fashimpaur
 *
 */
public class Item {
	private int id;
	private String name;
	private String brandName;
	private double price;
	private String size;
	public Item(int id, String name, String brandName, double price, String size) {
		super();
		this.id = id;
		this.name = name;
		this.brandName = brandName;
		this.price = price;
		this.size = size;
	}
	
	/**
	 * Retrieves Item ID number.
	 * @return int id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Retrieves item name.
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retrieves item brand name.
	 * @return String brand name 
	 */
	public String getBrandName() {
		return brandName;
	}
	
	/**
	 * Retrieves the item's price.
	 * @return double price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Retrieves the item's size.
	 * @return String size
	 */
	public String getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return id + " " + name + " " + brandName + " " + price + " (" + size + ")";
	}
	
	
}
