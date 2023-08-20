package org.example.MBean;

import javax.management.NotificationEmitter;

public interface LoggingControlMBean extends NotificationEmitter {

    boolean isLoggingEnabled();

    void enableLogging();
    void disableLogging();
}
