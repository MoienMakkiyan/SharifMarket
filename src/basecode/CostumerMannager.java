package basecode;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Random;

public class CostumerMannager {

    private static CostumerMannager instance = null;

    private CostumerMannager(){
    }
    SharedLibrary sharedLibrary =new SharedLibrary();
    ReadWriteFile readWriteFile =new ReadWriteFile();
    private ArrayList<Good> goods=new ArrayList<>();
    private ArrayList<Order> orders=new ArrayList<>();
    private ArrayList<User> users=new ArrayList<>();
    private int ID;

    public void PrintAll(){
        sharedLibrary.Print_ALL_goods(goods);
    }

    public void PrintAvailable(){
        sharedLibrary.Print_Available_goods(goods);
    }

    public void PrintUnavailable(){
        sharedLibrary.Print_Unavailable_goods(goods);
    }

    public boolean AddOrder(int costumerID, int goodID, int inventory){
        boolean found=false;
        boolean enough=false;
        int i;
        for (i=0;i<goods.size();i++){
            if(goods.get(i).getId()==goodID){
                found=true;
                if (goods.get(i).getCurrent_inventory()>=inventory&&users.get(back_index_of_user(ID)).getMoney()>=inventory*goods.get(i).getSelling_price()) enough=true;
                else enough = false;
                break;
            }
            else found=false;
        }
        if (found&&enough){
            goods.get(i).setCurrent_inventory(goods.get(i).getCurrent_inventory()-inventory);
            goods.get(i).setBuyingUntilNow(goods.get(i).getBuying_price()*inventory+goods.get(i).getBuyingUntilNow());
            goods.get(i).setSellingUntilNow(goods.get(i).getSelling_price()*inventory+goods.get(i).getSellingUntilNow());
            Order neworder = new Order(costumerID,GetDate(),goodID,inventory,Random_6_digit_num(),goods.get(i).getSelling_price(),goods.get(i).getBuying_price());
            orders.add(neworder);
            System.out.println("Your order id is = "+neworder.getOrderID());
            users.get(back_index_of_user(ID)).setMoney(users.get(back_index_of_user(ID)).getMoney()-inventory*goods.get(i).getSelling_price());
            Save();
            SaveUser();
            return true;
        }
        else {
            System.out.println("ERROR: order not successful");
            //System.out.println(String.valueOf(found) + String.valueOf(enough));
            return false;
        }
    }

    private int Random_6_digit_num(){
        Random random=new Random();
        return (random.nextInt(100000)+100000);
    }

    private String GetDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void DeleteOrder(int OrderID,int CostumerID){
        boolean found=false;
        boolean OwnOrder=false;
        int i;
        for (i=0;i<orders.size();i++){
            if (orders.get(i).getOrderID()==OrderID) {
                found=true;
                if (orders.get(i).getCostumerID()==CostumerID) OwnOrder=true;
                break;
            }
            else found=false;
        }
        if (OwnOrder){
            if (found){
                int j;
                for (j=0;j<goods.size();j++){
                    if (goods.get(j).getId()==orders.get(i).getGoodID()) break;
                }
                goods.get(j).setCurrent_inventory(goods.get(j).getCurrent_inventory()+orders.get(i).getInventory());
                goods.get(j).setBuyingUntilNow(goods.get(j).getBuyingUntilNow()-orders.get(i).getBuyPrice());
                goods.get(j).setSellingUntilNow(goods.get(j).getSellingUntilNow()-orders.get(i).getSellPrice());
                orders.remove(i);
                System.out.println("order "+OrderID+" was deleted successfully!");
            }
            else System.out.println("Error deleting order "+OrderID+" !");
        }
        else System.out.println("Hey, You do not submit this basecode.Order so you can't remove it!");
    }

    public void Save(){
        readWriteFile.Wirtegoods(goods);
        readWriteFile.WriteOrders(orders);
    }

    public void Load(){
        goods=readWriteFile.ReadGoods();
        orders=readWriteFile.ReadOrders();
    }

    public void LoadPass(){
        users = readWriteFile.ReadUser();
    }

    public void SaveUser(){
        readWriteFile.WriteUsers(users);
    }

    public boolean SignIn(String ID, String pass){
        boolean found=false;
        int i;
        for (i=0;i<users.size();i++){
            if (users.get(i).getId().equals(ID)){
                found=true;
                break;
            }
            else found=false;
        }
        if (found){
            if (users.get(i).getPassword().equals(pass)) {
                System.out.println("Username and Password Mached !");
                return true;
            }
            else {
                System.out.println("Wrong Password please try agine...");
                return false;
            }
        }
        else {
            System.out.println("Your Account has not found please create a new one!");
            return false;
        }
    }

    public void SignUp(String ID,String pass){
        User newUser = new User(ID,pass);
        users.add(newUser);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static void setInstance(CostumerMannager instance) {
        CostumerMannager.instance = instance;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int back_index_of_user(int n){
        int a = -1;
        for (int i=0 ; i < users.size() ; i++){
            if (Integer.parseInt(users.get(i).getId())==n) a = i;
        }
        return a;
    }

    public static CostumerMannager getInstance(){
        if (instance == null)
            instance = new CostumerMannager();
        return instance;
    }
}
