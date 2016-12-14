package main.java.com.commercehub.ims;

/**
 * This class describes the product with location as Product Id or product
 * location and level as quantity of product available in the inventory. It also
 * contains getter and setter methods for location and level. 'add' method
 * updates the quantity of the product on restocking. 'pick' method updates the
 * quantity of the product on picking.
 */
public class Product {

	//
	private String location;
	private int level;
	private boolean lock = false;

	/**
	 * @param productId:
	 *            location of the product
	 * @param productCount:
	 *            initial level of product
	 */
	public Product(String productId, int productCount) {
		location = productId;
		level = productCount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Adds 'count' amount to the level of product if product is not in used
	 * 
	 * @param count
	 *            Amount of product to be added
	 * @throws LockedProductException
	 * @throws InvalidQuantityException
	 */
	public void add(int count) throws LockedProductException, InvalidQuantityException {
		if (!isLock()) {
			setLock(true);
			if (count > 0)
				this.level += count;
			else
				throw new InvalidQuantityException();

		} else {
			throw new LockedProductException();
		}
		setLock(false);
	}

	/**
	 * Removes 'count' amount from the level of product if product is not in
	 * used
	 * 
	 * @param count
	 *            Amount of product to be picked
	 * @throws InvalidQuantityException
	 * @throws LockedProductException
	 */
	public void pick(int count) throws InvalidQuantityException, LockedProductException {
		if (!isLock()) {
			setLock(true);
			if (count > 0 && count <= this.level)
				this.level -= count;
			else
				throw new InvalidQuantityException();
		} else {
			throw new LockedProductException();
		}
		setLock(false);
	}

	public boolean isLock() {
		return lock;
	}

	private void setLock(boolean lock) {
		this.lock = lock;
	}

}
