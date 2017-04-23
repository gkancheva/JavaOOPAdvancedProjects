package main.com.softuni.bashsoft.static_data;

import java.util.HashSet;
import java.util.Set;

public class SessionData {
    public static String currentPath = System.getProperty("user.dir");
    public static Set<Thread> threadPool = new HashSet<>();
}