package basecode;

import java.util.ArrayList;
import java.util.Random;

public class AdminMannager {

    private static AdminMannager instance = null;

    private AdminMannager(){
    }

    SharedLibrary sharedLibrary =new SharedLibrary();
    ReadWriteFile readWriteFile =new ReadWriteFile();
    private ArrayList<Good> goods=new ArrayList<>();
    private ArrayList<Order> orders=new ArrayList<>();

    public void PrintAll(){
        sharedLibrary.Print_ALL_goods(goods);
    }

    public void PrintAvailable(){
        sharedLibrary.Print_Available_goods(goods);
    }

    public void PrintUnavailable(){
        sharedLibrary.Print_Unavailable_goods(goods);
    }

    public void PrintOrderList(){
        System.out.println("+---------+-----------------+---------+------------+------------+---------------------+-------------------+");
        System.out.println("| OrderID | basecode.Good name       | GoodID  | Inventory  | CostumerID | Registration time   | Processing status |");
        System.out.println("+---------+-----------------+---------+------------+------------+---------------------+-------------------+");
        for (int i=0;i<orders.size();i++){
            System.out.print("| ");
            System.out.print(orders.get(i).getOrderID());
            System.out.print("  | ");
            int j;
            for (j=0;j<goods.size();j++){
                if (goods.get(j).getId()==orders.get(i).getGoodID()){
                    System.out.print(goods.get(j).getName());
                    int a = 15 - goods.get(j).getName().length();
                    while (a >= 0){
                        System.out.print(" ");
                        a--;
                    }
                    break;
                }
            }
            System.out.print("| ");
            System.out.print(orders.get(i).getGoodID());
            System.out.print("  ");
            System.out.print("| ");
            System.out.print(orders.get(i).getInventory());
            System.out.print(" ");
            System.out.print(goods.get(j).getMDFR());
            int aa = 9 - (String.valueOf(orders.get(i).getInventory()).length() + goods.get(j).getMDFR().length());
            while (aa >= 0){
                System.out.print(" ");
                aa--;
            }
            System.out.print("| ");
            System.out.print(orders.get(i).getCostumerID());
            System.out.print("      | ");
            System.out.print(orders.get(i).getDate());
            System.out.print(" |");
            if (orders.get(i).isDone()) System.out.print("     Processed     ");
            else System.out.print("    UnProcessed    ");
            System.out.print("|");
            System.out.print("\n");
        }
        System.out.println("+---------+-----------------+---------+------------+------------+---------------------+-------------------+");
    }

    public void PrintUnProcessedOrderList(){
        System.out.println("+---------+-----------------+---------+------------+------------+---------------------+");
        System.out.println("| OrderID | basecode.Good name       | GoodID  | Inventory  | CostumerID | Registration time   |");
        System.out.println("+---------+-----------------+---------+------------+------------+---------------------+");
        for (int i=0;i<orders.size();i++) {
            if (!orders.get(i).isDone()) {
                System.out.print("| ");
                System.out.print(orders.get(i).getOrderID());
                System.out.print("  | ");
                int j;
                for (j=0;j<goods.size();j++){
                    if (goods.get(j).getId()==orders.get(i).getGoodID()){
                        System.out.print(goods.get(j).getName());
                        int a = (15 - goods.get(j).getName().length());
                        while (a >= 0){
                            System.out.print(" ");
                            a--;
                        }
                        break;
                    }
                }
                System.out.print("| ");
                System.out.print(orders.get(i).getGoodID());
                System.out.print("  ");
                System.out.print("| ");
                System.out.print(orders.get(i).getInventory());
                System.out.print(" ");
                System.out.print(goods.get(j).getMDFR());
                int aa = 9 - (String.valueOf(orders.get(i).getInventory()).length() + goods.get(j).getMDFR().length());
                while (aa >= 0){
                    System.out.print(" ");
                    aa--;
                }
                System.out.print("| ");
                System.out.print(orders.get(i).getCostumerID());
                System.out.print("      | ");
                System.out.print(orders.get(i).getDate());
                System.out.print(" |");
                System.out.print("\n");
            }
        }
        System.out.println("+---------+-----------------+---------+------------+------------+---------------------+");
    }

