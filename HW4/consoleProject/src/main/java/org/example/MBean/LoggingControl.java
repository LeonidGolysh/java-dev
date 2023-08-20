package org.example.MBean;

import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class LoggingControl extends NotificationBroadcasterSupport implements LoggingControlMBean {
    private boolean loggingEnabled = true;

    @Override
    public boolean isLoggingEnabled() {
        return false;
    }


    @Override
    public void enableLogging() {
        if (!loggingEnabled) {
            loggingEnabled = true;
            sendNotification(new Notification("Logging enabled.", this, 0));
        }
    }

    @Override
    public void disableLogging() {
        if (loggingEnabled) {
            loggingEnabled = false;
            sendNotification(new Notification("Logging disabled.", this, 0));
        }
    }

    public void registerMBean() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("org.example.MBean:type=LoggingControl");
        mbs.registerMBean(this, name);
        System.out.println("LoggingControl MBean registered");
    }
}
