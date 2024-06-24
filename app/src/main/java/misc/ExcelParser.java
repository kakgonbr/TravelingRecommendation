package misc;

import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelParser {
    public static void hotelParser(final java.util.ArrayList<components.HotelEntry> hotelList){
        int rowIndex = 0;

        try (FileInputStream fis = new FileInputStream("Hotel_Mau.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            int start = sheet.getFirstRowNum() + 1;
            int end = sheet.getLastRowNum();
            Row topRow = sheet.getRow(start - 1);


            for (rowIndex = start; rowIndex <= end; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                long lAmenities = 0;
                // For each row, create a new hotel
                components.HotelEntry hotel = new components.HotelEntry();
                misc.Utils.logAppend("Setting attributes for hotel #" + (components.HotelEntry.getCount()), null);
                for (Cell cell : row) {
                    switch (topRow.getCell(cell.getColumnIndex()).getStringCellValue()){
                        case "id": // id
                            misc.Utils.logAppend("\033[33mID: \033[0m" + (int) cell.getNumericCellValue(), null);
                            hotel.setId((int) cell.getNumericCellValue());
                        break;
                        case "name": // name
                            misc.Utils.logAppend("\033[33mName: \033[0m" + cell.getStringCellValue(), null);
                            hotel.setName(cell.getStringCellValue());
                        break;
                        case "facilities": // facilities
                            String[] strFacilities = cell.getStringCellValue().split(",");
                            long lFacilities = 0;
                            for (final String entry : strFacilities) lFacilities |= components.HotelEntry.mFacilities.get(entry);
                            misc.Utils.logAppend("\033[33mFacitilies binary: \033[0m" + Long.toBinaryString(lFacilities), null);
                            hotel.setFacilities(lFacilities);
                        break;
                        case "amenities": // amenities
                            String[] strAmenities = cell.getStringCellValue().split(",");
                            lAmenities = 0;
                            for (final String entry : strAmenities) lAmenities += components.HotelEntry.mTypesAmenities.get(entry);
                            hotel.setTypeAmenities(lAmenities);
                        break;
                        case "star": // star (rating)
                            misc.Utils.logAppend("\033[33mRating (stars): \033[0m" + (int) cell.getNumericCellValue(), null);
                            hotel.setRating((int) cell.getNumericCellValue());
                        break;
                        case "allow_view": // allow view
                            misc.Utils.logAppend("\033[33mAllow view: \033[0m" + (cell.getNumericCellValue() == 1.d ? "Yes" : "No"), null);
                            hotel.setView(cell.getNumericCellValue() == 1.d);
                        break;
                        case "type": // type
                            lAmenities |= components.HotelEntry.mTypesAmenities.get(cell.getStringCellValue());
                            misc.Utils.logAppend("\033[33mType and amenities binary: \033[0m" + Long.toBinaryString(lAmenities), null);
                            hotel.setTypeAmenities(lAmenities);
                        break;
                        case "description": // description
                            misc.Utils.logAppend("\033[33mDescription: \033[0m" + String.format("%.100s", cell.getStringCellValue()), null);
                            hotel.setDescription(cell.getStringCellValue());
                        break;
                        case "address":
                            misc.Utils.logAppend("\033[33mAddress: \033[0m" + String.format("%.100s", cell.getStringCellValue()), null);
                            hotel.setLocation(cell.getStringCellValue());
                        break;
                        case "longitude":
                            misc.DoublePair pair = new misc.DoublePair(cell.getNumericCellValue(), row.getCell(cell.getColumnIndex() + 1).getNumericCellValue()); // current cell and cell right to it.
                            misc.Utils.logAppend("\033[33mCoordinates: \033[0m" + pair, null);
                            hotel.setLocation(pair);
                        break;
                    }
                    
                }
                System.out.println("-".repeat(50));
                hotelList.add(hotel);
                // System.out.println(hotel.getScore(new components.HotelEntry(components.HotelEntry.getCount(), "yes sir", 0b1011111, 0b1, "good hotel", new misc.CoordsPair(100.d, 100.d), "nowhere", true, 3)));
            }
            
        } catch (IOException e) {
            misc.Utils.logAppend(e.getMessage(), misc.Utils.LogFlags.flErr);
        } catch (java.lang.IllegalStateException e1){
            misc.Utils.logAppend("COULD NOT PARSE EXCEL DATA, ERROR AT ROW INDEX: " + rowIndex + "\n" + e1.getMessage(), misc.Utils.LogFlags.flErr);
        } catch (java.lang.NumberFormatException e2){
            misc.Utils.logAppend("COULD NOT PARSE EXCEL DATA, ERROR AT ROW INDEX: " + rowIndex + "\n" + e2.getMessage(), misc.Utils.LogFlags.flErr);
        }
    }

    public static void restaurantParser(java.util.ArrayList<components.RestaurantEntry> restaurantList){
        int rowIndex = 0;

        try (FileInputStream fis = new FileInputStream("Restaurant_Mau.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            int start = sheet.getFirstRowNum() + 1;
            int end = sheet.getLastRowNum();
            Row topRow = sheet.getRow(start - 1);

            components.RestaurantEntry idealRestaurant = new components.RestaurantEntry();
            idealRestaurant.setCapacity(30);
            idealRestaurant.setHoliday(true);
            idealRestaurant.setLastAdmissionTime(java.time.LocalTime.of(20, 0));
            idealRestaurant.setPrep(new misc.LongPair(2, 8));
            idealRestaurant.setPrice(new misc.LongPair(10_000, 100_000));
            idealRestaurant.setRating(3.d);
            idealRestaurant.setDiningGood(0b0101101);
            idealRestaurant.setTypeAmenities(0b11010111);

            for (rowIndex = start; rowIndex <= end; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                long lAmenities = 0; long lDinning = 0;
                // For each row, create a new restaurant
                components.RestaurantEntry restaurant = new components.RestaurantEntry();
                System.out.println("-".repeat(50));
                misc.Utils.logAppend("Setting attributes for restaurant #" + (components.RestaurantEntry.getRestaurantCount()), null);
                for (Cell cell : row) {
                    switch (topRow.getCell(cell.getColumnIndex()).getStringCellValue()){
                        case "id":
                            misc.Utils.logAppend("\033[33mID: \033[0m" + (int) cell.getNumericCellValue(), null);
                            restaurant.setId((int) cell.getNumericCellValue());
                        break;
                        case "name":
                            misc.Utils.logAppend("\033[33mName: \033[0m" + cell.getStringCellValue(), null);
                            restaurant.setName(cell.getStringCellValue());
                        break;
                        case "amenities":
                            String[] strAmenities = cell.getStringCellValue().split(",");
                            for (final String entry : strAmenities) lAmenities |= components.RestaurantEntry.mTypesAmenities.get(entry);
                            restaurant.setTypeAmenities(lAmenities);
                        break;
                        case "max_price":
                            misc.LongPair pairPrice = new misc.LongPair((long) row.getCell(cell.getColumnIndex() + 1).getNumericCellValue(), (long) cell.getNumericCellValue());
                            restaurant.setPrice(pairPrice);
                            misc.Utils.logAppend("\033[33mPrice: \033[0m" + pairPrice, null);
                        break;
                        case "type":
                            String[] strTypes = cell.getStringCellValue().split(",");
                            for (final String entry : strTypes) lAmenities |= components.RestaurantEntry.mTypesAmenities.get(entry);
                            restaurant.setTypeAmenities(lAmenities);
                            misc.Utils.logAppend("\033[33mAmenities and Types binary: \033[0m" + Long.toBinaryString(lAmenities), null);
                        break;
                        case "address":
                            restaurant.setLocation(cell.getStringCellValue());
                            misc.Utils.logAppend("\033[33mAddress: \033[0m" + cell.getStringCellValue(), null);
                        break;
                        case "longitude":
                            misc.DoublePair pairCoords = new misc.DoublePair(cell.getNumericCellValue(), row.getCell(cell.getColumnIndex() + 1).getNumericCellValue());
                            restaurant.setLocation(pairCoords);
                            misc.Utils.logAppend("\033[33mLocation: \033[0m" + pairCoords, null);
                        break;
                        case "capacity":
                            restaurant.setCapacity((int) cell.getNumericCellValue());
                            misc.Utils.logAppend("\033[33mCapacity: \033[0m" + (int) cell.getNumericCellValue(), null);
                        break;
                        case "last_admission_time":
                            restaurant.setLastAdmissionTime(java.time.LocalTime.MIN.plusMinutes((long) (cell.getNumericCellValue() * 24 * 60)));
                            misc.Utils.logAppend("\033[33mLast Admission Time: \033[0m" + java.time.LocalTime.MIN.plusMinutes((long) (cell.getNumericCellValue() * 24 * 60)), null);
                            // java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{1,2}:\\d{1,2}").matcher(cell.getStringCellValue());
                            // if (matcher.find()){
                            //     String[] time = cell.getStringCellValue().substring(matcher.start(), matcher.end()).split(":");
                            //     restaurant.setLastAdmissionTime(java.time.LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]))); // pray that this doesnt throw
                            //     misc.Utils.logAppend("\033[33mLast Admission Time: \033[0m" + Integer.parseInt(time[0]) + ":" + Integer.parseInt(time[1]), null);
                            // }
                        break;
                        case "holiday":
                            restaurant.setHoliday(cell.getStringCellValue().equals("all_holiday"));
                            misc.Utils.logAppend("\033[33mHoliday: \033[0m" + (cell.getStringCellValue().equals("all_holiday")? "Yes" : "No"), null);
                        break;
                        case "dining_times":
                            String[] dinningTimes = cell.getStringCellValue().split(",");
                            for (final String entry : dinningTimes) lDinning |= components.RestaurantEntry.mDiningTimeGoodFor.get(entry.trim());
                            restaurant.setDiningGood(lDinning);
                        break;
                        case "good_for":
                            String[] goodFor = cell.getStringCellValue().split(",");
                            for (String entry : goodFor) {
                                if (entry.codePointAt(0) == 160) entry = entry.substring(1);
                                lDinning |= components.RestaurantEntry.mDiningTimeGoodFor.get(entry);
                            }
                            restaurant.setDiningGood(lDinning);
                        break;
                        case "preparation_time":
                            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{1,2} - \\d{1,2}").matcher(cell.getStringCellValue());
                            if (matcher.find()){
                                String[] time = cell.getStringCellValue().substring(matcher.start(), matcher.end()).split("-");
                                restaurant.setPrep(new misc.LongPair(Integer.parseInt(time[0].trim()), Integer.parseInt(time[1].trim()))); // pray that this doesnt throw
                                misc.Utils.logAppend("\033[33mPrep time: \033[0m" + new misc.LongPair(Integer.parseInt(time[0].trim()), Integer.parseInt(time[1].trim())), null);
                            }
                        break;
                        case "avg_rating":
                            restaurant.setRating(cell.getNumericCellValue());
                            misc.Utils.logAppend("\033[33mRating: \033[0m" + String.format("%.1f", cell.getNumericCellValue()), null);
                        break;
                    }
                    
                }
                restaurantList.add(restaurant);
                restaurant.getScore(idealRestaurant);
            }
            
        } catch (IOException e) {
            misc.Utils.logAppend(e.getMessage(), misc.Utils.LogFlags.flErr);
        } catch (java.lang.IllegalStateException e1){
            misc.Utils.logAppend("COULD NOT PARSE EXCEL DATA, ERROR AT ROW INDEX: " + rowIndex + "\n" + e1.getMessage(), misc.Utils.LogFlags.flErr);
        } catch (java.lang.NumberFormatException e2){
            misc.Utils.logAppend("COULD NOT PARSE EXCEL DATA, ERROR AT ROW INDEX: " + rowIndex + "\n" + e2.getMessage(), misc.Utils.LogFlags.flErr);
        }
    }
}
