package com.hack.art.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by ROLO on 15.04.2016.
 */
@Component
public class LangUtil {

    public String property;

    private ResourceBundle bundle;

    public String getProperty(String propertyName) {
        return bundle.getString(propertyName);
    }

    public String getProperty(String propertyName, Object... params) {
        return MessageFormat.format(bundle.getString(propertyName), params);
    }

    public LangUtil() {
        Locale locale = LocaleContextHolder.getLocale();
        bundle = ResourceBundle.getBundle("stApp", locale);
    }
}