    public void CheckOut(int OrderID){
        boolean found=false;
        int i;
        for (i=0;i<orders.size();i++){
            if (orders.get(i).getOrderID()==OrderID){
                found=true;
                break;
            }
        }
        if (found){
            System.out.println("order "+OrderID+" checkedout successfully!");
            orders.get(i).setDone(true);
        }
        else System.out.println("order "+OrderID+" do not checkedout successfully!");
    }

    public void AddGoods(String Name, int Count,String MDFR, int SellPrice, int BuyPrice){
        boolean isThere=false;
        boolean haveProfit=false;
        if (SellPrice>BuyPrice) haveProfit=true;
        else haveProfit=false;
        int i;
        for (i=0;i<goods.size();i++){
            if (goods.get(i).getName().equals(Name)){
                isThere=true;
                break;
            }
        }
        if (!isThere){
            if (haveProfit){
                Good newGood = new Good(Name,Count,MDFR,SellPrice,BuyPrice,Random_6_digit_num());
                goods.add(newGood);
                System.out.println("add was successful -> good_id = "+newGood.getId());
            }
            else System.out.println("Adding & Selling this basecode.Good has no profit so we don't need it :)");
        }
        else System.out.println("There is "+Name+" at the Market and its ID is "+goods.get(i).getId()+" !");
    }

    private int Random_6_digit_num(){
        Random random=new Random();
        return (random.nextInt(100000)+100000);
    }

    public void RemoveGood(int ID){
       boolean found=false;
       int i;
       for (i=0;i<goods.size();i++){
           if (goods.get(i).getId()==ID){
               found=true;
               break;
           }
           else found=false;
       }
       if (found) {
           goods.remove(i);
           System.out.println("That basecode.Good removed successfully!");
       }
       else System.out.println("There is no basecode.Good with this ID !");
    }

    public int AllProfit(){
        int profit=0;
        for (int i=0;i<goods.size();i++){
            profit+=(goods.get(i).getSelling_price()-goods.get(i).getBuying_price());
        }
        System.out.println(profit+" IRR");
        return profit;
    }

    public void GoodProfit(int ID){
        int i;
        for (i=0;i<goods.size();i++){
            if (goods.get(i).getId()==ID) break;
        }
        int Profit = goods.get(i).getSelling_price()-goods.get(i).getSelling_price();
        System.out.println(Profit+" IRR");
    }

