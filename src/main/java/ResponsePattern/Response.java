/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResponsePattern;

/**
 *
 * @author Dmitry
 */
public final class Response {

    public static final class Done {

        public static final String MESSAGE = "OK";
        public static final String CODE = "0";
    }

    public static final class IncorrectPhone {

        public static final String MESSAGE = "Указан неверный номер телефона";
        public static final String CODE = "-1";
    }

    public static final class IncorrectSenderPhone {

        public static final String MESSAGE = "Неправильный номер телефона отправителя";
        public static final String CODE = "-2";
    }

    public static final class IncorrectReceiverPhone {

        public static final String MESSAGE = "Неправильный номер телефона получателя";
        public static final String CODE = "-3";
    }

    public static final class IncorrectProductName {

        public static final String MESSAGE = "Неправильное название товара";
        public static final String CODE = "-4";
    }

    public static final class IncorrectPrice {

        public static final String MESSAGE = "Неверная цена товара";
        public static final String CODE = "-5";
    }

    public static final class IncorrectID {

        public static final String MESSAGE = "Недопустимый идентификатор";
        public static final String CODE = "-6";
    }

    public static final class AuthorizationError {

        public static final String MESSAGE = "Ошибка авторизации";
        public static final String CODE = "-7";
    }
}
