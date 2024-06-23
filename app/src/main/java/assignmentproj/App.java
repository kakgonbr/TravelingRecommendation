package assignmentproj;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        // System.out.println(System.getProperty("user.dir"));
        int rowIndex = 0;
        java.util.ArrayList<components.HotelEntry> hotelList = new java.util.ArrayList<>();


        try (FileInputStream fis = new FileInputStream("Hotel_Mau.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            int start = sheet.getFirstRowNum() + 1;
            int end = sheet.getLastRowNum();
            Row topRow = sheet.getRow(start - 1);


            for (rowIndex = start; rowIndex <= end; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                // For each row, create a new hotel
                components.HotelEntry hotel = new components.HotelEntry();
                logAppend("Setting attributes for hotel #" + (components.HotelEntry.getCount()), null);
                for (Cell cell : row) {
                    switch (topRow.getCell(cell.getColumnIndex()).getStringCellValue()){
                        case "id": // id
                            logAppend("\033[33mID: \033[0m" + (int) cell.getNumericCellValue(), null);
                            hotel.setId((int) cell.getNumericCellValue());
                        break;
                        case "name": // name
                            logAppend("\033[33mName: \033[0m" + cell.getStringCellValue(), null);
                            hotel.setName(cell.getStringCellValue());
                        break;
                        case "facilities": // facilities
                            String[] strFacilities = cell.getStringCellValue().split(",");
                            long lFacilities = 0;
                            for (final String word : strFacilities) lFacilities += misc.Utils.mFacilities.get(word);
                            logAppend("\033[33mFacitilies binary: \033[0m" + Long.toBinaryString(lFacilities), null);
                            hotel.setFacilities(lFacilities);
                        break;
                        case "star": // star (rating)
                            logAppend("\033[33mRating (stars): \033[0m" + (int) cell.getNumericCellValue(), null);
                            hotel.setRating((int) cell.getNumericCellValue());
                        break;
                        case "allow_view": // allow view
                            logAppend("\033[33mAllow view: \033[0m" + (cell.getNumericCellValue() == 1.d ? "Yes" : "No"), null);
                            hotel.setView(cell.getNumericCellValue() == 1.d);
                        break;
                        case "type": // type
                            int iType = misc.Utils.mTypes.get(cell.getStringCellValue());
                            logAppend("\033[33mFacitilies binary: \033[0m" + Integer.toBinaryString(iType), null);
                            hotel.setType(iType);
                        break;
                        case "description": // description
                            logAppend("\033[33mDescription: \033[0m" + String.format("%.100s", cell.getStringCellValue()), null);
                            hotel.setDescription(cell.getStringCellValue());
                        break;
                        case "address":
                            logAppend("\033[33mAddress: \033[0m" + String.format("%.100s", cell.getStringCellValue()), null);
                            hotel.setLocation(cell.getStringCellValue());
                        break;
                        case "longitude":
                            misc.CoordsPair pair = new misc.CoordsPair(cell.getNumericCellValue(), row.getCell(cell.getColumnIndex() + 1).getNumericCellValue()); // current cell and cell right to it.
                            logAppend("\033[33mCoordinates: \033[0m" + pair, null);
                            hotel.setLocation(pair);
                        break;
                    }
                    
                }
                System.out.println("-".repeat(50));
                hotelList.add(hotel);
                System.out.println(hotel.getScore(new components.HotelEntry(components.HotelEntry.getCount(), "yes sir", 0b1011111, 0b1, "good hotel", new misc.CoordsPair(100.d, 100.d), "nowhere", true, 3)));
            }
            
        } catch (IOException e) {
            logAppend(e.getMessage(), LogFlags.flErr);
        } catch (java.lang.IllegalStateException e1){
            logAppend("COULD NOT PARSE EXCEL DATA, ERROR AT ROW INDEX: " + rowIndex + "\n" + e1.getMessage(), LogFlags.flErr);
        } catch (java.lang.NumberFormatException e2){
            logAppend("COULD NOT PARSE EXCEL DATA, ERROR AT ROW INDEX: " + rowIndex + "\n" + e2.getMessage(), LogFlags.flErr);
        }

        // for (final components.HotelEntry hotel : hotelList) ;
    }

    private enum LogFlags{
        flNorm,
        flWarn,
        flErr
    }

    public static void logAppend(Object message, LogFlags flag){
        System.out.print(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss  ").format(java.time.LocalDateTime.now()));
        System.out.println(((flag == LogFlags.flWarn)? "\033[33mWARN: " : (flag == LogFlags.flErr) ? "\033[31mERR: " : "\033[0mINFO: ") + message + "\033[0m");
    }

    public static String getGreeting(){
        return "";
    }
}