    public void SaleStatistics(){
        int AllOrder=0;
        int AllProfit=0;
        int AllSell=0;
        System.out.println("+------------------+-----------------+---------+---------------+--------------+-------------+");
        System.out.println("| Number of Orders | basecode.Good name       | GoodID  | Sales number  | Selling(IRR) | Profit(IRR) |");
        System.out.println("+------------------+-----------------+---------+---------------+--------------+-------------+");
        for (int i=0;i<goods.size();i++){
            int OrderNum=0;
            int SellNum=0;
            for (int j=0;j<orders.size();j++){
                if (orders.get(j).getGoodID()==goods.get(i).getId()){
                    OrderNum++;
                    SellNum+=orders.get(j).getInventory();
                }
            }
            System.out.print("| ");
            System.out.print(OrderNum);
            for (int j=17-String.valueOf(OrderNum).length();j>0;j--) System.out.print(" ");
            System.out.print("| ");
            System.out.print(goods.get(i).getName());
            int a = 15 - goods.get(i).getName().length();
            while (a >= 0){
                System.out.print(" ");
                a--;
            }
            System.out.print("| ");
            System.out.print(goods.get(i).getId());
            System.out.print("  | ");
            System.out.print(SellNum);
            for (int j=14-String.valueOf(SellNum).length();j>0;j--) System.out.print(" ");
            System.out.print("| ");
            System.out.print(goods.get(i).getSellingUntilNow());
            for (int j=13-String.valueOf(goods.get(i).getSellingUntilNow()).length();j>0;j--) System.out.print(" ");
            System.out.print("| ");
            System.out.print(goods.get(i).getSellingUntilNow()-goods.get(i).getBuyingUntilNow());
            for (int j=12-String.valueOf(goods.get(i).getSellingUntilNow()-goods.get(i).getBuyingUntilNow()).length();j>0;j--) System.out.print(" ");
            System.out.print("|");
            System.out.print("\n");
            AllOrder+=OrderNum;
            AllSell+=goods.get(i).getSellingUntilNow();
            AllProfit+=goods.get(i).getSellingUntilNow()-goods.get(i).getBuyingUntilNow();
        }
        System.out.print("+-------------------------------------------------------------------------------------------+");
        System.out.print("\n");
        System.out.print("| All Orders = ");
        System.out.print(AllOrder);
        for (int i=17-String.valueOf(AllOrder).length();i>0;i--) System.out.print(" ");
        System.out.print("| All Sells = ");
        System.out.print(AllSell);
        for (int i=17-String.valueOf(AllSell).length();i>0;i--) System.out.print(" ");
        System.out.print("| All Profits = ");
        System.out.print(AllProfit);
        for (int i=13-String.valueOf(AllProfit).length();i>0;i--) System.out.print(" ");
        System.out.print("|");
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
    }

    public void EachSaleStatistics(int ID){
        int OrderNum=0;
        int SellNum=0;
        int Sell=0;
        int Profit=0;
        for (int i=0;i<orders.size();i++){
            if (orders.get(i).getGoodID()==ID){
                OrderNum++;
                SellNum+=orders.get(i).getInventory();
                Sell+=orders.get(i).getSellPrice();
                Profit+=(orders.get(i).getSellPrice()+orders.get(i).getBuyPrice());
            }
        }
        String GoodName="";
        for (int i=0;i<goods.size();i++){
            if (goods.get(i).getId()==ID){
                GoodName=goods.get(i).getName();
                break;
            }
        }
        System.out.println(OrderNum+" orders, "+SellNum+" "+GoodName+" ( ID = "+ID+" )"+", "+Sell+" IRR sell, "+Profit+" IRR profit");
    }

    public void EditNameCountGood(int ID, String NewName, int NewCount, String MDFR){
        boolean found=false;
        int i;
        for (i=0;i<goods.size();i++){
            if (goods.get(i).getId()==ID){
                found=true;
                break;
            }
            else found=false;
        }
        if (found){
            System.out.print("basecode.Good ID = "+ID+" :");
            System.out.print(goods.get(i).getName()+" "+goods.get(i).getCurrent_inventory()+" "+goods.get(i).getMDFR()+" ~> ");
            goods.get(i).setName(NewName);
            goods.get(i).setCurrent_inventory(NewCount);
            goods.get(i).setMDFR(MDFR);
            System.out.print(goods.get(i).getName()+" "+goods.get(i).getCurrent_inventory()+" "+goods.get(i).getMDFR());
        }
        else System.out.println("There is no basecode.Good with this ID !");
    }

    public void EditNameGood(int ID, String NewName){
        boolean found=false;
        int i;
        for (i=0;i<goods.size();i++){
            if (goods.get(i).getId()==ID){
                found=true;
                break;
            }
            else found=false;
        }
        if (found){
            System.out.print("basecode.Good ID = "+ID+" :");
            System.out.print(goods.get(i).getName()+" ~> ");
            goods.get(i).setName(NewName);
            System.out.print(goods.get(i).getName());
        }
        else System.out.println("There is no basecode.Good with this ID !");
    }

