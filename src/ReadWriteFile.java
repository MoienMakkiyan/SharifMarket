import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWriteFile {

    private ArrayList<Good> goods;
    private ArrayList<Order> orders;
    private ArrayList<User> users;
    String AA = "OrdersData.txt";

    public ReadWriteFile() {
    }

    public ArrayList<Good> ReadGoods(){
        ArrayList<Good> goods = new ArrayList<>();
        try {
            File myObj = new File("GoodsData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("\\s");
                Good addGood = new Good(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]),data[7]);
                goods.add(addGood);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:(");
            //e.printStackTrace();
            CreateGoodsFile("read");
        }
        return goods;
    }

    public ArrayList<Order> ReadOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        try {
            File myObj = new File("OrdersData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("\\s");
                Order addOrder = new Order(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3]),data[4]+" "+data[5],Integer.parseInt(data[6]),Integer.parseInt(data[7]),Boolean.valueOf(data[8]));
                orders.add(addOrder);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:(");
            //e.printStackTrace();
            CreateOrdersFile("read");
        }
        return orders;
    }

    public ArrayList<User> ReadUser(){
        ArrayList<User> users = new ArrayList<>();
        try {
            File myObj = new File("UsersData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("\\s");
                User addUser = new User(data[0],data[1]);
                users.add(addUser);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:(");
            //e.printStackTrace();
            CreateUsersFile();
        }
        return users;
    }

    public void Wirtegoods(ArrayList<Good> goods){
        this.goods=new ArrayList<>(goods);
        try {
            FileWriter myWriter = new FileWriter("GoodsData.txt");
            for (int i=0;i<goods.size();i++){
                myWriter.write(goods.get(i).getName());
                myWriter.write(" ");
                myWriter.write(String.valueOf(goods.get(i).getCurrent_inventory()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(goods.get(i).getSelling_price()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(goods.get(i).getBuying_price()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(goods.get(i).getId()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(goods.get(i).getSellingUntilNow()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(goods.get(i).getBuyingUntilNow()));
                myWriter.write(" ");
                myWriter.write(goods.get(i).getMDFR());
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully GoodsData saved to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred:(");
            //e.printStackTrace();
            CreateGoodsFile("write");
        }
    }

    public void WriteOrders(ArrayList<Order> orders){
        this.orders=new ArrayList<>(orders);
        try {
            FileWriter myWriter = new FileWriter("OrdersData.txt");
            for (int i=0;i<orders.size();i++){
                myWriter.write(String.valueOf(orders.get(i).getCostumerID()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(orders.get(i).getOrderID()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(orders.get(i).getSellPrice()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(orders.get(i).getBuyPrice()));
                myWriter.write(" ");
                myWriter.write(orders.get(i).getDate());
                myWriter.write(" ");
                myWriter.write(String.valueOf(orders.get(i).getGoodID()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(orders.get(i).getInventory()));
                myWriter.write(" ");
                myWriter.write(String.valueOf(orders.get(i).isDone()));
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully OrdersData saved to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred:(");
            //e.printStackTrace();
            CreateOrdersFile("write");
        }
    }

    public void WriteUsers(ArrayList<User> users){
        this.users=new ArrayList<>(users);
        try {
            FileWriter myWriter = new FileWriter("UsersData.txt");
            for (int i=0;i<users.size();i++){
                myWriter.write(users.get(i).getId());
                myWriter.write(" ");
                myWriter.write(users.get(i).getPassword());
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully OrdersData saved to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred:(");
            //e.printStackTrace();
            CreateUsersFile();
        }
    }

    private void CreateOrdersFile(String own){
        try {
            File myObj = new File("OrdersData.txt");
            if (myObj.createNewFile()) {
                System.out.println("Unfortunetly there was no file for set Data but now File created: " + myObj.getName());
                if (own.equals("write")) WriteOrders(orders);
                else ReadOrders();
            } else {
                System.out.println("Hey SHIT File already exists !!!!");
            }
        } catch (IOException e) {
            System.out.println("IDK An error occurred.");
            e.printStackTrace();
        }
    }

    private void CreateGoodsFile(String own){
        try {
            File myObj = new File("GoodsData.txt");
            if (myObj.createNewFile()) {
                System.out.println("Unfortunetly there was no file for set Data but now File created: " + myObj.getName());
                if (own.equals("write")) Wirtegoods(goods);
                else ReadGoods();
            } else {
                System.out.println("Hey SHIT File already exists !!!!");
            }
        } catch (IOException e) {
            System.out.println("IDK An error occurred.");
            e.printStackTrace();
        }
    }

    private void CreateUsersFile(){
        try {
            File myObj = new File("UsersData.txt");
            if (myObj.createNewFile()) {
                System.out.println("Unfortunetly there was no file for set Data but now File created: " + myObj.getName());
            } else {
                System.out.println("Hey SHIT File already exists !!!!");
            }
        } catch (IOException e) {
            System.out.println("IDK An error occurred.");
            e.printStackTrace();
        }
    }
}
