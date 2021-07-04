
package Main;
import Marketing.Marketing;
import Users.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
       Login impmnt=new Login();
    
        if(impmnt.admin_is_exist())
        {
            admin_registration ar=new admin_registration();
            LoadingScreen.start(ar, 35);
        }
        else
        {
            user_login u=new user_login();
            LoadingScreen.start(u, 35);
        }
    }
    
}
