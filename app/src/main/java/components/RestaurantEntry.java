package components;

public class RestaurantEntry implements Comparable<RestaurantEntry>{
    // Maps
    public static final java.util.HashMap<String, Long> mDiningTimeGoodFor = new java.util.HashMap<>() {{
        // Dining times
        put("Buoi sang", 1L);
        put("Buoi trua", 2L);
        put("Buoi xe", 4L);
        put("Buoi toi", 8L);
        // Good for
        put("An vat", 16L);
        put("Hen ho", 32L);
        put("Hop nhom", 64L);
        put("Thu gian", 128L);
        put("Ngam canh", 256L);
        put("An Fastfood", 512L);
        put("Dai tiec", 1024L);
        put("To chuc tiec cuoi", 2048L);
        put("To chuc hoi thao", 4096L);
        put("Takeaway - Mang ve", 8192L);
    }};

    public static final java.util.HashMap<String, Long> mTypesAmenities = new java.util.HashMap<>() {{ // Will be used for both types and amenities
        // Amenities
        put("delivery_services", 1L);
        put("takeaway_services", 2L);
        put("free_bike_park", 4L);
        put("outdoor_seats", 8L);
        // Types
        put("bakery", 16L);
        put("foodcourt", 32L);
        put("restaurant", 64L);
        put("coffee_dessert", 128L);
        put("sight_landmark", 256L);
        put("online_shopping", 512L);
        put("street_food", 1024L);
        put("shop_stores", 2048L);
        put("wedding_convention", 4096L);
    }};

    // Normal attributes
    private int id;
    private String name;
    private long typeAmenities;
    private misc.LongPair price;
    private String location;
    private misc.DoublePair pairLocation;
    private java.time.LocalTime lastAdmisionTime; // Time in date
    private int capacity;
    private boolean holiday; // available on holiday or not
    private double rating;
    private long diningTimeGoodFor; // Used for dinning_time and good_for
    private misc.LongPair prepTime;
    private long scoreOverall;

    // Static
    private static int restaurantCount = 0;
    private static java.util.ArrayList<Integer> occupiedIDs = new java.util.ArrayList<>();

    // -------------------------------------------- Constructors ---------------------------------------------
    public RestaurantEntry(int _id, String _name, long _typeAmenities, misc.LongPair _price, String _location, misc.DoublePair pairCoords, boolean _holiday, java.time.LocalTime _lastadm, int _cap, double _rating, long _dinningGoodFor, misc.LongPair _prep) throws IllegalArgumentException{
        if (!setId(_id)
            || !setName(_name)
            || !setTypeAmenities(_typeAmenities)
            || !setPrice(_price)
            || !setLocation(_location)
            || !setLocation(pairCoords)
            || !setCapacity(_cap)
            || !setRating(_rating)
            || !setDiningGood(_dinningGoodFor)
            || !setPrep(_prep)
            ) throw new IllegalArgumentException();
        setHoliday(_holiday);
        setLastAdmissionTime(_lastadm);
        restaurantCount++;
    }

    public RestaurantEntry(){
        int temp = 0;
        while (!setId(temp++));
        setName("Empty name");
        setTypeAmenities(0);
        setPrice(new misc.LongPair(1, 1));
        setLocation("Nowhere");
        setLocation(new misc.DoublePair(1.d, 1.d));
        setCapacity(1);
        setRating(1.d);
        setDiningGood(0);
        setPrep(new misc.LongPair(1, 2));
        setHoliday(false);
        setLastAdmissionTime(java.time.LocalTime.of(0, 0));

        restaurantCount++;
    }


    // ------------------------------------------- Setters --------------------------------------
    public boolean setId(int _id) {
        if (_id < 1) return false;
        if (_id == id) return true;
        if (occupiedIDs.contains(_id)) return false;
        
        occupiedIDs.remove(Integer.valueOf(_id));
        id = _id;
        occupiedIDs.add(_id);
        return true;
    }

    public boolean setName(String _name) {
        return !(name = misc.Utils.normalizeName(_name)).isBlank();
    }

    public boolean setLocation(String _location) {
        return !(location = _location).isBlank();
    }

    public boolean setLocation(misc.DoublePair _pairLocation) {
        return (pairLocation = _pairLocation).getX() > .0d && pairLocation.getY() > .0d;
    }

    public boolean setPrice(misc.LongPair _pricePair){
        return (price = _pricePair).getX() > 0 && price.getY() > price.getX();
    }

    public boolean setTypeAmenities(long _type) {
        return (typeAmenities = _type) >= 0;
    }

    public boolean setRating(double _rating){
        return (rating = _rating) > .0d && rating <= 5.d;
    }

    public void setLastAdmissionTime(java.time.LocalTime _time){
        lastAdmisionTime = _time;
    }

    public boolean setCapacity(int _cap){
        return (capacity = _cap) > 0;
    }

    public void setHoliday(boolean _holiday){
        holiday = _holiday; 
    }

    public boolean setDiningGood(long _dining){
        return (diningTimeGoodFor = _dining) >= 0;
    }

    public boolean setPrep(misc.LongPair _prep){
        return (prepTime = _prep).getX() > 0 && prepTime.getY() > prepTime.getX();
    }

    // -------------------------------------------------- Getters -----------------------------------------
    public long getScore(){
        return scoreOverall;
    }

