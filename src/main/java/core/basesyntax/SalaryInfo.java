package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DATA_SEPARATOR = " ";
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;
    private static final int NAME_INDEX = 1;
    private static final int DATE_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int resultSalary = 0;
            for (String datum : data) {
                String[] datumSplit = datum.split(DATA_SEPARATOR);
                LocalDate date = LocalDate.parse(datumSplit[DATE_INDEX], FORMATTER);
                int workingHours = Integer.parseInt(datumSplit[INDEX_OF_WORKING_HOURS]);
                int incomePerHour = Integer.parseInt(datumSplit[INDEX_OF_INCOME_PER_HOUR]);
                if (datumSplit[NAME_INDEX].equals(name)) {
                    if (date.isAfter(fromDate) && date.isBefore(toDate)
                            || date.isEqual(fromDate) || date.isEqual(toDate)) {
                        resultSalary += workingHours * incomePerHour;
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(resultSalary);
        }
        return builder.toString();
    }
}
