package com.example.HotelDemo;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;


@RestController
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private MenuRepository menuRepository;


	@RequestMapping(value = "/getAllRestaurantslist", method = RequestMethod.GET)
	@ResponseBody
	public String getAllRestaurantlist() {

		try {
			Iterable<Restaurant> allRestaurantsIterable = restaurantRepository.findAll();
			Iterator<Restaurant> allRestaurantsIterator = allRestaurantsIterable.iterator();

			String response = null;
			boolean recordFound = false;

			List<Restaurant> restaurantArray = new ArrayList<Restaurant>();

			while (allRestaurantsIterator.hasNext()) {
				recordFound = true;
				Restaurant restaurant = allRestaurantsIterator.next();
				restaurantArray.add(restaurant);
			}

			Gson gson = new Gson();
			response = "{\"Restaurants\":" + gson.toJson(restaurantArray) + "}";


			if (recordFound == false) {
				throw new RuntimeException("No restaurants found");
			} else {
				System.out.println(response);
				return response;
			}


		}catch(RuntimeException e){
			if(e.getMessage().compareTo("No restaurants found") == 0)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No restaurants found \n");
		}
		return "Error";
	}

	//getRestaurantById
	@RequestMapping(value = "/getDesiredRestaurantById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Restaurant getDesiredRestaurantById(@PathVariable("id") long id)  {

		try {
			Iterable<Restaurant> allRestaurantsIterable = restaurantRepository.findAll();
			Iterator<Restaurant> allRestaurantsIterator = allRestaurantsIterable.iterator();

			String response = null;
			boolean recordFound = false;


			while (allRestaurantsIterator.hasNext()) {
				Restaurant restaurant = allRestaurantsIterator.next();
				if(restaurant.getId() == id) {
					recordFound = true;
					Gson gson = new Gson();
					return restaurant;
				}
			}


			if (recordFound == false) {
				throw new RuntimeException("No restaurants found");
			} else {
				System.out.println(response);
				return null;
			}


		}catch(RuntimeException e){
			if(e.getMessage().compareTo("No restaurants found") == 0)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No restaurants found \n");
		}
		return null;
	}


	@RequestMapping(value = "/removeAllRestaurant", method = DELETE)
	@ResponseBody
	public void removeAllRestaurant() {

		Iterable<Menu> menuRepositoryIterable = menuRepository.findAll();
		Iterator<Menu> menuRepositoryIterator = menuRepositoryIterable.iterator();

		while (menuRepositoryIterator.hasNext()) {
			Menu menu = menuRepositoryIterator.next();
				try {
					menuRepository.delete(menu);
				} catch (Exception e) {
				}

		}

		Iterable<Restaurant> restaurantRepositoryIterable = restaurantRepository.findAll();
		Iterator<Restaurant> restaurantRepositoryIterator = restaurantRepositoryIterable.iterator();

		while (restaurantRepositoryIterator.hasNext()) {
			Restaurant restaurant = restaurantRepositoryIterator.next();

			try {
				restaurantRepository.delete(restaurant);
			} catch (Exception e) {
			}
		}
	}


	@RequestMapping(value = "/removeRestaurantById/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String removeRestaurantById(@PathVariable("id") long id)  {

		boolean recordFound = false;

		Iterable<Menu> menuRepositoryIterable = menuRepository.findAll();
		Iterator<Menu> menuRepositoryIterator = menuRepositoryIterable.iterator();

		while (menuRepositoryIterator.hasNext()) {
			Menu menu = menuRepositoryIterator.next();
			try {
				if(menu.getRestaurant().getId() == id) {
					menuRepository.delete(menu);
				}
			} catch (Exception e) {
			}

		}

		Iterable<Restaurant> restaurantRepositoryIterable = restaurantRepository.findAll();
		Iterator<Restaurant> restaurantRepositoryIterator = restaurantRepositoryIterable.iterator();

		while (restaurantRepositoryIterator.hasNext()) {
			Restaurant restaurant = restaurantRepositoryIterator.next();

			try {
				if(restaurant.getId() == id) {
					restaurantRepository.delete(restaurant);
					recordFound = true;
					break;
				}
			} catch (Exception e) {
			}
		}

		if(recordFound == false)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found !! \n");
		}
		return "OK";
	}

	//Create API
	@PostMapping("/createNewRestaurant")
	@ResponseBody
	public Restaurant createNewRestaurant(
			@RequestBody Restaurant restaurant) {

		try {

			if (restaurant.getRestaurantName() == null) {
				throw new RuntimeException("Restaurant name is missing !!");
			}

			if (restaurant.getRestaurantName().length() ==0) {
				throw new RuntimeException("Restaurant name is missing !!");
			}

			if (restaurant.getRestaurantPhoneNumber() == null) {
				throw new RuntimeException("Restaurant phone number is missing !!");
			}


			Iterable<Restaurant> allRestaurantsIterable = restaurantRepository.findAll();
			Iterator<Restaurant> allRestaurantsIterator = allRestaurantsIterable.iterator();

			String response = null;
			boolean recordFound = false;


			while (allRestaurantsIterator.hasNext()) {
				Restaurant temp = allRestaurantsIterator.next();
				if(temp.getRestaurantName().compareTo(restaurant.getRestaurantName()) == 0)
				{
					throw new RuntimeException("Restaurant with same name already exists !!");
				}
			}


			Restaurant restaurant2 = new Restaurant();
			restaurant.setRestaurantName(restaurant.getRestaurantName());
			restaurant.setRestaurantType(restaurant.getRestaurantType());
			restaurant.setRestaurantPhoneNumber(restaurant.getRestaurantPhoneNumber());

			restaurantRepository.save(restaurant);

			allRestaurantsIterable = restaurantRepository.findAll();
			allRestaurantsIterator = allRestaurantsIterable.iterator();

			while (allRestaurantsIterator.hasNext()) {
				Restaurant temp = allRestaurantsIterator.next();
				if((temp.getRestaurantName().compareTo(restaurant.getRestaurantName()) == 0) &&
				(temp.getRestaurantPhoneNumber() == restaurant.getRestaurantPhoneNumber()) &&
						(temp.getRestaurantType() == restaurant.getRestaurantType()))
								{
									return temp;
				}
			}


			return null;
		}catch(RuntimeException e){

			if(e.getMessage().compareTo("Restaurant name is missing !!") == 0)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant name is missing !! \n");
			else if(e.getMessage().compareTo("Restaurant phone number is missing !!") == 0)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant phone number is missing !! \n");
			else if(e.getMessage().compareTo("Restaurant with same name already exists !!") == 0){
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant with same name already exists !! \n");
			}

		}
			return null;
	}


//Update

	@PostMapping("/updateRestaurant/{restaurantId}")
	public String updateRestaurant(
			@RequestBody Restaurant restaurantSource, @PathVariable("restaurantId") long restaurantId) {


		Iterable<Restaurant> allRestaurantsIterable = restaurantRepository.findAll();
		Iterator<Restaurant> allRestaurantsIterator = allRestaurantsIterable.iterator();


		while (allRestaurantsIterator.hasNext()) {

			Restaurant restaurant2 = allRestaurantsIterator.next();

			if (restaurant2.getId() == restaurantId) {
				restaurant2.setRestaurantName(restaurantSource.getRestaurantName());
				restaurant2.setRestaurantType(restaurantSource.getRestaurantType());
				restaurant2.setRestaurantPhoneNumber(restaurantSource.getRestaurantPhoneNumber());
				restaurantRepository.save(restaurant2);
				break;
			}


		}
		return "SUCCESS";
	}
}