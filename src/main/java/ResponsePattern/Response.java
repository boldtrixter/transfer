/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResponsePattern;

/**
 *
 * @author user
 */
public final class Response {

    public static final class IncorrectSenderPhone {

        public static final String MESSAGE = "Неправильный номер телефона отправителя";
        public static final String CODE = "-1";
    }

    public static final class IncorrectReceiverPhone {

        public static final String MESSAGE = "Неправильный номер телефона получателя";
        public static final String CODE = "-1";
    }

    public static final class IncorrectProductName {

        public static final String MESSAGE = "Неправильное название товара";
        public static final String CODE = "-3";
    }

    public static final class IncorrectPrice {

        public static final String MESSAGE = "Неверная цена товара";
        public static final String CODE = "-2";
    }
}
