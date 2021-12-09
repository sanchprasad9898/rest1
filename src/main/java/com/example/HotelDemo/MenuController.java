package com.example.HotelDemo;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
public class MenuController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @RequestMapping(value = "/getAllMenus", method = RequestMethod.GET)
    @ResponseBody
    public String getAllMenus() {

        try {
            Iterable<Menu> allMenusIterable = menuRepository.findAll();
            Iterator<Menu> allMenusIterator = allMenusIterable.iterator();

            ArrayList<MenuPojo> allMenusInTheRestuarant = new ArrayList<MenuPojo>();
            boolean notMenuFound = false;

            while (allMenusIterator.hasNext()) {
                notMenuFound = true;
                Menu menu = allMenusIterator.next();
                MenuPojo menuPojo = new MenuPojo();
                menuPojo.setPrice(menu.getPrice());
                menuPojo.setItem_name(menu.getItem_name());
                menuPojo.setQuantity(menu.getQuantity());
                menuPojo.setRestaurantId(menu.getRestaurant().getId());
                allMenusInTheRestuarant.add(menuPojo);
            }

            Gson gson = new Gson();
            String response = "{\"Menus\":" + gson.toJson(allMenusInTheRestuarant) + "}";


            if (notMenuFound == false) {
                response = "No Menus found";
                return response;
            } else {
                System.out.println(response);
                return response;
            }


        } catch (Exception e) {
            System.out.println("Exception occurred during getAllMenus : " + e.getMessage());
        }
        return "ERROR";
    }

    @RequestMapping(value = "/removeAllMenu", method = DELETE)
    @ResponseBody
    public void removeAllMenu() {

        Iterable<Menu> menuRepositoryIterable = menuRepository.findAll();
        Iterator<Menu> menuRepositoryIterator = menuRepositoryIterable.iterator();

        while (menuRepositoryIterator.hasNext()) {
            Menu menu = menuRepositoryIterator.next();

            try {
                menuRepository.delete(menu);
            } catch (Exception e) {
            }
        }
    }

    @RequestMapping(value = "/removeDesiredMenuItem/{id}", method = DELETE)
    @ResponseBody
    public void removeDesiredMenuItem(@PathVariable("id") long id) {

        Iterable<Menu> menuRepositoryIterable = menuRepository.findAll();
        Iterator<Menu> menuRepositoryIterator = menuRepositoryIterable.iterator();

        while (menuRepositoryIterator.hasNext()) {
            Menu menu = menuRepositoryIterator.next();
            if (menu.getId() == id) {
                try {
                    menuRepository.delete(menu);
                } catch (Exception e) {
                }
            }
        }
    }


    @RequestMapping(value = "/getRestaurantMenu/{restaurantId}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<MenuPojo> getRestaurantMenuById(@PathVariable("restaurantId") long restaurantId) {

        Iterable<Menu> menuRepositoryIterable = menuRepository.findAll();
        Iterator<Menu> menuRepositoryIterator = menuRepositoryIterable.iterator();

        ArrayList<MenuPojo> restaurantMenu = new ArrayList<MenuPojo>();

        while (menuRepositoryIterator.hasNext()) {
            Menu menu = menuRepositoryIterator.next();
            if (menu.getRestaurant().getId() == restaurantId) {

                MenuPojo menuPojo = new MenuPojo();
                menuPojo.setItem_name(menu.getItem_name());
                menuPojo.setPrice(menu.getPrice());
                menuPojo.setQuantity(menu.getQuantity());
                menuPojo.setRestaurantId(menu.getRestaurant().getId());
                menuPojo.setMenuId(menu.getId());

                restaurantMenu.add(menuPojo);
            }
        }

        return restaurantMenu;
    }

    //getbymenuid
    @RequestMapping(value = "/getMenuItemById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getMenuItemById(@PathVariable("id") long id) {

        try {
            Iterable<Menu> allMenusIterable = menuRepository.findAll();
            Iterator<Menu> allMenusIterator = allMenusIterable.iterator();

            ArrayList<MenuPojo> allMenusInTheRestuarant = new ArrayList<MenuPojo>();
            boolean notMenuFound = false;

            while (allMenusIterator.hasNext()) {
                notMenuFound = true;

                Menu menu = allMenusIterator.next();
                if (menu.getId() == id) {
                    MenuPojo menuPojo = new MenuPojo();
                    menuPojo.setPrice(menu.getPrice());
                    menuPojo.setItem_name(menu.getItem_name());
                    menuPojo.setQuantity(menu.getQuantity());
                    menuPojo.setRestaurantId(menu.getRestaurant().getId());
                    allMenusInTheRestuarant.add(menuPojo);
                    break;
                }
            }
            Gson gson = new Gson();
            String response = "{\"Menus\":" + gson.toJson(allMenusInTheRestuarant) + "}";


            if (notMenuFound == false) {
                response = "No Menus found";
                return response;
            } else {
                System.out.println(response);
                return response;
            }


        } catch (Exception e) {
            System.out.println("Exception occurred during getAllMenus : " + e.getMessage());
        }
        return "ERROR";
    }

    //Create API
    @PostMapping("/createMenuItem/{restaurantId}")
    public Menu createMenuItem(
            @RequestBody MenuPojo menuPojo, @PathVariable("restaurantId") long restaurantId) {

        Menu menu = new Menu();
        menu.setItem_name(menuPojo.getItem_name());
        menu.setPrice(menuPojo.getPrice());
        menu.setQuantity(menuPojo.getQuantity());

        Iterable<Restaurant> allRestaurantsIterable = restaurantRepository.findAll();
        Iterator<Restaurant> allRestaurantsIterator = allRestaurantsIterable.iterator();


        while (allRestaurantsIterator.hasNext()) {

            Restaurant restaurant = allRestaurantsIterator.next();

            if (restaurant.getId() == restaurantId) {
                menu.setRestaurant(restaurant);
            }
        }
        menuRepository.save(menu);

        Iterable<Menu> allMenuIterable = menuRepository.findAll();
        Iterator<Menu> allMenuIterator = allMenuIterable.iterator();


        while (allMenuIterator.hasNext()) {

            Menu newMenu = allMenuIterator.next();

            if ((newMenu.getRestaurant().getId() == restaurantId) &&
                    (newMenu.getItem_name().compareTo(menuPojo.getItem_name()) == 0) &&
                    (newMenu.getPrice() == menuPojo.getPrice()) &&
                    newMenu.getQuantity() == menuPojo.getQuantity()) {
                return newMenu;
            }
        }
        return null;
    }

    //Update
    @PostMapping("/updateMenuItem/{menuItemId}")
    public String updateMenuItem(
            @RequestBody MenuPojo menuPojo, @PathVariable("menuItemId") long menuItemId) {


        Iterable<Menu> allMenusIterable = menuRepository.findAll();
        Iterator<Menu> allMenusIterator = allMenusIterable.iterator();


        while (allMenusIterator.hasNext()) {

            Menu menu = allMenusIterator.next();

            if (menu.getId() == menuItemId) {
                menu.setItem_name(menuPojo.getItem_name());
                menu.setPrice(menuPojo.getPrice());
                menu.setQuantity(menuPojo.getQuantity());
                menuRepository.save(menu);
                break;
            }
        }
        return "SUCCESS";
    }

}




