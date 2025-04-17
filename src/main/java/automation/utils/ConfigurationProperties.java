package automation.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("Framework.properties")
public class ConfigurationProperties {
    @Value("${browser}")
    private String browser;

    @Value("${email}")
    private String email;

    @Value("${password}")
    private String password;

    @Value("${user}")
    private String username;

    public String getBrowser() {
        return browser;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
