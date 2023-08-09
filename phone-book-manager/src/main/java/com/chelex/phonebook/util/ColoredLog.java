package com.chelex.phonebook.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.chelex.phonebook.constant.AnsiColorCodes.ANSI_GREEN;
import static com.chelex.phonebook.constant.AnsiColorCodes.ANSI_RED;
import static com.chelex.phonebook.constant.AnsiColorCodes.ANSI_RESET;
import static com.chelex.phonebook.constant.AnsiColorCodes.ANSI_YELLOW;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColoredLog {

    public static void info(String message) {
        log.info(ANSI_GREEN + message + ANSI_RESET);
    }

    public static void warn(String message) {
        log.warn(ANSI_YELLOW + message + ANSI_RESET);
    }

    public static void error(String message) {
        log.error(ANSI_RED + message + ANSI_RESET);
    }

    public static void log(String ansiColor, String message) {
        log.error(ansiColor + message + ANSI_RESET);
    }
}
