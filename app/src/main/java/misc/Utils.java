package misc;

public class Utils {
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

    public static java.util.HashMap<String, Integer> mTypes = new java.util.HashMap<>() {{
        put("Hotels", 1);
        put("Hostels", 2);
        put("Capsule Hotels", 4);
    }};

    public enum validations {
        vInt,
        vLong,
        vFloat,
        vDouble,
        vBool
    }

    // public enum eFacilities {
    //     air_con(),
    //     airport_shuttle(),
    //     beach(),
    //     bar(),
    //     family_room(),
    //     ev_charge_station(),
    //     non_smoke_room(),
    //     swim_pool();
        
    //     private long value;
    //     private long nextBit = 1;
    //     private eFacilities() {
    //         value = nextBit;
    //         nextBit *= 2;
    //     }

    //     public long getValue() {
    //         return value;
    //     }
    // }

    // public enum eTypes {
    //     hotel(),
    //     hostel(),
    //     capsule();
        
    //     private int value;
    //     private int nextBit = 1;
    //     private eTypes() {
    //         value = nextBit;
    //         nextBit *= 2;
    //     }

    //     public int getValue() {
    //         return value;
    //     }
    // }
    
    public static String getLine(String message, validations val){
        System.out.print(message);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        switch(val){
            case vInt:
                if (scanner.hasNextInt()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            case vLong:
                if (scanner.hasNextLong()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            case vFloat:
                if (scanner.hasNextFloat()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            case vDouble:
                if (scanner.hasNextDouble()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            case vBool:
                if (scanner.hasNextBoolean()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            default:
                return scanner.nextLine();
        }
    }
    
    public static String getLine(String message){
        System.out.print(message);
        return (new java.util.Scanner(System.in)).nextLine();
    }

    public static String normalizeName(String name){ // name is a copy of a reference to a String object in memory
        name = name.trim(); // Remove leading and trailing whitespaces
        name = name.replaceAll("[^a-zA-Z0-9\\s]", "");
        name = name.replaceAll("\\s+", " "); // Remove redundant spaces
        String[] words = name.split("[\\s]"); // Split by space

        for (int i = 0; i < words.length; i++){
            // Capitalizing the words individually
            // left-inclusive, right-exclusive
            if (!words[i].isBlank())
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        return String.join(" ", words);
    }

    public static String normalizeName(String name, String charSet){ // name is a copy of a reference to a String object in memory
        name = name.trim(); // Remove leading and trailing whitespaces
        name = name.replaceAll("[^" + charSet + "\\s]", "");
        name = name.replaceAll("\\s+", " "); // Remove redundant spaces
        String[] words = name.split("[\\s]"); // Split by space

        for (int i = 0; i < words.length; i++){
            // Capitalizing the words individually
            // left-inclusive, right-exclusive
            if (!words[i].isBlank())
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        return String.join(" ", words);
    }
}
