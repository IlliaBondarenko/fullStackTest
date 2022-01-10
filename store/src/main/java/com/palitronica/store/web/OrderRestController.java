package com.palitronica.store.web;

import com.palitronica.store.business.ItemService;
import com.palitronica.store.business.CustomerService;
import com.palitronica.store.data.entity.Customer;
import com.palitronica.store.data.entity.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.taxjar.Taxjar;
import com.taxjar.exception.TaxjarException;
import com.taxjar.model.rates.RateResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
public class OrderRestController {
    private final CustomerService customerService;
    private final ItemService itemService;
    Taxjar client = new Taxjar("46f0e006fd20f614683e4c10ebb27b0f");

    ObjectMapper oMapper = new ObjectMapper();

    public OrderRestController(CustomerService customerService, ItemService itemService) {
        this.customerService = customerService;
        this.itemService = itemService;
    }

    /**
     * Receives, processes and responds to an order
     * 
     * @param order Order to be processed
     * @return Processed order
     */
    @PostMapping("/order")
    public ResponseEntity<Object> recieveOrder(@RequestBody Map<String, Object> order) {
        try {
            ArrayList<Customer> customerList = customerService.getAllCustomers();
            ArrayList<Item> itemList = itemService.getAllItems();
            Map<String, Integer> itemMap = oMapper.convertValue(order.get("items"), Map.class);
            int customerId = (int) order.get("customerId");

            // get customers name
            String customerName = "";
            for (Customer customer : customerList) {
                if (customer.getcustomerId() == customerId) {
                    customerName = customer.getcustomerName();
                }
            }

            // get combined tax rate for the order based on customers address
            RateResponse res = new RateResponse();
            try {
                Map<String, String> params = new HashMap<>();
                params.put("state", customerList.get(customerId).getcustomerProvince());
                params.put("country", customerList.get(customerId).getcustomerCounty());
                res = client.ratesForLocation(customerList.get(customerId).getcustomerZIP(), params);
            } catch (TaxjarException e) {
                e.printStackTrace();
            }
            float taxRate = res.rate.getCombinedRate();

            // calculate the total price per item
            ArrayList<Float> pricePerItem = new ArrayList<Float>();
            itemMap.forEach((k, v) -> {
                for (Item item : itemList) {
                    if (item.getitemId() == Long.parseLong(k)) {
                        pricePerItem.add(v * item.getitemPrice());
                    }
                }
            });

            // calculate the total combined price without taxes
            float combinedPriceNoTax = 0f;
            for (Float val : pricePerItem) {
                combinedPriceNoTax += val;
            }

            // calculate the tax component
            float totalTax = combinedPriceNoTax * taxRate;

            // calculate the total combined price with taxes
            float totalCombinedPrice = combinedPriceNoTax + totalTax;

            // String response = customerName + ";" + pricePerItem + ";" + totalTax + ";"
            //         + totalCombinedPrice;
            Map<String,Object> response = new HashMap<String,Object>();
            response.put("customerName", customerName);
            response.put("pricePerItem", pricePerItem);
            response.put("totalTax", totalTax);
            response.put("totalCombinedPrice", totalCombinedPrice);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Returns a list of all items.
     * 
     * @return a list of all items.
     */
    @GetMapping("/items")
    public ResponseEntity<ArrayList<Item>> getAllItems() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    /**
     * Returns a list of all customers.
     * 
     * @return a list of all customers.
     */
    @GetMapping("/customers")
    public ResponseEntity<ArrayList<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

}
