package com.example.myapplication.helper;


import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

public class DateFormat {
  public static String getCurrentDate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
    Date date = new Date();

    return dateFormat.format(date);
  }
}
