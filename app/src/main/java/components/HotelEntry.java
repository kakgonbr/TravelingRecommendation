package components;

import java.util.ArrayList;

public class HotelEntry implements Comparable<HotelEntry>{
    // Normal attributes
    private int id;
    private String name;
    private long facilities;
    private int type; // Hotel, Hostel, Capsule Hotel
    private String description;
    private misc.CoordsPair pairLocation;
    private String location;
    private boolean allowView;
    private int rating;

    // Score
    private long scoreOverall;

    // Static
    private static int hotelCount = 0;
    private static ArrayList<Integer> occupiedIDs = new ArrayList<>();

    // ----------------------------------------------------- Constructors --------------------------------------------------------
    public HotelEntry(int _id, String _name, long _fac, int _type, String _desc, misc.CoordsPair _pCoords, String _location, boolean _view, int _rating) throws IllegalArgumentException {
        // System.out.println(occupiedIDs);
        // if (!setId(_id)) throw new IllegalArgumentException();
        // if (!setName(_name)) throw new IllegalArgumentException();
        // if (!setFacilities(_fac)) throw new IllegalArgumentException();
        // if (!setType(_type)) throw new IllegalArgumentException();
        // if (!setDescription(_desc)) throw new IllegalArgumentException();
        // if (!setLocation(_pCoords)) throw new IllegalArgumentException();
        // if (!setLocation(_location)) throw new IllegalArgumentException();
        
        if (   !setId(_id)
            || !setName(_name)
            || !setFacilities(_fac)
            || !setType(_type)
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
        setType(0);
        setDescription("Temporary hotel entry used for creating new instances");
        setLocation("Nowhere");
        setLocation(new misc.CoordsPair(1.d, 1.d));
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

    public boolean setLocation(misc.CoordsPair _pairLocation) {
        return (pairLocation = _pairLocation).getX() > .0d && pairLocation.getY() > .0d;
    }

    public boolean setType(int _type) {
        return (type = _type) >= 0;
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
        System.out.println("Rating: " + (Math.abs(idealHotel.getRating() - getRating()) <= 1 ? 2 - Math.abs(idealHotel.getRating() - getRating()) : 0L));
        System.out.println("Facilities: " + Long.bitCount(idealHotel.getFacilities() & getFacilities()));
        System.out.println("Types: " + Long.bitCount(idealHotel.getType() & getType()));
        System.out.println("View: " + (idealHotel.getView() ^ getView() ? 0L : 1L));

        return (scoreOverall = (Math.abs(idealHotel.getRating() - getRating()) <= 1 ? 2 - Math.abs(idealHotel.getRating() - getRating()) : 0L) // max rating diff of 1, 2 - diff to get score
                        + Long.bitCount(idealHotel.getFacilities() & getFacilities()) // facilities
                        + Long.bitCount(idealHotel.getType() & getType()) // types
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

    public misc.CoordsPair getPairLocation() {
        return pairLocation;
    }

    public int getType() {
        return type;
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
    
}

