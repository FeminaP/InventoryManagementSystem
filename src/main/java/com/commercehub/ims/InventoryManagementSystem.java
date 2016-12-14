package main.java.com.commercehub.ims;

public interface InventoryManagementSystem {

	/**
	 * Deduct 'amountToPick' of the given 'productId' from inventory.
	 * 
	 * @param productId
	 *            The ID of the product to pick
	 * @param amountToPick
	 *            The quantity of the product to pick
	 * @return Product with updated level
	 */
	PickingResult pickProduct(String productId, int amountToPick);

	/**
	 * Add 'amountToRestock' of the given productId to inventory.
	 * 
	 * @param productId
	 *            The ID of the product to restock
	 * @param amountToRestock
	 *            The quantity of the product to restock
	 * @return Product with updated level
	 */
	RestockingResult restockProduct(String productId, int amountToRestock);

}
