package com.example.HotelDemo;

import com.google.gson.Gson;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SpringBootApplication
@EnableSwagger2
public class HoteldemoApplication {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(HoteldemoApplication.class,args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.HotelDemo")).build();
	}


	@Bean
	CommandLineRunner runner() {
		return args -> {
			System.out.println("The List of Restaurants availabe are");
			Restaurant newRestaurant = new Restaurant("A2B","9878676675","Pure south Indian Vegetarian foods");
			restaurantRepository.save(newRestaurant);
			Restaurant newRestaurant1 = new Restaurant("SeaShell","978675644","Chinese and Arabian foods");
			restaurantRepository.save(newRestaurant1);
			System.out.println(newRestaurant.getId());
			System.out.println(newRestaurant.getRestaurantName());
			System.out.println(newRestaurant.getRestaurantType());
			System.out.println(newRestaurant.getRestaurantPhoneNumber());
			System.out.println(newRestaurant1.getId());
			System.out.println(newRestaurant1.getRestaurantName());
			System.out.println(newRestaurant1.getRestaurantType());
			System.out.println(newRestaurant1.getRestaurantPhoneNumber());


			System.out.println("The Menu items with price&quantity:");
			Menu newMenu = new Menu("Idly",60,2,newRestaurant);
			menuRepository.save(newMenu);
			newMenu.setRestaurant(newRestaurant);
			Menu newMenu1 = new Menu("Dosa",70,1,newRestaurant);
			menuRepository.save(newMenu1);
			newMenu1.setRestaurant(newRestaurant);
			Menu newMenu2 = new Menu("Noodles",100,1,newRestaurant1);
			menuRepository.save(newMenu2);
			newMenu2.setRestaurant(newRestaurant1);
			Menu newMenu3 = new Menu("Briyani",200,1,newRestaurant1);
			menuRepository.save(newMenu3);
			newMenu3.setRestaurant(newRestaurant1);
			System.out.println(newMenu.getId());
			System.out.println(newMenu.getItem_name());
			System.out.println(newMenu.getPrice());
			System.out.println(newMenu.getQuantity());
			System.out.println(newMenu1.getId());
			System.out.println(newMenu1.getItem_name());
			System.out.println(newMenu1.getPrice());
			System.out.println(newMenu1.getQuantity());
			System.out.println(newMenu2.getId());
			System.out.println(newMenu2.getItem_name());
			System.out.println(newMenu2.getPrice());
			System.out.println(newMenu2.getQuantity());
			System.out.println(newMenu3.getId());
			System.out.println(newMenu3.getItem_name());
			System.out.println(newMenu3.getPrice());
			System.out.println(newMenu3.getQuantity());

			Customer newCustomer = new Customer("Krishna", 1234567890);
			newCustomer.setRestaurant(newRestaurant);
			customerRepository.save(newCustomer);


		};
	}

}
