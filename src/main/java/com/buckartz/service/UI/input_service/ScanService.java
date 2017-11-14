package com.buckartz.service.UI.input_service;

import com.buckartz.service.UI.Message;

import java.util.Scanner;

public class ScanService {
    
    private static final String CHARSET_NAME = "Cp1251";

    private Scanner scanner;

    private static ScanService scanService;

    private ScanService() {
        scanner = new Scanner(System.in, CHARSET_NAME);
    }

    public static ScanService getScanInstance() {
        if (scanService == null) {
            scanService = new ScanService();
            return scanService;
        } else {
            return scanService;
        }
    }

    public int scanInt() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return -1;
    }
    
    public String scanString() {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        return null;
    }
    
    public boolean repeat(String msg) {
        do {
            System.out.println(msg);
            String input = scanString().trim();
            input = input.toLowerCase();
            switch (input) {
                case "да":
                case "д":
                case "yes":
                case "y":
                    return true;
                case "нет":
                case "н":
                case "no":
                case "n":
                    return false;
                default:
                    System.out.println(Message.INCORRECT_DATA + input);
            }
        } while (true);
    }
}
