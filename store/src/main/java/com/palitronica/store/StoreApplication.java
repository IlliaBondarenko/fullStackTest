package com.palitronica.store;

import com.palitronica.store.data.entity.Customer;
import com.palitronica.store.data.entity.Item;
import com.palitronica.store.data.repository.ItemRepository;
import com.palitronica.store.data.repository.CustomerRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ItemRepository itemRepository, CustomerRepository repository) {
		String[][] itemData = {
				{ "item1", "12.99" },
				{ "item2", "11.99" },
				{ "item3", "7.99" },
				{ "item4", "8.49" },
				{ "item5", "3.49" },
				{ "item6", "1.99" },
				{ "item7", "21.99" },
				{ "item8", "189.99" },
				{ "item9", "1.99" },
				{ "item10", "0.99" }
		};

		String[][] customerData = {
				{ "cutomer1", "4155 126 Ave SE", "Calgary", "AB", "CA", "T2Z0A1" },
				{ "cutomer2", "910 Spadina Crescent E", "Saskatoon", "SK", "CA", "S7K3H5" },
				{ "cutomer3", "4600 Cambie St", "Vancouver", "BC", "CA", "V5K0A1" },
				{ "cutomer4", "650 Dixon Rd", "Etobicoke", "ON", "CA", "M9W1J1" }
		};

		return args -> {
			Stream.of(itemData).forEach(array -> {
				Item item = new Item();
				item.setitemName(array[0]);
				item.setitemPrice(Float.parseFloat(array[1]));
				itemRepository.save(item);
			});
			itemRepository.findAll().forEach(System.out::println);
			
			Stream.of(customerData).forEach(array -> {
				Customer customer = new Customer();
				customer.setcustomerName(array[0]);
				customer.setcustomerStreet(array[1]);
				customer.setcustomerCity(array[2]);
				customer.setcustomerProvince(array[3]);
				customer.setcustomerCounty(array[4]);
				customer.setcustomerZIP(array[5]);
				repository.save(customer);
			});
			repository.findAll().forEach(System.out::println);
		};
	}

}
