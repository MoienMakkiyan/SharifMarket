public class AdminPanel {
    public static void main(String[] args) {
        AdminMannager adminMannager = new AdminMannager();
        AdminInput adminInput = new AdminInput(adminMannager);
        adminInput.run();
    }
}
