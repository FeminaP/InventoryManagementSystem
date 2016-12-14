package main.java.com.commercehub.ims;

public class RestockingResult {

	Product product;

	public RestockingResult(Product product) {
		this.product = product;
	}

	/**
	 * @return String product and quantity after restocking
	 */
	public String toString() {
		return product.getLocation() + " has count " + product.getLevel() + " after restocking.";
	}

}
