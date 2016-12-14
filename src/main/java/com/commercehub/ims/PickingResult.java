package main.java.com.commercehub.ims;

public class PickingResult {

	private Product product;

	public PickingResult(Product product) {
		this.product = product;
	}

	/**
	 * @return String product and quantity after picking
	 */
	public String toString() {
		return product.getLocation() + " has count " + product.getLevel() + " after picking up.";
	}

}
