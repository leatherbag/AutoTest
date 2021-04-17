package config;

import org.aeonbits.owner.Config;

    @Config.LoadPolicy(Config.LoadType.MERGE)
    @Config.Sources({
            "system:properties",
            "classpath:config/authorization.properties"
    })
    public interface AuthorizationConfig extends Config {
        @Config.Key("google.username")
        String googleUsername();

        @Config.Key("google.password")
        String googlePassword();

        @Config.Key("google.passwordnegative")
        String googlePasswordNegative();

        @Config.Key("yandex.username")
        String yandexUsername();

        @Config.Key("yandex.password")
        String yandexPassword();

        @Key("web.url")
        String webUrl();
    }
