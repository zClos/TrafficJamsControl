package com.buckartz.service.UI.input_service;

import com.buckartz.service.LocalMap;
import com.buckartz.service.UI.Message;

public class MapDialog {

    private ScanService scanService;
    private LocalMap localMap;

    public MapDialog() {
        scanService = ScanService.getScanInstance();
        localMap = LocalMap.getLocalMapInstance();
    }


    public void addEdge() {
        localMap.showEditMap();
        String sourceCityName, targetCityName;
        do {
            System.out.println(Message.SET_START_ROUTE);
            sourceCityName = scanService.scanString().trim();

            System.out.println(Message.SET_FINISH_ROUTE);
            targetCityName = scanService.scanString().trim();

            do {
                Integer x, y, backup;
                System.out.println(Message.SET_X);
                x = scanService.scanInt();
                if (x < 0 || x >= localMap.getMapSize()[0]) {
                    System.out.println(Message.INCORRECT_DATA);
                    continue;
                }
                System.out.println(Message.SET_Y);
                y = scanService.scanInt();
                if (y < 0 || y >= localMap.getMapSize()[1]) {
                    System.out.println(Message.INCORRECT_DATA);
                    continue;
                }
                System.out.println(Message.SET_BACKUP);
                backup = scanService.scanInt();
                if (backup < -1 || backup > 10) {
                    System.out.println(Message.INCORRECT_DATA + "[-1;10]");
                    continue;
                }
                localMap.addTmpRoad(x, y, backup);
            } while (scanService.repeat(Message.ADD_ROAD + Message.CONFIRM));

            if (localMap.addEdge(sourceCityName, targetCityName)) {
                System.out.println(Message.EDGE_ADD_SUCCESS);
            } else {
                System.out.println(Message.EDGE_ADD_FAIL);
            }

        } while (scanService.repeat(Message.REPEAT_MSG + Message.CONFIRM));

    }

    public void addCity() {
        localMap.showEditMap();
        Integer x, y;
        String name;
        do {
            System.out.println(Message.SET_CITY_NAME);
            name = scanService.scanString().trim();

            System.out.println(Message.SET_X);
            x = scanService.scanInt();
            if (x < 0 || x >= localMap.getMapSize()[0]) {
                System.out.println(Message.INCORRECT_DATA);
                continue;
            }
            System.out.println(Message.SET_Y);
            y = scanService.scanInt();
            if (y < 0 || y >= localMap.getMapSize()[1]) {
                System.out.println(Message.INCORRECT_DATA);
                continue;
            }

            if (localMap.addCity(name, x, y)) {
                System.out.println(Message.CITY_ADD_SUCCESS);
            } else {
                System.out.println(Message.CITY_ADD_FAIL);
            }

        } while (scanService.repeat(Message.REPEAT_MSG + Message.CONFIRM));
    }

    public void getPath() {
        localMap.showMap();
        String sourceCityName, targetCityName;
        do {
            System.out.println(Message.SET_START_ROUTE);
            sourceCityName = scanService.scanString().trim();

            System.out.println(Message.SET_FINISH_ROUTE);
            targetCityName = scanService.scanString().trim();

            localMap.getPath(sourceCityName, targetCityName);

        } while (scanService.repeat(Message.REPEAT_MSG + Message.CONFIRM));
    }

    public void addBackupInfo() {
        localMap.showEditMap();
        String sourceCityName, targetCityName;
        do {
            System.out.println(Message.SET_START_ROUTE);
            sourceCityName = scanService.scanString().trim();

            System.out.println(Message.SET_FINISH_ROUTE);
            targetCityName = scanService.scanString().trim();

            Integer x, y, backup;
            System.out.println(Message.SET_X);
            x = scanService.scanInt();
            if (x < 0 || x >= localMap.getMapSize()[0]) {
                System.out.println(Message.INCORRECT_DATA);
                continue;
            }
            System.out.println(Message.SET_Y);
            y = scanService.scanInt();
            if (y < 0 || y >= localMap.getMapSize()[1]) {
                System.out.println(Message.INCORRECT_DATA);
                continue;
            }
            System.out.println(Message.SET_BACKUP);
            backup = scanService.scanInt();
            if (backup < -1 || backup > 10) {
                System.out.println(Message.INCORRECT_DATA + "[-1;10]");
                continue;
            }
            localMap.addBackupInfo(sourceCityName, targetCityName, x, y, backup);
        } while (scanService.repeat(Message.REPEAT_MSG + Message.CONFIRM));

    }

    public void avgBackupMap() {
        System.out.printf(Message.AVG_BACKUP_MAP + " %.2f\n", localMap.getAvgMapBackup());
    }

    public void avgBackupPath() {
        System.out.printf(Message.AVG_BACKUP_PATH + " %.2f\n", localMap.getAvgPathBackup());
    }

    public void showMap() {
        localMap.showMap();
    }
}
