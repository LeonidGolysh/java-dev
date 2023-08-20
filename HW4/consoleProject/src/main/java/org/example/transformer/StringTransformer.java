package org.example.transformer;

import org.example.MBean.LoggingControl;
import org.example.MBean.LoggingControlMBean;

import java.util.Scanner;

public class StringTransformer {
    private final Scanner scanner = new Scanner(System.in);
    private boolean loggingEnable = true;
    private LoggingControlMBean loggingControlMBean;

    public void setLoggingControlMBean(LoggingControlMBean loggingControlMBean) {
        this.loggingControlMBean = loggingControlMBean;
    }

    public void run() {
        System.out.println("Enter a string: ");
        String input = scanner.nextLine();

        if (loggingEnable) {
            logInput(input);
        }

        String transformedString = transformedString(input);
        if (loggingEnable) {
            logTransformed(transformedString);
        }
    }

    private void logTransformed(String transformedString) {
        System.out.println("Transformed: " + transformedString);
    }

    private String transformedString(String input) {
        StringBuilder transformed = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (i % 2 == 0) {
                transformed.append(Character.toUpperCase(c));
            } else {
                transformed.append(Character.toLowerCase(c));
            }
        }
        return transformed.toString();
    }

    private void logInput(String input) {
        System.out.println("Input: " + input);
    }

    public static void main(String[] args) {
        StringTransformer stringTransformer = new StringTransformer();
        LoggingControl loggingControl = new LoggingControl();

        stringTransformer.setLoggingControlMBean(loggingControl);

        try {
            loggingControl.registerMBean();
        } catch (Exception e) {
            System.err.println("Error registering MBean: " + e.getMessage());
        }

        stringTransformer.run();
    }
}
