/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.message;

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

    public static final class IncorrectCarrierPhone {

        public static final String MESSAGE = "Неправильный номер телефона перевозчика";
        public static final String CODE = "-9";
    }

    public static final class IncorrectDate {

        public static final String MESSAGE = "Неверный формат даты, необходимый формат dd:mm:yyyy hh:mm";
        public static final String CODE = "-10";
    }

    public static final class IncorrectProductName {

        public static final String MESSAGE = "Наименование товара не может быть пустым";
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

    public static final class TokenLifetimeEnd {

        public static final String MESSAGE = "Вы давно не пользовались сервисом, повторите авторизацию";
        public static final String CODE = "-8";
    }

    public static final class IncorrectAcceptanceType {

        public static final String MESSAGE = "Неверный тип подтверждения";
        public static final String CODE = "-10";
    }

    public static final class IncorrectCallbackLink {

        public static final String MESSAGE = "Необходимо указать линк для подтверждения";
        public static final String CODE = "-11";
    }

    public static final class AccessDenied {

        public static final String MESSAGE = "Доступ запрещен";
        public static final String CODE = "-12";
    }
}
