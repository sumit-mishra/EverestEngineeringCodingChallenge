package com.tameofthrones.communicator;

import java.util.Scanner;


public class CommandLine {
    private Scanner scanner;

    private static class Holder {
        private static final CommandLine INSTANCE = new CommandLine();
    }

    public static CommandLine instance() {
        return Holder.INSTANCE;
    }

    private CommandLine() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void printInCommandLine(final String text) {
        System.out.println(text);
    }

    public void close() {
        scanner.close();
    }

}
