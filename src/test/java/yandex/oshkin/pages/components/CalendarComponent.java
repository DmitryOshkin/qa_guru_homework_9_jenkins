package yandex.oshkin.pages.components;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {

        // Заполняем дату рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption(year);
//        $(".react-datepicker__day.react-datepicker__day--0" + day
//                + ":not(.react-datepicker__day--outside-month)").click();
        String dayLocator = format(".react-datepicker__day.react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day);
        $(dayLocator).click();

    }
}
