package com.johnbryce.app.clr;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.johnbryce.app.beans.Customer;
import com.johnbryce.app.service.AdminService;
import com.johnbryce.app.service.CustomerService;
import com.johnbryce.app.util.PrintUtils;

@Component
@Order(value = 2)
public class CustomerTesting implements CommandLineRunner {

	@Autowired
	private PrintUtils printUtils;

	@Autowired
	private AdminService adminService;

	@Autowired
	private CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {
		printUtils.seperateLines("Customer Methods:");

		Customer c1 = new Customer("Moshe", "Leibovitch", "mosheleib@gmail.com", "moshe123");
		Customer c2 = new Customer("Eliad", "Engelstein", "eliadengel@gmail.com", "eliade452");
		Customer c3 = new Customer("Matan", "Becker", "matanbecker@gmail.com", "matanbeck678");
		Customer c4 = new Customer("Kobi", "Aizler", "kobiA@gmail.com", "aizler123");
		Customer c5 = new Customer("Nadav", "Dorony", "nadav@gmail.con", "nadavdor956");
		List<Customer> custoArrays = Arrays.asList(c1, c2, c3, c4, c5);

		// adding the customer
		for (int i = 0; i < custoArrays.size(); i++) {
			adminService.addCustomer(custoArrays.get(i));
		}
		printUtils.printCustomersWithOutCoupon(adminService.getAllCustomers());

		// customer login - success
		System.out.println(
				"Customer login success?: " + customerService.login("mosheleib@gmail.com", "moshe123"));
		// customer login - fails
		System.out.println(
				"Customer login success?: " + customerService.login("mosheleib23@gmail.com", "moshe135"));
		System.out.println();

		// customer exists - true
		System.out.println("customer exists? " + adminService.isCustomerExists("eliadengel@gmail.com", "eliade452"));
		// customer exists - false
		System.out
				.println("customer exists? " + adminService.isCustomerExists("eliadengel36@gmail.com", "eliade45213"));
		System.out.println();

		// update customer
		c1.setEmail("mosheleib12@gmail.com");
		adminService.updateCustomer(c1);
		System.out.println("After updating customer " + c1.getId() + " :");
		printUtils.printOneCustomerWithOutCoupon(c1);

		// delete customer
				/*adminService.deleteCustomer(c5.getId());
				System.out.println("customers details after deleting customer " + c5.getId() + ":");
				printUtils.printCustomersWithOutCoupon(adminService.getAllCustomers());*/

		// get one customer
		System.out.println("get customer 3:");
		printUtils.printOneCustomerWithOutCoupon((adminService.getOneCustomer(3)));
	}
}
