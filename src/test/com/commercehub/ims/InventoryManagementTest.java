package test.com.commercehub.ims;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.java.com.commercehub.ims.InvalidQuantityException;
import main.java.com.commercehub.ims.InventoryManagement;
import main.java.com.commercehub.ims.LockedProductException;
import main.java.com.commercehub.ims.PickingResult;
import main.java.com.commercehub.ims.Product;
import main.java.com.commercehub.ims.RestockingResult;

public class InventoryManagementTest {

	private InventoryManagement inventory;
	private ByteArrayOutputStream outContent;
	private Product product;

	@Before
	public void setup() {
		inventory = new InventoryManagement();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		product = Mockito.spy(new Product("Tester", 99));
		inventory.addNewProduct(product);

	}

	@Test
	public void testAddNewProduct_success() {
		Product prod = new Product("Test", 10);
		Assert.assertTrue(inventory.addNewProduct(prod));

	}

	@Test
	public void testAddNewProduct_failureProductExists() {
		Product prod = new Product("Tester", 0);
		Assert.assertFalse(inventory.addNewProduct(prod));

	}

	@Test
	public void testAddNewProduct_failureQuantity() {
		Product prod = new Product("Test", -1);
		Assert.assertFalse(inventory.addNewProduct(prod));

	}

	@Test
	public void testPickProduct_noProductFound() {
		Assert.assertNull(inventory.pickProduct("Test", 3));

	}

	@Test
	public void testPickProduct_success() {
		String expectedString = product.getLocation() + " has count " + (product.getLevel() - 5) + " after picking up.";
		PickingResult result = inventory.pickProduct(product.getLocation(), 5);
		Assert.assertEquals(expectedString, result.toString());

	}

	@Test
	public void testPickProduct_invalidQuantityExceptionFound()
			throws LockedProductException, InvalidQuantityException {
		Assert.assertNull(inventory.pickProduct(product.getLocation(), 100));
		Assert.assertEquals(
				"For '" + product.getLocation() + "', Please select number between 1 and " + product.getLevel(),
				outContent.toString().trim());

	}

	@Test
	public void testPickProduct_lockedProductExceptionFound() throws LockedProductException, InvalidQuantityException {
		Mockito.when(product.isLock()).thenReturn(true);
		Assert.assertNull(inventory.pickProduct("Tester", 50));
		Assert.assertEquals("Product is in use. Try again.", outContent.toString().trim());

	}

	@Test
	public void testRestockProduct_noProductFound() {
		Assert.assertNull(inventory.restockProduct("Test", 2));

	}

	@Test
	public void testRestockProduct_success() {
		String expectedString = product.getLocation() + " has count " + (product.getLevel() + 5) + " after restocking.";
		RestockingResult result = inventory.restockProduct(product.getLocation(), 5);
		Assert.assertEquals(expectedString, result.toString());

	}

	@Test
	public void testRestockProduct_invalidQuantityExceptionFound()
			throws LockedProductException, InvalidQuantityException {
		inventory.restockProduct(product.getLocation(), 0);
		Assert.assertEquals("For '" + product.getLocation() + "', Please select number greater than 0",
				outContent.toString().trim());

	}

	@Test
	public void testRestockProduct_lockedProductExceptionFound()
			throws LockedProductException, InvalidQuantityException {
		Mockito.when(product.isLock()).thenReturn(true);
		Assert.assertNull(inventory.restockProduct("Tester", 50));
		Assert.assertEquals("Product is in use. Try again.", outContent.toString().trim());

	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

}
