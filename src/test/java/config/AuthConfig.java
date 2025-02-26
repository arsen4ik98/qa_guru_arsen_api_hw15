package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"   // Файл по умолчанию, если "env" не передан или файл не найден
})
public interface AuthConfig extends Config {

    @Key("userName")
    String userName();

    @Key("password")
    String password();
}