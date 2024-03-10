package com.baeldung.disablingscheduledtasks;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Notification {

    private final UUID id = UUID.randomUUID();

    private final ZonedDateTime sendOutTime;
    private boolean isSentOut = false;

    public Notification(ZonedDateTime sendOutTime) {
        this.sendOutTime = sendOutTime;
    }

    public void sendOut(Clock clock) {
        ZonedDateTime now = ZonedDateTime.now(clock);
        if (now.isAfter(sendOutTime)) {
            isSentOut = true;
        }
    }

    public UUID getId() {
        return id;
    }

    public boolean isSentOut() {
        return isSentOut;
    }
}