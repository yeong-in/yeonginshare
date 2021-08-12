package com.example.calendarviewexample;

import android.widget.TextView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//날짜 바뀔 수 있게 해주는거
public class CalendarUtils {

    public static LocalDate selectedDate;

    public static String formattedDAte(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);

    }

    public static String formattedTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return time.format(formatter);

    }


    public static String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }


    //요일별 이벤트 추가하는 구문을 만들어줘야하는데, main.js에 있어서 가져옴옴
   public static ArrayList<LocalDate> daysInMonthArray(LocalDate date) {

        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = CalendarUtils.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i=1; i<=42; i++){
            if(i<=dayOfWeek || i > daysInMonth+dayOfWeek)

                daysInMonthArray.add(null);

            else
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(),i - dayOfWeek));


        }return daysInMonthArray;

    }
    //현재 날짜를 어레이리스트에 담는거군
    public static ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate) {

        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate current = sundayForDAte(selectedDate);
        LocalDate endDate = current.plusWeeks(1);

        //달의 마지막 날이 될때까지 날짜증가시킴
        while (current.isBefore(endDate))
        {
            days.add(current);
            current = current.plusDays(1);
        }

        return days;
    }

    //일요일
    private static LocalDate sundayForDAte(LocalDate current) {

        //현재 숫자에서 -1 하면 지난주 일요일 날짜가 나오나봄
        LocalDate oneWeekAgo = current.minusWeeks(1);

        while(current.isAfter(oneWeekAgo)){
            if(current.getDayOfWeek() == DayOfWeek.SUNDAY)
                return current;

            current = current.minusDays(1);
        }
        return null;
    }



}
