package basecode;

import basecode.AdminInput;
import basecode.AdminMannager;

public class AdminPanel {
    public static void main(String[] args) {
        AdminMannager adminMannager = new AdminMannager();
        AdminInput adminInput = new AdminInput(adminMannager);
        adminInput.run();
    }
}
