package helpers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {
    public static String setFormat(String date) {
        String outputFormat = "dd-MM-yyyy";

        // Parse the input string to OffsetDateTime
        DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime dateTime = null;

        try {
            dateTime = OffsetDateTime.parse(date, inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
        String formattedDateTimeString = dateTime.format(outputFormatter);
        
        return formattedDateTimeString;
    }
}
