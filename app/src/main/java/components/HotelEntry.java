package components;

public class HotelEntry implements Comparable<HotelEntry>{
    public static final java.util.HashMap<String, Long> mFacilities = new java.util.HashMap<>() {{
        put("air_conditioning", 1L);
        put("airport_shuttle", 2L);
        put("beach", 4L);
        put("bar", 8L);
        put("family_rooms", 16L);
        put("electric_vehicle_charging_station", 32L);
        put("non_smoking_room", 64L);
        put("swimming_pool", 128L);
    }};

    public static final java.util.HashMap<String, Long> mTypesAmenities = new java.util.HashMap<>() {{ // Will be used for both types and amenities
        put("Hotels", 1L);
        put("Hostels", 2L);
        put("Capsule Hotels", 4L);
        put("safe", 8L);
        put("suit_press", 16L);
        put("heating", 32L);
    }};

    // Normal attributes
    private int id;
    private String name;
    private long facilities;
    private long typeAmenities; // Hotel, Hostel, Capsule Hotel
    private String description;
    private misc.DoublePair pairLocation;
    private String location;
    private boolean allowView;
    private int rating;

    // Score
    private long scoreOverall;

    // Static
    private static int hotelCount = 0;
    private static java.util.ArrayList<Integer> occupiedIDs = new java.util.ArrayList<>();

    // ----------------------------------------------------- Constructors --------------------------------------------------------
    public HotelEntry(int _id, String _name, long _fac, int _typeAmenities, String _desc, misc.DoublePair _pCoords, String _location, boolean _view, int _rating) throws IllegalArgumentException {
        if (   !setId(_id)
            || !setName(_name)
            || !setFacilities(_fac)
            || !setTypeAmenities(_typeAmenities)
            || !setDescription(_desc)
            || !setLocation(_location)
            || !setLocation(_pCoords)
            || !setRating(_rating)) throw new IllegalArgumentException();
        setView(_view);
        hotelCount++;
    }
    public HotelEntry() {
        int temp = 0;
        while(setId(temp++));
        setName("Empty name");
        setFacilities(0L);
        setTypeAmenities(0);
        setDescription("Temporary hotel entry used for creating new instances");
        setLocation("Nowhere");
        setLocation(new misc.DoublePair(1.d, 1.d));
        setView(false);
        hotelCount++;
    }
    // -------------------------------------------------- Setters -----------------------------------------
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

    public boolean setDescription(String _description) {
        return !(description = misc.Utils.normalizeName(_description)).isBlank();
    }

    public boolean setFacilities(long _facilities) {
        return (facilities = _facilities) >= 0;
    }

    public boolean setLocation(String _location) {
        return !(location = _location).isBlank();
    }

    public boolean setLocation(misc.DoublePair _pairLocation) {
        return (pairLocation = _pairLocation).getX() > .0d && pairLocation.getY() > .0d;
    }

    public boolean setTypeAmenities(long _type) {
        return (typeAmenities = _type) >= 0;
    }

    public void setView(boolean _view){
        allowView = _view;
    }

    public boolean setRating(int _rating){
        return (rating = _rating) > 0 && rating <= 5;
    }

    // -------------------------------------------------- Getters -----------------------------------------
    public long getScore(){
        return scoreOverall;
    }

    // Important method: calculate score based on the ideal hotel's attributes
    public long getScore(HotelEntry idealHotel){
        // System.out.println("Rating: " + (Math.abs(idealHotel.getRating() - getRating()) <= 1 ? 2 - Math.abs(idealHotel.getRating() - getRating()) : 0L));
        // System.out.println("Facilities: " + Long.bitCount(idealHotel.getFacilities() & getFacilities()));
        // System.out.println("TypeAmenitiess: " + Long.bitCount(idealHotel.getTypeAmenities() & getTypeAmenities()));
        // System.out.println("View: " + (idealHotel.getView() ^ getView() ? 0L : 1L));

        return (scoreOverall = (Math.abs(idealHotel.getRating() - getRating()) <= 1 ? 2 - Math.abs(idealHotel.getRating() - getRating()) : 0L) // max rating diff of 1, 2 - diff to get score
                        + Long.bitCount(idealHotel.getFacilities() & getFacilities()) // facilities
                        + Long.bitCount(idealHotel.getTypeAmenities() & getTypeAmenities()) // types
                        + (idealHotel.getView() ^ getView() ? 0L : 1L) // view, using exclusive or to get match, if match, expression becomes false -> 1
                        );
    }

    public static int getCount(){
        return hotelCount;
    }

    public String getDescription() {
        return description;
    }

    public long getFacilities() {
        return facilities;
    }

    public static int getHotelCount() {
        return hotelCount;
    }

    public int getId() {
        return id;
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

    public long getTypeAmenities() {
        return typeAmenities;
    }

    public boolean getView(){
        return allowView;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public int compareTo(HotelEntry o) {
        return - Long.compare(getScore(), o.getScore());
    }

    @Override
    public String toString(){
        long temp;
        return String.format("\nID: %d"
                                + "\nName: %s"
                                + "\nRating: %d"
                                + "\nLocation: %s"
                                + "\nDescription:\n%s\n"
                                + "\nType: "
                                + (((temp = getTypeAmenities()) & 1L) == 0L ? "" : "Hotel")
                                + ((temp & 2L) == 0L ? "" : "Hostel")
                                + ((temp & 4L) == 0L ? "" : "Capsule Hotel")
                                + "\n\nAmenities: "
                                + ((temp & 8L) == 0L ? "" : "\n - Safe")
                                + ((temp & 16L) == 0L ? "" : "\n - Suit Press")
                                + ((temp & 32L) == 0L ? "" : "\n - Heating")
                                + "\n\nFacilities: "
                                + (((temp = getFacilities()) & 1L) == 0L ? "" : "\n - Air Conditioner")
                                + ((temp & 2L) == 0L ? "" : "\n - Airport Shuttle")
                                + ((temp & 4L) == 0L ? "" : "\n - Beach")
                                + ((temp & 8L) == 0L ? "" : "\n - Bar")
                                + ((temp & 16L) == 0L ? "" : "\n - Family Rooms")
                                + ((temp & 32L) == 0L ? "" : "\n - EV Charging Station")
                                + ((temp & 64L) == 0L ? "" : "\n - Non Smoking Room")
                                + ((temp & 128L) == 0L ? "" : "\n - Swimming Pool") + "\n"
                                , getId(), getName(), getRating(), getLocation(), getDescription());
    }
    
}

