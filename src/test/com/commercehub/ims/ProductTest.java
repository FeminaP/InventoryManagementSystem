package test.com.commercehub.ims;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import main.java.com.commercehub.ims.InvalidQuantityException;
import main.java.com.commercehub.ims.LockedProductException;
import main.java.com.commercehub.ims.Product;

public class ProductTest {

	private Product product = new Product("Test", 99);

	@Test(expected = InvalidQuantityException.class)
	public void testAdd_throwsInvalidQuantityException() throws LockedProductException, InvalidQuantityException {
		product.add(-1);
	}

	@Test(expected = LockedProductException.class)
	public void testAdd_throwsLockedProductException() throws LockedProductException, InvalidQuantityException {
		Product prod = Mockito.spy(product);
		Mockito.when(prod.isLock()).thenReturn(true);

		prod.add(1);
	}

	@Test
	public void testAdd_success() throws LockedProductException, InvalidQuantityException {
		int currentQuantity = product.getLevel();
		int addCount = 5;
		product.add(addCount);
		int expectedQuantity = currentQuantity + addCount;
		Assert.assertEquals(expectedQuantity, product.getLevel());

	}

	@Test(expected = InvalidQuantityException.class)
	public void testPick_throwsInvalidQuantityException() throws LockedProductException, InvalidQuantityException {
		product.pick(0);
	}

	@Test(expected = InvalidQuantityException.class)
	public void testPick_overPickingThrowsInvalidQuantityException()
			throws LockedProductException, InvalidQuantityException {
		product.pick(product.getLevel() + 1);
	}

	@Test(expected = LockedProductException.class)
	public void testPick_throwsLockedProductException() throws LockedProductException, InvalidQuantityException {
		Product prod = Mockito.spy(product);
		Mockito.when(prod.isLock()).thenReturn(true);

		prod.pick(3);
	}

	@Test
	public void testPick_success() throws LockedProductException, InvalidQuantityException {
		int currentQuantity = product.getLevel();
		int pickCount = 5;
		product.pick(pickCount);
		int expectedQuantity = currentQuantity - pickCount;
		Assert.assertEquals(expectedQuantity, product.getLevel());

	}
	
	@Test
	public void testPick_successPickTotalQuantity() throws LockedProductException, InvalidQuantityException {
		int currentQuantity = product.getLevel();
		product.pick(currentQuantity);
		Assert.assertEquals(0, product.getLevel());

	}

}