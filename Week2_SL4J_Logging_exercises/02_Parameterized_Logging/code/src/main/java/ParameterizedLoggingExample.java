import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "john_doe";
        int age = 30;

        logger.info("User {} has logged in", username);
        logger.debug("User {} is {} years old", username, age);
        logger.warn("Disk space is low for user {}", username);
        logger.error("Failed to load profile for user {}", username);
    }
}
