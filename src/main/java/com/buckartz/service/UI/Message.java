package com.buckartz.service.UI;

public class Message {

//  ScanService msg
    public static final String REPEAT_MSG = "Желаете повторить команду? ";
    public static final String CONFIRM = "Да(Д)/Нет(Н) Yes(Y)/No(N)";
    public static final String INCORRECT_DATA = "Вы ввели не корректные данные ";

// UserDialog
    public static final String NAME = "Введите имя";
    public static final String LOGIN = "Введите логин";
    public static final String PASSWORD = "Введите пароль";
    public static final String SUCCESS_SIGN_UP = "Вы успешно зарегистрировались, войдите в систему";
    public static final String USER_EXIST = "Пользователь с таким login'om уже существует";
    public static final String USER_DOESNT_EXIST = "Пользователь не найден";

// App messages
    public static final String HELLO = "Добро пожаловать, ";
    public static final String GUEST = "Гость";
    public static final String INPUT_COMMAND = "Введите команду, для справки воспользуйтесь help";
    public static final String NOT_AVAILABLE = "У вас нет прав для этой команды";
    public static final String NOT_ENTER = "Вы не ввели команду";
    public static final String UNKNOWN = "Неизвестная команда: ";
    public static final String LIST_COMMAND = "Список доступных команд:";
    public static final String AVG_BACKUP_MAP = "Средняя загруженность дорог по региону ";
    public static final String AVG_BACKUP_PATH = "Средняя загруженность дорог по маршруту ";

//   MapDialog messages
    public static final String SET_CITY_NAME = "Введите название города";
    public static final String CITY_ADD_SUCCESS = "Город успешно добавлен";
    public static final String CITY_ADD_FAIL = "Не удалось добавить город";
    public static final String SET_X = "Введите x(верт) координату";
    public static final String SET_Y = "Введите y(гор) координату";
    public static final String ADD_ROAD = "Желаете ввести еще участок дороги? ";
    public static final String SET_BACKUP = "Введите Вашу оценку загруженности дороги от -1 до 10";
    public static final String SET_START_ROUTE = "Введите начало маршрута";
    public static final String SET_FINISH_ROUTE = "Введите конечную точку маршрута";
    public static final String EDGE_ADD_SUCCESS = "Связь добавленна успешно";
    public static final String EDGE_ADD_FAIL = "Не удалось добавить связь";
}
