package com.leeeyou.manager.timber;

public final class FakeCrashLibrary {
    public static void log(int priority, String tag, String message) {
    }

    public static void logWarning(Throwable t) {
    }

    public static void logError(Throwable t) {
    }

    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }
}