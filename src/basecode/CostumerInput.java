package basecode;

import java.util.Scanner;

public class CostumerInput {
    private CostumerMannager costumerMannager;
    private Scanner scanner = new Scanner(System.in);
    private int ID;

    public CostumerInput(CostumerMannager costumerMannager) {
        this.costumerMannager = costumerMannager;
    }

    public void run() {
        String input="";
        System.out.println("Costumer Panel Stated !");
        while (!(input.equals("exit"))){
            boolean bre=false;
            while (!bre){
                costumerMannager.LoadPass();
                System.out.println("Login part :");
                System.out.println("1- Sign in");
                System.out.println("2- Sign up");
                String a=scanner.nextLine();
                boolean passlogin=false;
                while (!passlogin){
                    if (a.equals("1")){
                        System.out.print("Username : ");
                        String a1=scanner.nextLine();
                        System.out.print("Password : ");
                        String a2=scanner.nextLine();
                        passlogin=costumerMannager.SignIn(a1,a2);
                        if (passlogin) ID=Integer.parseInt(a1);
                        if (passlogin) bre=true;
                    }
                    if (a.equals("2")){
                        System.out.print("Username : ");
                        String aa1=scanner.nextLine();
                        System.out.print("Password : ");
                        String aa2=scanner.nextLine();
                        costumerMannager.SignUp(aa1,aa2);
                        System.out.println("The basecode.User = "+aa1+" registerd successfully !");
                        break;
                    }
                }
                costumerMannager.SaveUser();
            }
            while (!(input = scanner.nextLine()).equalsIgnoreCase("logout")){
                costumerMannager.Load();
                if (input.startsWith("ls -r")) costumerMannager.PrintAll();
                if (input.startsWith("ls -n")) costumerMannager.PrintUnavailable();
                if (input.startsWith("ls -i")) costumerMannager.PrintAvailable();
                if (input.startsWith("order")){
                    String[] in = input.split("\\s");
                    if (in.length==3) costumerMannager.DeleteOrder(Integer.valueOf(in[2]),ID);
                    else costumerMannager.AddOrder(ID,Integer.parseInt(in[1]),Integer.parseInt(in[3]));
                }
                costumerMannager.Save();
            }
        }
    }
}