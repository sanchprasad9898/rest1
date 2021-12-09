package com.example.HotelDemo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}
