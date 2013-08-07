/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.session.promin;

/**
 *
 * @author user
 */
import com.pb.Chameleon.Session.ManagerSystem.SessionManager;
import com.pb.chameleon.ejb_api.session.LoginException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Владислав Волошин
 */
public class Sessions {

    /**
     * Время жизни сессии, в минутах
     */
    final int sessionTTL = 45;
    /**
     * Адрес сервера Проминя
     */
    final String url; // = "http://10.1.108.22:8071/ChameleonServer"; 
    
//            /**
//         * Тестовый url для сервиса
//         */
//        public static final String TEST_URL = "http://10.1.108.22:8071/ChameleonServer";
//        /**
//         * Боевой url для сервиса
//         */
//        public static final String BATTLE_URL="https://promin.privatbank.ua:8073/ChameleonServer";
    
    
//       public static final String GLOBAL_SERVICE_LOGIN = "UTSM";
//   public static final String GLOBAL_SERVICE_PASSWORD = "GFhJUKIghFgh";
//   public static final String GLOBAL_SERVICE_SYSTEM_AUTORIZATION = "EXCL";
//    
    
    /**
     * Сервисный логин
     */
    final String login; // = "UTSM";
    /**
     * Система авторизации сервисного логина
     */
    final String authSys; // = "EXCL";
    /**
     * Пароль сервисного логина
     */
    final String password; // = "GFhJUKIghFgh";
    /**
     * Текущая сессия Проминя
     */
    String currentSession;
    /**
     * Дата, когда нужно обновить сессию
     */
    Long sessionExpiry;
    /**
     * Блокировка для пересоздания сессии
     */
    Lock sessionCreationLock = new ReentrantLock();

    /**
     * Конструктор
     * @param url Адрес сервера Проминя
     * @param login Сервисный логин
     * @param authSys Система авторизации сервисного логина
     * @param password Пароль сервисного логина
     */
    public Sessions(String url, String login, String authSys, String password) {
        this.url = url;
        this.login = login;
        this.authSys = authSys;
        this.password = password;
        currentSession = null;
        sessionExpiry = System.currentTimeMillis();
    }

    /**
     * Получение сервисной сессии Проминя
     * @return String
     * @throws Exception 
     */
    public String getSession() throws Exception {
        if (!isSessionUsable())
            recreateSession();
        return currentSession;
    }

    /**
     * Проверка не необходимость пересоздания сессии
     * @return boolean
     */
    private boolean isSessionUsable() {
        return sessionExpiry > System.currentTimeMillis();
    }

    /**
     * Пересоздает сессию, если нужно.
     * @throws Exception 
     */
    private void recreateSession() throws Exception {
        try {
            sessionCreationLock.lock();
            if (!isSessionUsable())
                openNewSession();
        } finally {
            sessionCreationLock.unlock();
        }
    }

    /**
     * Открывает новую сессию.
     * @throws Exception 
     */
    private void openNewSession() throws Exception {
        try {
            currentSession = SessionManager.getInstance(url, null, null).createSession(login, authSys, password, "", "");
            Calendar cl = Calendar.getInstance();
            cl.add(Calendar.MINUTE, sessionTTL);
            sessionExpiry = cl.getTimeInMillis();
        } catch (LoginException ex) {
            Logger.getLogger(Sessions.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Configuration error or login locked", ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Sessions.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Exception from Promin server", ex);
        } catch (Exception ex) {
            Logger.getLogger(Sessions.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Exception from Promin server", ex);
        }
    }
}
