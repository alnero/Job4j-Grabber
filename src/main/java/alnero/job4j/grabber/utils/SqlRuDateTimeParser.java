package alnero.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {
    /** Formatter months mapper. */
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
        DateTimeFormatter todayYesterdayFormatter = new DateTimeFormatterBuilder()
                .appendPattern("d ")
                .appendText(ChronoField.MONTH_OF_YEAR, MONTHS)
                .appendPattern(" yy")
                .toFormatter();
        if (parse.startsWith("сегодня")) {
            parse = parse.replace("сегодня", LocalDate.now().format(todayYesterdayFormatter));
        }
        if (parse.startsWith("вчера")) {
            parse = parse.replace("вчера", LocalDate.now().minusDays(1).format(todayYesterdayFormatter));
        }
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("d ")
                .appendText(ChronoField.MONTH_OF_YEAR, MONTHS)
                .appendPattern(" yy, HH:mm")
                .toFormatter();
        return LocalDateTime.parse(parse, dateTimeFormatter);
    }
}
