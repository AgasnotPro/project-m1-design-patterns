package org.example;

import java.util.ArrayList;
import java.util.List;

public class SystemLogger {
    private final List<String> logs = new ArrayList<>();

    public void addLog(String message) {
        System.out.println(message);
        logs.add(message);
    }
}
