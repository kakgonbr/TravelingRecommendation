package assignmentproj;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        java.util.ArrayList<components.HotelEntry> hotelList = new java.util.ArrayList<>();

        misc.ExcelParser.hotelParser(hotelList);

        java.util.ArrayList<components.RestaurantEntry> restaurantList = new java.util.ArrayList<>();

        misc.ExcelParser.restaurantParser(restaurantList);

        for (final components.HotelEntry hotel : hotelList) for (final components.RestaurantEntry restaurant : restaurantList){
            System.out.println("Hotel: " + hotel.getName() + "\nRestaurant: " + restaurant.getName());
            System.out.println("Score: \nHotel:" + hotel.getScore() + "\nRestaurant: " + restaurant.getScore() + "\nTotal: " + (hotel.getScore() + restaurant.getScore() + components.TotalScoreCalculator.getScore(hotel, restaurant, 3, 2, new misc.DoublePair(108.199769d, 16.058261806494d))));
        }

        new guicomponents.FrameMain();
    }


    public static String getGreeting(){
        return "";
    }
}