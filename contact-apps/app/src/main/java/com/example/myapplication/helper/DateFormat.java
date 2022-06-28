package com.example.myapplication.helper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormat {
  public static String getCurrentDate(String currentDateString, String targetTimeZone) {
    Instant instant = Instant.parse(currentDateString);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy | HH:mm")
      .withZone(ZoneId.of(targetTimeZone));
    return formatter.format(instant);
  }
}
