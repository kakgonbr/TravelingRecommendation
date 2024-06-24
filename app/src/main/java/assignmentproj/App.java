package assignmentproj;

public class App {
    public static void main(String[] args) {
        // java.util.ArrayList<components.HotelEntry> hotelList = new java.util.ArrayList<>();

        // misc.ExcelParser.hotelParser(hotelList);
        
        // for (final components.HotelEntry hotel : hotelList)
        //     System.out.println(hotel);

        java.util.ArrayList<components.RestaurantEntry> restaurantList = new java.util.ArrayList<>();

        misc.ExcelParser.restaurantParser(restaurantList);
    }


    public static String getGreeting(){
        return "";
    }
}