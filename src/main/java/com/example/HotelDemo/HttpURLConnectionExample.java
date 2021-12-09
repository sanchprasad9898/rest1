package com.example.HotelDemo;


import com.google.gson.Gson;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String Get_All_Restaurant_List = "http://localhost:8084/getAllRestaurantlist";
    private static final String Get_Desired_Restaurant_By_Id = "http://localhost:8084/getDesiredRestaurantById";
    private static final String Create_New_Restaurant = "http://localhost:8084/createNewRestaurant";
    private static final String Update_Restaurant = "http://localhost:8084/updateRestaurant/";
    private static final String Remove_All_Restaurants = "http://localhost:8084/removeAllRestaurant";
    private static final String Remove_Restaurant_By_Id = "http://localhost:8084/removeRestaurantById/";

    private static final String Get_All_Menus = "http://localhost:8084/getAllMenus";
    private static final String Get_Menu_Item_By_Id = "http://localhost:8084/getMenuItemById";
    private static final String Create_Menu_Item = "http://localhost:8084/createMenuItem";
    private static final String Update_Menu_Item = "http://localhost:8084/updateMenuItem";
    private static final String Remove_All_Menu = "http://localhost:8084/removeAllMenu";
    private static final String Remove_Desired_Menu_Item = "http://localhost:8084/removeRestaurantById";


    public static void main(String[] args) throws IOException {

        getRestaurants();
        createRestaurant();
        updateRestaurant();
        deleteRestaurant();

/*
        getMenus();
        createMenu();
        updateMenu();
        deleteMenu();
*/
        }

    private static void getRestaurants() throws IOException {

        URL obj = new URL(Get_All_Restaurant_List);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        System.out.println("GET Response Code :: " + responseCode);


        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    private static void createRestaurant() throws IOException {
        URL obj = new URL(Create_New_Restaurant);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        // con.setRequestProperty("User-Agent", USER_AGENT);


        String createRestaurantString = "{" +
                "\"restaurantId\": 0," +
                "\"restaurantName\": \"Ifa\"," +
                "\"restaurantPhoneNumber\": \"1234567890\"," +
                "\"restaurantType\": \"Veg Ifa\"" +
                "}";

        System.out.println("The create restaurant string is : " + createRestaurantString);
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = createRestaurantString.getBytes();
            os.write(input, 0, input.length);
        }



        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == 200) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

    private static void updateRestaurant() throws IOException {
        URL obj = new URL(Create_New_Restaurant);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        // con.setRequestProperty("User-Agent", USER_AGENT);


        String createRestaurantString = "{" +
                "\"restaurantId\": 0," +
                "\"restaurantName\": \"Vishnu Bhavan\"," +
                "\"restaurantPhoneNumber\": \"23213232321\"," +
                "\"restaurantType\": \"Veg\"" +
                "}";

        System.out.println("The create restaurant string is : " + createRestaurantString);
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = createRestaurantString.getBytes();
            os.write(input, 0, input.length);
        }



        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        StringBuffer response = null;

        if (responseCode == 200) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }

        long currentRestaurantId = 0l;
        Gson gson = new Gson();
        RestaurantPojo restaurant = gson.fromJson(response.toString(), RestaurantPojo.class);
        currentRestaurantId = restaurant.getId();

        System.out.println("The current restaurant id : " + currentRestaurantId);

        obj = new URL(Update_Restaurant+currentRestaurantId);
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");


        String updateRestaurantString = "{" +
                "\"restaurantId\": " + currentRestaurantId + "," +
                "\"restaurantName\": \"Sarvana Bahavan\"," +
                "\"restaurantPhoneNumber\": \"5435454435\"," +
                "\"restaurantType\": \"Veg\"" +
                "}";

        System.out.println("The update restaurant string is : " + updateRestaurantString);
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = updateRestaurantString.getBytes();
            os.write(input, 0, input.length);
        }



        // For POST only - END

        responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);


        if (responseCode == 200) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());

        } else {
            System.out.println("POST request not worked");
        }


    }

    private static void deleteRestaurant() throws IOException {
        URL obj = new URL(Create_New_Restaurant);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        // con.setRequestProperty("User-Agent", USER_AGENT);


        String createRestaurantString = "{" +
                "\"restaurantId\": 0," +
                "\"restaurantName\": \"Balaji Bhavan\"," +
                "\"restaurantPhoneNumber\": \"34324345444\"," +
                "\"restaurantType\": \"Veg\"" +
                "}";

        System.out.println("The create restaurant string is : " + createRestaurantString);
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = createRestaurantString.getBytes();
            os.write(input, 0, input.length);
        }



        // For DELETE only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        StringBuffer response = null;

        if (responseCode == 200) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }

        long currentRestaurantId = 0l;
        Gson gson = new Gson();
        RestaurantPojo restaurant = gson.fromJson(response.toString(), RestaurantPojo.class);
        currentRestaurantId = restaurant.getId();

        System.out.println("The current restaurant id : " + currentRestaurantId);

        obj = new URL(Remove_Restaurant_By_Id+currentRestaurantId);
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);



        // For DELETE only - END

        responseCode = con.getResponseCode();
        System.out.println("DELETE Response Code :: " + responseCode);


        if (responseCode == 200) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());

        } else {
            System.out.println("DELETE request not worked");
        }

    }
}
