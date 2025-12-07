package org.example.mockexam.configs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    public static Logger log(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz.getCanonicalName());
    }
}
