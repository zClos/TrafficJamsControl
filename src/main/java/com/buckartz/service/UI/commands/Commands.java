package com.buckartz.service.UI.commands;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum Commands {
    HELP, SIGN_IN, SIGN_UP, SIGN_OUT, CHANGE_NAME, CHANGE_PSWD, ADD_ADMIN,
    FIND_PATH, ADD_CITY, ADD_BACKUP_INFO, ADD_EDGE, AVG_BACKUP_PATH, AVG_BACKUP_MAP, MAP,
    EXIT;

    private static final Map<UserTypeCommands, Set<Commands>> usersAction = new EnumMap<>(UserTypeCommands.class);

    static {
        UserTypeCommands undefUserKey = UserTypeCommands.GUEST;
        Set<Commands> undefUser = EnumSet.of(
            HELP,
            SIGN_IN, SIGN_UP,
            EXIT
        );
        usersAction.put(undefUserKey, undefUser);
        
        UserTypeCommands driverKey = UserTypeCommands.DRIVER;
        Set<Commands> driver = EnumSet.of(HELP,
            SIGN_OUT, CHANGE_NAME, CHANGE_PSWD,
            FIND_PATH, ADD_BACKUP_INFO, AVG_BACKUP_PATH, AVG_BACKUP_MAP, MAP,
            EXIT
        );
        usersAction.put(driverKey, driver);

        UserTypeCommands adminKey = UserTypeCommands.ADMIN;
        Set<Commands> admin = EnumSet.of(HELP,
            SIGN_OUT, CHANGE_NAME, CHANGE_PSWD, ADD_ADMIN,
            FIND_PATH, ADD_CITY, ADD_BACKUP_INFO, ADD_EDGE, AVG_BACKUP_PATH, AVG_BACKUP_MAP, MAP,
            EXIT
        );
        usersAction.put(adminKey, admin);
    }

    public static Set<Commands> getCommadsForUser(UserTypeCommands key) {
        return usersAction.get(key);
    }
}
