package tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.*;

@RunWith(Parameterized.class)
public class OrdersTest extends BaseTest {

        // Поля класса:
        // Kнопка заказа (верхняя или нижняя)
        private final String orderButton;
        // Имя заказчика
        private final String name;
        // Фамилия заказчика
        private final String surname;
        // Адрес доставки
        private final String address;
        // Номер телефона
        private final String phoneNumber;
        // Период аренды из выпадающего меню
        private final String rentalPeriod;
        // Цвет самоката
        private final String colour;
        // Комментарий
        private final String comment;
        // Ожидаемый результат
        private boolean actual;

        public OrdersTest(String orderButton, String name, String surname, String address, String phoneNumber,
                                String rentalPeriod, String colour, String comment)
        {
            this.orderButton = orderButton;
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.rentalPeriod = rentalPeriod;
            this.colour = colour;
            this.comment = comment;
        }

        @Parameterized.Parameters(name = "Заказ через {0}: {1} {2}, {3}, период: {5}, цвет: {6}")
        public static Object[][] getOrderFormData() {
            return new Object[][] {
                    { "верхняя кнопка", "Иван", "Иванов", "Москва", "89271111111", "сутки", "black", "1" },
                    { "нижняя кнопка", "Петр",
                            "Петров",
                            "г. Саранск, ул. Ленина, 6-33",
                            "+79275632548", "семеро суток", "grey",
                            "Предварительно позвонить"
                    },
            };
        }

        // Тест: проверяем весь флоу позитивного сценария заказа самоката
        @Test
        public void checkOrderUpTest()  {
            super.implicitlyWait(3);

            MainPage mainPage = new MainPage(driver);
            mainPage.open();
            mainPage.clickSignInButton();

            mainPage.clickOrderUpButton();

            OrderFormPage orderFormPage = new OrderFormPage(driver);
            orderFormPage.inputAllFields(name, surname, address, phoneNumber);
            orderFormPage.clickNextButton();

            RentPage rentPage = new RentPage(driver);
            rentPage.waitRentPageWillBeLoaded();
            rentPage.fillRequiredFields(rentalPeriod, colour, comment);
            rentPage.clickOrderButton();

            QuestionAboutOrderPage confirmQuestionPage = new QuestionAboutOrderPage(driver);
            confirmQuestionPage.clickConfirmButton();

            SuccessOrderPage successOrderCreationPage = new SuccessOrderPage(driver);
            actual = successOrderCreationPage.isSuccessOrderCreationMessageVisible();
            Assert.assertTrue("Expected: a message is displayed that the order was created successfully ",
                    actual);
        }

    @Test
    public void checkOrderDownTest()  {
        super.implicitlyWait(3);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSignInButton();

        mainPage.clickOrderDownButton();

        OrderFormPage orderFormPage = new OrderFormPage(driver);
        orderFormPage.inputAllFields(name, surname, address, phoneNumber);
        orderFormPage.clickNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.waitRentPageWillBeLoaded();
        rentPage.fillRequiredFields(rentalPeriod, colour, comment);
        rentPage.clickOrderButton();

        QuestionAboutOrderPage confirmQuestionPage = new QuestionAboutOrderPage(driver);
        confirmQuestionPage.clickConfirmButton();

        SuccessOrderPage successOrderCreationPage = new SuccessOrderPage(driver);
        actual = successOrderCreationPage.isSuccessOrderCreationMessageVisible();
        Assert.assertTrue("Expected: a message is displayed that the order was created successfully ",
                actual);
    }



}
