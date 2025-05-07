// App.java (feature/greeting-update branch)
package org.example;

public class App {
    public String getGreeting() {
        return "Hello World! This is the User enhancement module";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
