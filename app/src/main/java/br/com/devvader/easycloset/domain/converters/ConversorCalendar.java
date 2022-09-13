package br.com.devvader.easycloset.domain.converters;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {

    @TypeConverter
    public Long converterDataParaMilesegundosEmLong(Calendar data) {
        return data.getTimeInMillis();
    }

    @TypeConverter
    public Calendar converterMilesegundosEmLongParaData(Long milesegundos) {
        Calendar data = Calendar.getInstance();
        if(milesegundos != null) {
            data.setTimeInMillis(milesegundos);
        }
        return data;
    }
}
