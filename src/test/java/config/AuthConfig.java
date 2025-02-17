package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",// Использует файл, указанный через переменную окружения "env"
        "classpath:local.properties"   // Файл по умолчанию, если "env" не передан или файл не найден
})
public interface AuthConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String baseUrl();

    @Key("userName")
    String userName();

    @Key("password")
    String password();
}