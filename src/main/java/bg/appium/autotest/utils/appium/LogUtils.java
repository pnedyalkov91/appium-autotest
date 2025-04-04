package bg.appium.autotest.utils.appium;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final String LOG_DIRECTORY = ".logs";
    private static final File logDirectory = new File(LOG_DIRECTORY);
    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    /**
     * Delete old log files from the log directory.
     */
    protected static void deleteOldLogFiles() {
        List<File> logsArrayList = Stream.of(Objects.requireNonNull(logDirectory.listFiles()))
                .filter(Objects::nonNull)
                .toList();

        logsArrayList.forEach(logFile -> {
            long lastModifiedFile = logFile.lastModified() / (1000 * 60);
            long currentTime = System.currentTimeMillis() / (1000 * 60);
            long timeDifference = currentTime - lastModifiedFile;

            if (timeDifference >  720) {
                if (logFile.delete()) {
                    logger.info("{} deleted successfully.", logFile.getName());
                } else {
                    throw new RuntimeException("Failed to delete old log file: " + logFile.getName());
                }
            }
        });
    }
}
