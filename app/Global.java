import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

/**
 * It is used to load the initial data in the application
 * @author VGudzhev
 */
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        if (User.find.findRowCount() == 0) {
            String initialData =  Yaml.load("initial-data.yml").toString();
            String[] adminData = initialData.split(" ");
            User admin = new User.UserBuilder().name("admin").email(adminData[0]).password(adminData[1]).id(1l).role("admin").build();
            admin.save();
        }
    }
}