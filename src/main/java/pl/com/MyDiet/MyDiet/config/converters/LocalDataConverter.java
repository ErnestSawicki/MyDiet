package pl.com.MyDiet.MyDiet.config.converters;

import org.springframework.core.convert.converter.Converter;


import javax.annotation.Nullable;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDataConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert( @Nullable String s) {
        if (s ==  null ||s.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
