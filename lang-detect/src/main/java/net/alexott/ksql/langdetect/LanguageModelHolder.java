package net.alexott.ksql.langdetect;

import com.github.jfasttext.JFastText;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

class LanguageModelHolder {
    private static Logger LOGGER = LoggerFactory.getLogger(LanguageModelHolder.class);
    static JFastText langModel = null;

    static {
        try {
            // TODO: use system property to load compressed or full language model...
            InputStream is = LanguageModelHolder.class.getResourceAsStream("/lid.176.ftz");
            if (is != null) {
                langModel = new JFastText(is);
            } else {
                LOGGER.error("Can't load resource from classpath...");
            }
        } catch (Exception ex) {
            LOGGER.error("Error loading language model: " + ex.getMessage(), ex);
        }
    }

    static JFastText getLangModel() {
        return langModel;
    }
}