    public long getScore(RestaurantEntry idealRestaurant){
        System.out.println("Score:\nType and Amenities: " + Long.bitCount(typeAmenities & idealRestaurant.getTypeAmenities()));
        System.out.printf("Price: %d, %d\n", (idealRestaurant.getPrice().getX() >= getPrice().getX()? 1 : 0), (idealRestaurant.getPrice().getY() <= getPrice().getY()? 1 : 0));
        System.out.println("Last Admission Time: " + (idealRestaurant.getLastAdmisionTime().toSecondOfDay() < getLastAdmisionTime().toSecondOfDay()? 1 : 0));
        System.out.println("Capacity: " + (idealRestaurant.getCapacity() <= getCapacity()? 1 : 0));
        System.out.println("Holiday: " + (idealRestaurant.getHoliday() ^ getHoliday()? 0 : 1));
        System.out.println("Rating: " + (Math.abs(idealRestaurant.getRating() - getRating()) < 2.d ? (int)Math.ceil(2.d - Math.abs(idealRestaurant.getRating() - getRating())) : 0));
        System.out.printf("Prep time: %d, %d\n", (idealRestaurant.getPrepTime().getX() >= getPrepTime().getX()? 1 : 0), (idealRestaurant.getPrepTime().getY() <= getPrepTime().getY()? 1 : 0));

        return (scoreOverall = Long.bitCount(typeAmenities & idealRestaurant.getTypeAmenities())
            + (idealRestaurant.getPrice().getX() >= getPrice().getX()? 1 : 0) // Min price of ideal > min price of current
            + (idealRestaurant.getPrice().getY() <= getPrice().getY()? 1 : 0)
            + (idealRestaurant.getLastAdmisionTime().toSecondOfDay() < getLastAdmisionTime().toSecondOfDay()? 1 : 0)
            + (idealRestaurant.getCapacity() <= getCapacity()? 1 : 0)
            + (idealRestaurant.getHoliday() ^ getHoliday()? 0 : 1) // Xor to get match, if match -> false -> 1
            + (Math.abs(idealRestaurant.getRating() - getRating()) < 2.d ? (int)Math.ceil(2.d - Math.abs(idealRestaurant.getRating() - getRating())) : 0)
            + Long.bitCount(diningTimeGoodFor & idealRestaurant.getDiningTimeGoodFor())
            + (idealRestaurant.getPrepTime().getX() >= getPrepTime().getX()? 1 : 0)
            + (idealRestaurant.getPrepTime().getY() <= getPrepTime().getY()? 1 : 0)
        );
    }

    public int getCapacity() {
        return capacity;
    }

    public long getDiningTimeGoodFor() {
        return diningTimeGoodFor;
    }
    
    public int getId() {
        return id;
    }

    public java.time.LocalTime getLastAdmisionTime() {
        return lastAdmisionTime;
    }
    
    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public misc.DoublePair getPairLocation() {
        return pairLocation;
    }

    public misc.LongPair getPrepTime() {
        return prepTime;
    }

    public misc.LongPair getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public static int getRestaurantCount() {
        return restaurantCount;
    }

    public long getTypeAmenities() {
        return typeAmenities;
    }

    public boolean getHoliday(){
        return holiday;
    }


    @Override
    public int compareTo(RestaurantEntry o) {
        return - Long.compare(getScore(), o.getScore());
    }
    @Override
    public String toString(){
        long temp;
        return String.format("\nID: %d"
                                    + "\nName: %s"
                                    + "\nrating: %.2f"
                                    + "\nPrice: %d - %d"
                                    + "\nLocation: %s"
                                    + "\nCapacity: %d people"
                                    + "\nPreparation Time: %d - %d minutes"
                                    + "\nDining Times:"
                                    + (((temp = getDiningTimeGoodFor()) & 1L) == 0L ? "" : "\n - Morning")
                                    + ((temp & 2L) == 0L ? "" : "\n - Noon")
                                    + ((temp & 4L) == 0L ? "" : "\n - Afternoon")
                                    + ((temp & 8L) == 0L ? "" : "\n - Evening")                                    
                                    + "\nAmenities: "
                                    + (((temp = getTypeAmenities()) & 1L) == 0 ? "" : "\n - Delivery" )
                                    + ((temp & 2L) == 0L ? "" : "\n - Takeaway" )
                                    + ((temp & 4L) == 0L ? "" : "\n - Free Bike Park")
                                    + ((temp & 2L) == 0L ? "" : "\n - Outdoor Seats" )
                                    + "\nTypes: "
                                    + ((temp & 16L) == 0L ? "" : "\n - Bakery")
                                    + ((temp & 32L) == 0L ? "" : "\n - Foodcourt")
                                    + ((temp & 64L) == 0L ? "" : "\n - Restaurant")
                                    + ((temp & 128L) == 0L ? "" : "\n - Coffee - Dessert")
                                    + ((temp & 256L) == 0L ? "" : "\n - Sight - Landmark")
                                    + ((temp & 512L) == 0L ? "" : "\n - Online Shopping")
                                    + ((temp & 1024L) == 0L ? "" : "\n - Street Food")
                                    + ((temp & 2048L) == 0L ? "" : "\n - Shop - Store")
                                    + ((temp & 4096L) == 0L ? "" : "\n - Wedding Convention") + "\n"
                                    , getId(), getName(), getRating(), getPrice().getX(), getPrice().getY(), getLocation(),
                                    getCapacity(),
                                    getPrepTime().getX(),
                                    getPrepTime().getY());
    }
}
