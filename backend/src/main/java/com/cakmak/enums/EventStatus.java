package com.cakmak.enums;

public enum EventStatus {
    FUTURE,
    LIVE,
    FINISHED;

    public static EventStatus fromString(String status) {
        switch (status) {
            case "FUTURE":
                return FUTURE;
            case "LIVE":
                return LIVE;
            case "FINISHED":
                return FINISHED;
            default:
                return null;
        }
    }
}
