package com.example.HotelDemo;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    @ResponseBody
    public String getAllCustomers() {

        try {
            Iterable<Customer> allCustomersIterable = customerRepository.findAll();
            Iterator<Customer> allCustomersIterator = allCustomersIterable.iterator();

            String response = null;
            List<Customer> customersArray = new ArrayList<Customer>();

            while (allCustomersIterator.hasNext()) {
                Customer customer = allCustomersIterator.next();
                customersArray.add(customer);
            }

            Gson gson = new Gson();
            response = "{\"Restaurants\":" + gson.toJson(customersArray) + "}";
            System.out.println(response);
            return response;
        } catch (Exception e) {
        }
        return "Error";
    }


    //Create API
    @PostMapping("/createNewCustomer")
    @ResponseBody
    public Customer createNewCustomer(
            @RequestBody Customer customer) {

        Iterable<Customer> allCustomersIterable = customerRepository.findAll();
        Iterator<Customer> allCustomersIterator = allCustomersIterable.iterator();


        while (allCustomersIterator.hasNext()) {
            Customer temp = allCustomersIterator.next();
            if ((temp.getName().compareTo(customer.getName()) == 0) &&
                    (temp.getPhoneNumber() == customer.getPhoneNumber())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already exists !!! \n");
            }
        }


        try {
            Customer newCustomer = new Customer();
            newCustomer.setName(customer.getName());
            newCustomer.setPhoneNumber(customer.getPhoneNumber());


            Iterable<Restaurant> allRestaurantsIterable = restaurantRepository.findAll();
            Iterator<Restaurant> allRestaurantsIterator = allRestaurantsIterable.iterator();

            while (allRestaurantsIterator.hasNext()) {
                Restaurant restaurant2 = allRestaurantsIterator.next();
                newCustomer.setRestaurant(restaurant2);
                customerRepository.save(newCustomer);
            }


            allCustomersIterable = customerRepository.findAll();
            allCustomersIterator = allCustomersIterable.iterator();

            while (allCustomersIterator.hasNext()) {
                Customer temp = allCustomersIterator.next();
                if ((temp.getName().compareTo(customer.getName()) == 0) &&
                        (temp.getPhoneNumber() == customer.getPhoneNumber())) {
                    return temp;
                }
            }


            return null;
        } catch (RuntimeException e) {

        }
        return null;
    }


    @RequestMapping(value = "/removeCustomerById/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeCustomerById(@PathVariable("id") long id) {
        boolean recordFound = false;

        Iterable<Customer> allCustomersIterable = customerRepository.findAll();
        Iterator<Customer> allCustomersIterator = allCustomersIterable.iterator();

        while (allCustomersIterator.hasNext()) {
            Customer temp = allCustomersIterator.next();
            if (temp.getCustomerId() == id) {
                customerRepository.delete(temp);
                recordFound = true;
            }
        }

        if (recordFound == false) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with the specified id doesnt exists !!! \n");
        }

        return "Customer deleted successfully";
    }


    //getRestaurantById
    @RequestMapping(value = "/getDesiredCustomerById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getDesiredCustomerById(@PathVariable("id") long id) {

        Iterable<Customer> allCustomersIterable = customerRepository.findAll();
        Iterator<Customer> allCustomersIterator = allCustomersIterable.iterator();

        while (allCustomersIterator.hasNext()) {
            Customer temp = allCustomersIterator.next();
            if (temp.getCustomerId() == id) {
                return temp;
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with the specified id doesn't exists !!! \n");

    }

    //getRestaurantById
    @RequestMapping(value = "/getDesiredCustomerByNameAndPhoneNumber/{name}/{phoneNumber}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getDesiredCustomerByNameAndPhoneNumber(@PathVariable("name") String name, @PathVariable("phoneNumber") long phoneNumber) {

        Iterable<Customer> allCustomersIterable = customerRepository.findAll();
        Iterator<Customer> allCustomersIterator = allCustomersIterable.iterator();

        while (allCustomersIterator.hasNext()) {
            Customer temp = allCustomersIterator.next();

            if ((temp.getName().compareTo(name) == 0) &&
                    (temp.getPhoneNumber() == phoneNumber)) {
                return temp;
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with the specified id doesn't exists !!! \n");

    }


    @PostMapping("/updateCustomer/{customerId}")
    public String updateCustomer(
            @RequestBody Customer customerSource, @PathVariable("customerId") long customerId) {

        Iterable<Customer> allCustomersIterable = customerRepository.findAll();
        Iterator<Customer> allCustomersIterator = allCustomersIterable.iterator();

        while (allCustomersIterator.hasNext()) {
            Customer temp = allCustomersIterator.next();

            if (temp.getCustomerId() == customerId) {

                temp.setPhoneNumber(customerSource.getPhoneNumber());
                //temp.setName(customerSource.getName());
                customerRepository.save(temp);

            }
            return "OK";
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with the specified id doesn't exists !!! \n");


    }
}
