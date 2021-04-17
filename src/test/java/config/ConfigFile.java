package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigFile {

    public static String getGoogleUsername() {

        return getAuthorization().googleUsername();
    }


    public static String getGooglePassword() {

        return getAuthorization().googlePassword();
    }

    public static String getGooglePasswordNegative() {

        return getAuthorization().googlePasswordNegative();
    }

    public static String getYandexUsername() {

        return getAuthorization().yandexUsername();
    }


    public static String getYandexPassword() {

        return getAuthorization().yandexPassword();
    }

    private static AuthorizationConfig getAuthorization() {
        return ConfigFactory.newInstance().create(
                AuthorizationConfig.class, System.getProperties());
    }

}