package ca.qc.cgmatane.foodwatcher.data;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.model.Home;

public class HomeDAO implements HomeSQL {

    private static HomeDAO instance = null;
    protected List<Home> listHome;

    private DataBase dataBase;

    public static HomeDAO getInstance() {
        if (null == instance) {
            instance = new HomeDAO();
        }
        return instance;
    }

    public HomeDAO() {
        this.dataBase = DataBase.getInstance();
        listHome = new ArrayList<>();
    }

    public List<Home> pickupListHome() {
        listHome = new ArrayList<>();
        mockListHome();
        return listHome;
    }

    public void mockListHome() {
        Home home;
        for (int i = 0; i < 5; i++) {
            int id_maison = i;
            String etiquette = "Maison " + i;
            home = new Home(id_maison, etiquette);
            listHome.add(home);
        }
    }
}
