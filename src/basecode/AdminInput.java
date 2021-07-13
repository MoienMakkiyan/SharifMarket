package basecode;

import java.util.Scanner;

public class AdminInput {
    private AdminMannager adminMannager;
    private Scanner scanner = new Scanner(System.in);
    private String password = "I love OOP";
    public AdminInput(AdminMannager adminMannager) {
        this.adminMannager = adminMannager;
    }

    public void run(){
        boolean pass=false;
        String input="";
        System.out.println("Admin Panel Stated !");
        boolean AcceptPassword=false;
        while (!AcceptPassword){
            System.out.print("Please Enter the password (if you are TA enter {I love OOP}): ");
            String passs = scanner.nextLine();
            if (passs.equalsIgnoreCase(password)) {
                AcceptPassword=true;
                System.out.println("Password Accepted :)");
            }
            else {
                AcceptPassword=false;
                System.out.println("Invalid Password Please try again ...");
            }
        }
        System.out.println("Now Please Enter Your Commond :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")){
            adminMannager.Load();
            if (input.startsWith("ls -r")) {
                adminMannager.PrintAll();
                pass=true;
            }
            if (input.startsWith("ls -n")) {
                adminMannager.PrintUnavailable();
                pass=true;
            }
            if (input.startsWith("ls -i")) {
                adminMannager.PrintAvailable();
                pass=true;
            }
            if (input.startsWith("ls -o")) {
                adminMannager.PrintUnProcessedOrderList();
                pass=true;
            }
            if (input.startsWith("checkout")) {
                adminMannager.CheckOut(Integer.parseInt(input.substring(10)));
                pass=true;
            }
            if (input.startsWith("ls -ho")){
                adminMannager.PrintOrderList();
                pass=true;
            }
            if (input.startsWith("add")){
                String[] in = input.split("\\s");
                adminMannager.AddGoods(in[2],Integer.parseInt(in[4]),in[5],Integer.parseInt(in[7]),Integer.parseInt(in[9]));
                pass=true;
            }
            if (input.startsWith("remove")){
                String[] in = input.split("\\s");
                adminMannager.RemoveGood(Integer.parseInt(in[2]));
                pass=true;
            }
            if (input.startsWith("edit")){
                String[] in = input.split("\\s");
                if (in.length==4) adminMannager.EditNameGood(Integer.parseInt(in[1]),in[3]);
                if (in.length>=8) adminMannager.EditSellPriceCountGood(Integer.parseInt(in[1]),Integer.parseInt(in[3]),Integer.parseInt(in[5]),Integer.parseInt(in[7]),in[8]);
                if (in.length>=6&&in.length<8) adminMannager.EditNameCountGood(Integer.parseInt(in[1]),in[3],Integer.parseInt(in[5]),in[6]);
                pass=true;
            }
            if (input.startsWith("calc -p")){
                String[] in = input.split("\\s");
                if (in.length==2) adminMannager.AllProfit();
                else adminMannager.GoodProfit(Integer.parseInt(in[3]));
                pass=true;
            }
            if (input.startsWith("calc -s")){
                String[] in = input.split("\\s");
                if (in.length==2) adminMannager.SaleStatistics();
                else adminMannager.EachSaleStatistics(Integer.parseInt(in[3]));
                pass=true;
            }
            if (!pass)System.out.println("Unkonwn Commond please try again ...");
            if (pass) adminMannager.Save();
            input="";
        }
    }
}
