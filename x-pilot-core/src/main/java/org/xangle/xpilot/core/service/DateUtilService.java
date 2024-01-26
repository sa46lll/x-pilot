package org.xangle.xpilot.core.service;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 1초 이상 60초 미만 -> x secs ago
 * 1분 이상 60분 미만 -> x mins ago
 * 1시간 이상 24시간 미만 -> x hrs y mins ago
 * 1일 이상 -> x days y hrs ago
 */
public class DateUtilService {

    private DateUtilService() {
    }

    public static String getAge(LocalDateTime target) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(target, now);
        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + " secs ago";
        } else if (seconds < 60 * 60) {
            long minutes = seconds / 60;
            return minutes + " mins ago";
        } else if (seconds < 60 * 60 * 24) {
            long hours = seconds / (60 * 60);
            long remainingMinutes = (seconds % (60 * 60)) / 60;
            return hours + " hrs " + remainingMinutes + " mins ago";
        } else {
            long days = seconds / (60 * 60 * 24);
            long remainingHours = (seconds % (60 * 60 * 24)) / (60 * 60);
            return days + " days " + remainingHours + " hrs ago";
        }
    }
}
