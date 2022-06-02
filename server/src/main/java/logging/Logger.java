package logging;


import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final String gray = "\u001B[90m";
    private static final String red = "\u001B[31m";
    private static final String green = "\u001B[32m";
    private static final String yellow = "\u001B[33m";
    private static final String blue = "\u001B[34m";
    private static final String magenta = "\u001B[35m";
    private static final String cyan = "\u001B[36m";

    private static final String white = "\u001B[37m";

    private static final String reset = "\u001B[0m";
    private static final List<LogListener> listeners = new ArrayList<>();

    public static void log(LogLevel level, Exception exception, String message) {
        StringBuilder builder = new StringBuilder();

        // Get calling stack trace
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // Get the index of the first stack trace element that is not part of the logging framework
        int index;
        for (index = 0; index < stackTrace.length; index++) {
            if (!stackTrace[index].getClassName().startsWith("logging") && !stackTrace[index].getClassName().startsWith("java.lang")) {
                break;
            }
        }

        // [FATAL]
        builder.append(gray);
        builder.append("[");
        builder.append(getColorCode(level));
        builder.append(String.format("%5s", level));
        builder.append(gray);
        builder.append("] ");

        // [Class.Method:line]
        builder.append(gray);
        builder.append("[");
        builder.append(buildStackTraceElement(stackTrace[index]));
        builder.append(gray);
        builder.append("] ");

        // Message
        builder.append(getColorCode(level));
        builder.append(message);

        // Exception
        if (exception != null) {
            builder.append("\n");
            builder.append("         ");
            builder.append(red);
            builder.append(exception.getClass().getSimpleName());
            builder.append(gray);
            builder.append(": ");
            builder.append(getColorCode(level));
            builder.append(exception.getMessage());

            StackTraceElement[] trace = exception.getStackTrace();

            int stackCounter = 0;
            for (StackTraceElement stack : trace) {
                if (stack.getClassName().startsWith("logging") || stack.getClassName().startsWith("java.lang")) {
                    continue;
                }

                stackCounter++;

                builder.append("\n");
                builder.append(gray);
                builder.append(gray);
                builder.append("         ");
                builder.append(stackCounter);
                builder.append(": ");
                builder.append(buildStackTraceElement(stack));
                builder.append(gray);
                builder.append(":");
                builder.append(blue);
                builder.append(stack.getLineNumber());
            }
        }

        builder.append(reset);

        System.out.println(builder);

        for (LogListener listener : listeners) {
            listener.onLog(level, exception, message);
        }
    }

    public static void log(LogLevel level, String message) {
        log(level, null, message);
    }

    public static void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public static void debug(Exception exception, String message) {
        log(LogLevel.DEBUG, exception, message);
    }

    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    public static void info(Exception exception, String message) {
        log(LogLevel.INFO, exception, message);
    }

    public static void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public static void warn(Exception exception, String message) {
        log(LogLevel.WARN, exception, message);
    }

    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public static void error(Exception exception, String message) {
        log(LogLevel.ERROR, exception, message);
    }

    public static void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    public static void fatal(Exception exception, String message) {
        log(LogLevel.FATAL, exception, message);
    }

    private static String getColorCode(LogLevel level) {
        // rust switch?!
        return switch (level) {
            case DEBUG -> gray;
            case INFO -> blue;
            case WARN -> yellow;
            case ERROR -> red;
            case FATAL -> magenta;
        };
    }

    public static void addListener(LogListener listener) {
        listeners.add(listener);
    }

    private static String buildStackTraceElement(StackTraceElement stack) {
        return  cyan +
                stack.getClassName() +
                gray +
                "." +
                green +
                stack.getMethodName() +
                gray +
                "()";
    }
}