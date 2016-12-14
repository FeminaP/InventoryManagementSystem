package main.java.com.commercehub.ims;

import java.util.HashMap;

/**
 * This class maintains the inventory. It contains methods for picking product
 * and restocking product. These methods throw exception when there is invalid
 * quantity or product is busy. This class also contains a method to add a new
 * product to the inventory and a method to print inventory.
 *
 */
public class InventoryManagement implements InventoryManagementSystem {

	private HashMap<String, Product> inventory = new HashMap<String, Product>();

	@Override
	public PickingResult pickProduct(String productId, int amountToPick) {

		if (!inventory.containsKey(productId))
			return null;

		try {
			inventory.get(productId).pick(amountToPick);
			return new PickingResult(inventory.get(productId));
		} catch (InvalidQuantityException e) {
			System.out.println("For '" + productId + "', Please select number between 1 and "
					+ inventory.get(productId).getLevel());
		} catch (LockedProductException e) {
			System.out.println("Product is in use. Try again.");
		}

		return null;

	}

	@Override
	public RestockingResult restockProduct(String productId, int amountToRestock) {
		if (!inventory.containsKey(productId))
			return null;

		try {
			inventory.get(productId).add(amountToRestock);
			return new RestockingResult(inventory.get(productId));
		} catch (InvalidQuantityException e) {
			System.out.println("For '" + productId + "', Please select number greater than 0");
		} catch (LockedProductException e) {
			System.out.println("Product is in use. Try again.");
		}

		return null;

	}

	/**
	 * Adds new Product to the inventory
	 * 
	 * @param location
	 *            The ID of the product to be added
	 * 
	 * @param level
	 *            The quantity of the product to stock
	 */
	public boolean addNewProduct(Product product) {
		if (inventory.containsKey(product.getLocation())) {
			return false;
		}

		// Assuming we can add product with level 0
		if (product.getLevel() < 0) {
			System.out.println("Please provide zero or more level to add new product");
			return false;
		}

		inventory.put(product.getLocation(), product);
		return true;

	}

	/**
	 * Prints the inventory
	 */
	public void printInventory() {
		System.out.println("");
		for (String product_id : inventory.keySet()) {
			Product product = inventory.get(product_id);
			System.out.println("Product: " + product_id + " => Count: " + product.getLevel());
		}
		System.out.println("");
	}

}
