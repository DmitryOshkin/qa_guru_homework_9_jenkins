package yandex.oshkin.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import yandex.oshkin.pages.RegistrationPage;

import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String address = faker.address().fullAddress();
    String phoneNumber = faker.number().digits(10);

    public void checkResultForm(String male) {
        registrationPage
                .checkResultsFormHeaderText("Thanks for submitting the form")
                .checkResultsValue("Student Name", firstName + " " + lastName)
                .checkResultsValue("Student Email", userEmail)
                .checkResultsValue("Gender", male)
                .checkResultsValue("Mobile", phoneNumber)
                .checkResultsValue("Date of Birth", "05 June,1988")
                .checkResultsValue("Subjects", "Physics, Maths")
                .checkResultsValue("Hobbies", "Sports, Music")
                .checkResultsValue("Picture", "sketching8.jpg")
                .checkResultsValue("Address", address)
                .checkResultsValue("State and City", "Haryana Panipat");
    }

    @Test
    void fillPracticeFormStepsTest() {

        Allure.label("owner", "OshkinDmitrii");
        Allure.feature("Forms");
        Allure.story("Заполнение Student Registration Form отдельными шагами");
        Allure.label("severity", SeverityLevel.NORMAL.value());
        Allure.link("demoqa", "https://demoqa.com/");


        step("Открываем страницу заполнения регистрационной формы студентов", () -> {
            registrationPage
                    .openPage();
        });
        step("Заполняем имя студента", () -> {
            registrationPage
                    .setFirstName(firstName)
                    .setLastName(lastName);
        });
        step("Заполняем электронный адрес студента", () -> {
            registrationPage
                    .setUserEmail(userEmail);
        });
        step("Выбираем пол студента", () -> {
            registrationPage
                    .selectGender("Male");
        });
        step("Заполняем номер телефона", () -> {
            registrationPage
                    .setPhoneNumber(phoneNumber);
        });
        step("Заполняем дату рождения", () -> {
            registrationPage
                    .setBirthDate("05", "June", "1988");
        });
        step("Вводим предметы", () -> {
            registrationPage
                    .setSubject("Physics")
                    .setSubject("Maths");
        });
        step("Выбираем хобби", () -> {
            registrationPage
                    .selectHobbies("Sports")
                    .selectHobbies("Music");
        });
        step("Прикрепляем картинку", () -> {
            registrationPage
                    .uploadPicture("img/sketching8.jpg");
        });
        step("Заполняем Адрес", () -> {
            registrationPage
                    .setAddress(address);
        });
        step("Выбираем штат и город", () -> {
            registrationPage
                    .selectState("Haryana")
                    .selectCity("Panipat");
        });
        step("Завершаем заполнение формы", () -> {
            registrationPage
                    .clickSubmit();
        });
        step("Проверяем корректность заполнения формы", () -> {
            checkResultForm("Male");
        });
    }


    @Test
    void fillPracticeFormBlockTest() {

        Allure.label("owner", "OshkinDmitrii");
        Allure.feature("Forms");
        Allure.story("Заполнение Student Registration Form блоком>");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("demoqa", "https://demoqa.com/");

        step("Открываем форму регистрации и заполняем данные о студенте", () -> {
            registrationPage
                    .openPage()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .selectGender("Male")
                    .setPhoneNumber(phoneNumber)
                    .setBirthDate("05", "June", "1988")
                    .setSubject("Physics")
                    .setSubject("Maths")
                    .selectHobbies("Sports")
                    .selectHobbies("Music")
                    .uploadPicture("img/sketching8.jpg")
                    .setAddress(address)
                    .selectState("Haryana")
                    .selectCity("Panipat")
                    .clickSubmit();
        });
        step("Проверяем корректность заполнения формы", () -> {
            checkResultForm("Male");
        });
    }

    @Test
    void fillPracticeFormListenerTest() {

        Allure.label("owner", "OshkinDmitrii");
        Allure.feature("Forms");
        Allure.story("Заполнение Student Registration Form поэтапно по полям");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("demoqa", "https://demoqa.com/");

        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .selectGender("Male")
                .setPhoneNumber(phoneNumber)
                .setBirthDate("05", "June", "1988")
                .setSubject("Physics")
                .setSubject("Maths")
                .selectHobbies("Sports")
                .selectHobbies("Music")
                .uploadPicture("img/sketching8.jpg")
                .setAddress(address)
                .selectState("Haryana")
                .selectCity("Panipat")
                .clickSubmit();

        checkResultForm("Female");

    }
}
