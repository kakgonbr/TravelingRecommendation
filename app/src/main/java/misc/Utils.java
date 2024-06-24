package misc;

public class Utils {
    public enum validations {
        vInt,
        vLong,
        vFloat,
        vDouble,
        vBool
    }

    public enum LogFlags{
        flNorm,
        flWarn,
        flErr
    }

    public static void logAppend(Object message, LogFlags flag){
        System.out.print(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss  ").format(java.time.LocalDateTime.now()));
        System.out.println(((flag == LogFlags.flWarn)? "\033[33mWARN: " : (flag == LogFlags.flErr) ? "\033[31mERR: " : "\033[0mINFO: ") + message + "\033[0m");
    }
    
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
