package alnero.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {
    private static final Map<Long, String> MONTHS = Map.ofEntries(
            Map.entry(1L, "янв"),
            Map.entry(2L, "фев"),
            Map.entry(3L, "мар"),
            Map.entry(4L, "апр"),
            Map.entry(5L, "май"),
            Map.entry(6L, "июн"),
            Map.entry(7L, "июл"),
            Map.entry(8L, "авг"),
            Map.entry(9L, "сен"),
            Map.entry(10L, "окт"),
            Map.entry(11L, "ноя"),
            Map.entry(12L, "дек"));

    @Override
    public LocalDateTime parse(String parse) {
        if (parse.startsWith("сегодня")) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("d ")
                    .appendText(ChronoField.MONTH_OF_YEAR, MONTHS)
                    .appendPattern(" yy")
                    .toFormatter();
            parse = parse.replace("сегодня", LocalDate.now().format(formatter));
        }
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("d ")
                .appendText(ChronoField.MONTH_OF_YEAR, MONTHS)
                .appendPattern(" yy, HH:mm")
                .toFormatter();
        return LocalDateTime.parse(parse, formatter);
    }
}
