import java.util.List;

import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import com.avaje.ebean.Ebean;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        if (User.find.findRowCount() == 0) {
            String initialData =  Yaml.load("initial-data.yml").toString();
            String[] adminData = initialData.split(" ");
            User admin = new User.UserBuilder().email(adminData[0]).password(adminData[1]).id(1l).build();
            admin.save();
        }
    }
}