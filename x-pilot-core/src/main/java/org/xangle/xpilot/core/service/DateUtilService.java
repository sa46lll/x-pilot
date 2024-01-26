package org.xangle.xpilot.core.service;

import java.time.Duration;
import java.time.Instant;

/**
 * 1초 이상 60초 미만 -> x secs(sec) ago
 * 1분 이상 60분 미만 -> x mins(min) ago
 * 1시간 이상 24시간 미만 -> x hrs(hr) y mins(min) ago
 * 1일 이상 -> x days(day) y hrs(hr) ago
 */
public class DateUtilService {

    private DateUtilService() {
    }

    public static Instant now() {
        return Instant.now();
    }

    public static String getAge(Instant target) {
        Instant now = Instant.now();
        Duration duration = Duration.between(target, now).abs();

        long seconds = duration.getSeconds();
        if (seconds < 60) {
            return format(seconds, "sec");
        } else if (seconds < 3600) {
            return format(seconds / 60, "min");
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            long minutes = (seconds % 3600) / 60;
            return format(hours, "hr", minutes, "min");
        } else {
            long days = seconds / 86400;
            long hours = (seconds % 86400) / 3600;
            return format(days, "day", hours, "hr");
        }
    }

    private static String format(long value, String unit) {
        return value + " " + unit + (value > 1 ? "s" : "") + " ago";
    }

    private static String format(long value1, String unit1, long value2, String unit2) {
        return value1 + " " + unit1 + (value1 > 1 ? "s" : "") + " " +
                value2 + " " + unit2 + (value2 > 1 ? "s" : "") + " ago";
    }
}
