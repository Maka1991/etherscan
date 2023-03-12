package factories;

import com.github.javafaker.Faker;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class UserFactory {

    private static final Faker faker;

    static {
        faker = new Faker();
    }

    public static User createDefault() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphanumeric(12));

        String email = faker.internet().safeEmailAddress();
        user.setEmail(email);
        user.setConfirmEmail(email);

        String password = UUID.randomUUID().toString();
        user.setPassword(password);
        user.setConfirmPassword(password);

        user.setTermsAndConditions(Boolean.TRUE);
        user.setNewsletter(Boolean.TRUE);
        return user;
    }

}
