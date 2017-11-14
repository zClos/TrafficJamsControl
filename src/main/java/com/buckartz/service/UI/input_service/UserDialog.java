package com.buckartz.service.UI.input_service;

import com.buckartz.model.user.User;
import com.buckartz.service.UI.Message;
import com.buckartz.service.service_user.UserService;

public class UserDialog {

    private ScanService scanService = ScanService.getScanInstance();
    private UserService userService;

    public UserDialog() {
        scanService = ScanService.getScanInstance();
        userService = new UserService();
    }

    public User signIn() {
        String login, pswrd;
        do {
            System.out.println(Message.LOGIN);
            login = scanService.scanString().trim();

            System.out.println(Message.PASSWORD);
            pswrd = scanService.scanString().trim();

            User user = userService.singIn(login, pswrd);
            if (user != null) {
                return user;
            } else {
                System.out.println(Message.USER_DOESNT_EXIST);
            }
        } while (scanService.repeat(Message.REPEAT_MSG + Message.CONFIRM));
        return null;
    }

    public void signUp() {
        String name, login, pswrd;
        do {
            System.out.println(Message.NAME);
            name = scanService.scanString().trim();

            System.out.println(Message.LOGIN);
            login = scanService.scanString().trim();

            System.out.println(Message.PASSWORD);
            pswrd = scanService.scanString().trim();

            if (userService.signUp(name, login, pswrd)) {
                System.out.println(Message.SUCCESS_SIGN_UP);
                break;
            } else {
                System.out.println(Message.USER_EXIST);
            }
        } while (scanService.repeat(Message.REPEAT_MSG + Message.CONFIRM));
    }

    public void changeName(User user) {
        String name;
        System.out.println(Message.NAME);
        name = scanService.scanString().trim();
        user.setName(name);
        userService.updateData(user);
    }

    public void changePswd(User user) {
        String pswd;
        System.out.println(Message.PASSWORD);
        pswd = scanService.scanString().trim();
        user.setPass(pswd);
        userService.updateData(user);
    }

    public boolean addAdmin() {
        String login, pswrd;
        do {
            System.out.println(Message.LOGIN);
            login = scanService.scanString().trim();

            System.out.println(Message.PASSWORD);
            pswrd = scanService.scanString().trim();

            if (userService.addAdmin(login, pswrd)) {
                return true;
            } else {
                System.out.println(Message.USER_EXIST);
            }
        } while (scanService.repeat(Message.REPEAT_MSG + Message.CONFIRM));
        return false;
    }
}
