package net.alexott.ksql.langdetect;

import com.github.jfasttext.JFastText;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

import java.io.InputStream;

class LanguageModelHolder {
    static JFastText langModel = null;

    static {
        try {
            InputStream is = LanguageModelHolder.class.getResourceAsStream("/lid.176.ftz");
            if (is != null) {
                langModel = new JFastText(is);
            } else {
                System.err.println("Can't load resource from classpath...");
            }
        } catch (Exception ex) {
            // TODO: use Logger
            System.err.println("Error loading language model: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }

    static JFastText getLangModel() {
        return langModel;
    }
}
