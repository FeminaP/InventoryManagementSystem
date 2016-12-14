package test.com.commercehub.ims;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.commercehub.ims.Product;
import main.java.com.commercehub.ims.RestockingResult;

public class RestockingResultTest {

	@Test
	public void testToString_printsString() {
		Product product = new Product("Test", 5);
		RestockingResult restockResult = new RestockingResult(product);
		String expectedString = product.getLocation() + " has count " + product.getLevel() + " after restocking.";
		Assert.assertEquals(expectedString, restockResult.toString());

	}

}
