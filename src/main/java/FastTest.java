import com.buckartz.App;
import com.buckartz.dao.impl.CityDaoImpl;
import com.buckartz.model.user.User;
import com.buckartz.other.FillDataBase;
import com.buckartz.model.map.City;
import com.buckartz.service.LocalMap;
import com.buckartz.service.UI.input_service.MapDialog;
import com.buckartz.service.service_user.UserService;
import com.buckartz.util.HibernateUtil;

public class FastTest {

    public static void main(String[] args) {
//        If not uses, in the hibernate.cfg.xml comment 13 line
        FillDataBase.fillData();

        App app = new App();
        app.load();
        LocalMap localMap = LocalMap.getLocalMapInstance();
        localMap.showMap();
        localMap.addCity("D", 4, 0);
        localMap.addTmpRoad(3, 0, 1);
        localMap.addTmpRoad(2, 0, 3);
        localMap.addTmpRoad(1, 0, 4);
        localMap.addEdge("D", "A");
        localMap.getPath("C", "B");
        localMap.showMap();
        localMap.addTmpRoad(4, 9, 0);
        localMap.addEdge("C", "B");
        localMap.getPath("C", "B");
        localMap.showMap();
        localMap.getPath("D", "C");
        localMap.showMap();
        localMap.getPath("D", "B");
        localMap.showMap();

        UserService userService = new UserService();
        userService.signUp("qwe", "some", "some");
        System.out.println(userService.singIn("qwe", "qwe"));
        System.out.println(userService.singIn("qwe", "qwe"));
        System.out.println(userService.singIn("driver", "driver"));
        userService.signUp("qwe", "some1", "some1");
        System.out.println(userService.singIn("some1", "some1"));
        userService.addAdmin("dba", "dba");
        User dba = userService.singIn("dba", "dba");
        dba.setName("dba");
        userService.updateData(dba);
        System.out.println(userService.singIn("dba", "dba"));
    }
}