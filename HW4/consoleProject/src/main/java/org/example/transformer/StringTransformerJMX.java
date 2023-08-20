package org.example.transformer;

import org.example.MBean.LoggingControl;
import org.example.transformer.StringTransformer;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class StringTransformerJMX {
    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("org.example.MBean:type=LoggingControl");
        LoggingControl loggingControl = new LoggingControl();
        mbs.registerMBean(loggingControl, name);

        StringTransformer stringTransformer = new StringTransformer();
        stringTransformer.setLoggingControlMBean(loggingControl);

        System.out.println("Waiting forever...");
        stringTransformer.run();
    }
}
