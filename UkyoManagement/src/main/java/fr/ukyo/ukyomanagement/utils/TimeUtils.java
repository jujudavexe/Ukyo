package fr.ukyo.ukyomanagement.utils;

public class TimeUtils {

    public static long parseDuration(String durationString) {
        char unit = durationString.charAt(durationString.length() - 1);
        String valueString = durationString.substring(0, durationString.length() - 1);

        long multiplier = 1000;
        switch (unit) {
            case 'y':
                multiplier *= 365 * 3600 * 30;
                break;
            case 'M':
                multiplier *= 24L * 3600 * 30;
                break;
            case 'h':
                multiplier *= 60 * 60;
                break;
            case 'd':
                multiplier *= 24 * 60 * 60;
                break;
            case 'm':
                multiplier *= 60;
                break;
        }

        long value = Long.parseLong(valueString);


        return value * multiplier;
    }

    public static String formatDuration(long duration) {

        if (duration > 1576800000000L)
            return "PERMANENT";

        long seconds = duration / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long months = days / 30;
        long years = days / 365;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;
        months %= 30;
        years %= 365;

        String formattedDuration = "";
        if (years > 0) {
            formattedDuration += years + " an(s)";
        }
        if (months > 0) {
            formattedDuration += months + " mois";
        }
        if (days > 0) {
            formattedDuration += days + " jour(s) ";
        }
        if (hours > 0) {
            formattedDuration += hours + " heure(s) ";
        }
        if (minutes > 0) {
            formattedDuration += minutes + " minute(s) ";
        }
        if (seconds > 0) {
            formattedDuration += seconds + " seconde(s)";
        }

        return formattedDuration.trim();
    }

}