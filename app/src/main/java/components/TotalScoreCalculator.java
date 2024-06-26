package components;

public class TotalScoreCalculator {
    public static long getScore(components.HotelEntry selectedHotel, components.RestaurantEntry selectedRestaurant, int hotelStays, int restaurantVisits, misc.DoublePair startingLoc){
        System.out.println("Distance: " + selectedHotel.getPairLocation().doubleGetDistance(selectedRestaurant.getPairLocation()));
        // - 1 point per km of commune
        // Assuming that the person would primarily stay at the hotel and travel to the restaurant
        // Lower by a certain amount depending on how long the person stays at the hotel
        System.out.println("Subtract: " + (-1 * (long) (((selectedHotel.getPairLocation().doubleGetDistance(selectedRestaurant.getPairLocation()) * restaurantVisits * 2L)) / hotelStays) - (selectedHotel.getPairLocation().doubleGetDistance(startingLoc) > 5.d? 1L : 0L)));
        return -1 * (long) (((selectedHotel.getPairLocation().doubleGetDistance(selectedRestaurant.getPairLocation()) * restaurantVisits * 2L)) / hotelStays) - (selectedHotel.getPairLocation().doubleGetDistance(startingLoc) > 5.d? 1L : 0L);
    }
}
