import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderExample {
    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message - shown in both console and file");
        logger.info("Info message - shown in both console and file");
        logger.warn("Warning message - shown in both console and file");
        logger.error("Error message - shown in both console and file");
    }
}
