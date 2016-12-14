package main.java.com.commercehub.main;

import main.java.com.commercehub.ims.InventoryManagement;
import main.java.com.commercehub.ims.Product;

public class Main {

	public static void main(String[] args) {

		InventoryManagement warehouse = new InventoryManagement();

		Product product1 = new Product("Red Pen", 10);
		Product product2 = new Product("Blue Pen", 20);
		Product product3 = new Product("Black Pen", 30);
		Product product4 = new Product("Yellow Pen", 40);

		warehouse.addNewProduct(product1);
		warehouse.addNewProduct(product2);
		warehouse.addNewProduct(product3);
		warehouse.addNewProduct(product4);

		System.out.println("Warehouse information before picking/restoring");
		warehouse.printInventory();

		warehouse.pickProduct("Red Pen", 10);
		warehouse.restockProduct("Yellow Pen", 4);
		warehouse.pickProduct("Black Pen", 8);

		System.out.println("Warehouse information after picking/restoring");
		warehouse.printInventory();

	}

}
