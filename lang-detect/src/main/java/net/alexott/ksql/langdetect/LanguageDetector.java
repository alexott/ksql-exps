package net.alexott.ksql.langdetect;

import com.github.jfasttext.JFastText;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

import java.io.InputStream;

@UdfDescription(name = "langdetect", description = "Language detection using FastText")
public class LanguageDetector {
    private final static String UNKNOWN_LANGUAGE="unk";
    private final static String LABEL_PREFIX="__label__";

    @Udf(description = "perform language detection")
    public String langdetect(final String text) {
        JFastText langModel = LanguageModelHolder.getLangModel();
        if (langModel == null) {
            return null;
        }
        try {
            String label = langModel.predict(text);
            if (label != null && label.startsWith(LABEL_PREFIX)) {
                return label.substring(LABEL_PREFIX.length());
            } else {
                return UNKNOWN_LANGUAGE;
            }
        } catch (Exception ex) {
            // TODO: use Logger
            System.err.println("Exception during language detection: " + ex.getMessage());
            return null;
        }
    }
}
