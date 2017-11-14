package com.buckartz;

import com.buckartz.model.user.Admin;
import com.buckartz.model.user.User;
import com.buckartz.other.FillDataBase;
import com.buckartz.service.LocalMap;
import com.buckartz.service.UI.Message;
import com.buckartz.service.UI.commands.Commands;
import com.buckartz.service.UI.commands.UserTypeCommands;
import com.buckartz.service.UI.input_service.MapDialog;
import com.buckartz.service.UI.input_service.ScanService;
import com.buckartz.service.UI.input_service.UserDialog;

import java.util.Set;

public class App {


    private ScanService scanService;
    private MapDialog mapDialog;
    private UserDialog userDialog;

    private User user;
    private LocalMap localMap;
    private Set<Commands> commandsList;

    public App() {
        scanService = ScanService.getScanInstance();
        mapDialog = new MapDialog();
        userDialog = new UserDialog();
        commandsList = Commands.getCommadsForUser(UserTypeCommands.GUEST);
    }

    public static void main(String[] args) {
//        If not uses, in the hibernate.cfg.xml comment 13 line
        FillDataBase.fillData();

        App app = new App();
        app.load();
        app.chooseCommand();
    }

    public void load() {
        String name;
        if (user != null) {
            localMap = LocalMap.getLocalMapInstance();
            name = user.getName();
        } else {
            name = Message.GUEST;
        }
        System.out.println(Message.HELLO + name + "!");
    }

    public void chooseCommand() {
        do {
            System.out.println(Message.INPUT_COMMAND);
            String commandStr = scanService.scanString().trim();
            try {
                Commands command = Commands.valueOf(commandStr.toUpperCase());
                if (commandsList.contains(command)) {
                    switch (command) {
                        case HELP:
                            help();
                            break;

                        case SIGN_IN:
                            user = userDialog.signIn();
                            if (user != null) {
                                load();
                                if (user instanceof Admin)
                                    commandsList = Commands.getCommadsForUser(UserTypeCommands.ADMIN);
                                else
                                    commandsList = Commands.getCommadsForUser(UserTypeCommands.DRIVER);
                            }
                            break;

                        case SIGN_UP:
                            userDialog.signUp();
                            break;

                        case SIGN_OUT:
                            signOut();
                            load();
                            break;

                        case CHANGE_NAME:
                            userDialog.changeName(user);
                            break;

                        case CHANGE_PSWD:
                            userDialog.changePswd(user);
                            break;

                        case ADD_ADMIN:
                            userDialog.addAdmin();
                            break;

                        case FIND_PATH:
                            mapDialog.getPath();
                            break;

                        case ADD_CITY:
                            mapDialog.addCity();
                            break;

                        case ADD_BACKUP_INFO:
                            mapDialog.addBackupInfo();
                            break;

                        case ADD_EDGE:
                            mapDialog.addEdge();
                            break;

                        case AVG_BACKUP_PATH:
                            mapDialog.avgBackupPath();
                            break;

                        case AVG_BACKUP_MAP:
                            mapDialog.avgBackupMap();
                            break;

                        case MAP:
                            mapDialog.showMap();
                            break;

                        case EXIT:
                            exit();
                    }
                } else {
                    System.out.println(Message.NOT_AVAILABLE);
                }
            } catch (NullPointerException ex) {
                System.out.println(Message.NOT_ENTER);
            } catch (IllegalArgumentException ex) {
                System.out.println(Message.UNKNOWN + commandStr);
            }
        } while (true);

    }

    public void help() {
        System.out.println(Message.LIST_COMMAND);
        System.out.println(commandsList);
    }

    public void signOut() {
        user = null;
        commandsList = Commands.getCommadsForUser(UserTypeCommands.GUEST);
    }

    public void exit() {
        System.exit(0);
    }
}
