package test.com.commercehub.ims;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.commercehub.ims.PickingResult;
import main.java.com.commercehub.ims.Product;

public class PickingResultTest {

	@Test
	public void testToString_printsString() {
		Product product = new Product("Test", 5);
		PickingResult pickResult = new PickingResult(product);
		String expectedString = product.getLocation() + " has count " + product.getLevel() + " after picking up.";
		Assert.assertEquals(expectedString, pickResult.toString());

	}

}