    public void EditSellPriceCountGood(int ID, int NewSellPrice, int NewBuyPrice, int NewCount, String MDFR){
        boolean found=false;
        int i;
        for (i=0;i<goods.size();i++){
            if (goods.get(i).getId()==ID){
                found=true;
                break;
            }
            else found=false;
        }
        boolean profit = NewSellPrice > NewBuyPrice;
        if (found){
            if (profit){
                System.out.print("basecode.Good ID = "+ID+" :");
                System.out.print(goods.get(i).getName()+" "+goods.get(i).getCurrent_inventory()+" "+goods.get(i).getMDFR()+" Buy Price : "+goods.get(i).getBuying_price()+" Sell Price : "+goods.get(i).getSelling_price()+" ~> ");
                goods.get(i).setBuying_price(NewBuyPrice);
                goods.get(i).setSelling_price(NewSellPrice);
                goods.get(i).setCurrent_inventory(NewCount);
                goods.get(i).setMDFR(MDFR);
                System.out.print(goods.get(i).getName()+" "+goods.get(i).getCurrent_inventory()+" "+goods.get(i).getMDFR()+" Buy Price : "+goods.get(i).getBuying_price()+" Sell Price : "+goods.get(i).getSelling_price());
            }
            else System.out.println("Adding & Selling this basecode.Good has no profit so we don't need it :)");
        }
        else System.out.println("There is no basecode.Good with this ID !");
    }

    public void Save(){
        readWriteFile.Wirtegoods(goods);
        readWriteFile.WriteOrders(orders);
    }

    public void Load(){
        System.out.println("Data Read !");
        goods=readWriteFile.ReadGoods();
        orders=readWriteFile.ReadOrders();
    }

    public static void setInstance(AdminMannager instance) {
        AdminMannager.instance = instance;
    }

    public SharedLibrary getSharedLibrary() {
        return sharedLibrary;
    }

    public void setSharedLibrary(SharedLibrary sharedLibrary) {
        this.sharedLibrary = sharedLibrary;
    }

    public ReadWriteFile getReadWriteFile() {
        return readWriteFile;
    }

    public void setReadWriteFile(ReadWriteFile readWriteFile) {
        this.readWriteFile = readWriteFile;
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

    public int AllSell(){
        //int AllOrder=0;
        //int AllProfit=0;
        int AllSell=0;
        for (int i=0;i<goods.size();i++){
            int OrderNum=0;
            int SellNum=0;
            for (int j=0;j<orders.size();j++){
                if (orders.get(j).getGoodID()==goods.get(i).getId()){
                    OrderNum++;
                    SellNum+=orders.get(j).getInventory();
                }
            }
            //AllOrder+=OrderNum;
            AllSell+=goods.get(i).getSellingUntilNow();
            //AllProfit+=goods.get(i).getSellingUntilNow()-goods.get(i).getBuyingUntilNow();
        }
        return AllSell;
    }

    public int AllOrder(){
        int AllOrder=0;
        //int AllProfit=0;
        //int AllSell=0;
        for (int i=0;i<goods.size();i++){
            int OrderNum=0;
            int SellNum=0;
            for (int j=0;j<orders.size();j++){
                if (orders.get(j).getGoodID()==goods.get(i).getId()){
                    OrderNum++;
                    SellNum+=orders.get(j).getInventory();
                }
            }
            AllOrder+=OrderNum;
            //AllSell+=goods.get(i).getSellingUntilNow();
            //AllProfit+=goods.get(i).getSellingUntilNow()-goods.get(i).getBuyingUntilNow();
        }
        return AllOrder;
    }

    public void SetSellNum(){
        for (int i=0;i<goods.size();i++){
            int SellNum=0;
            for (int j=0;j<orders.size();j++){
                if (orders.get(j).getGoodID()==goods.get(i).getId()){
                    SellNum+=orders.get(j).getInventory();
                }
            }
            goods.get(i).setSellNum(SellNum);
        }
    }

    public static AdminMannager getInstance(){
        if (instance == null)
            instance = new AdminMannager();
        return instance;
    }
}
