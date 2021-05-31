import java.util.ArrayList;

public class SharedLibrary {
    public SharedLibrary() {
    }

    public void Print_ALL_goods(ArrayList<Good> goods){
        System.out.println("+-----------------+---------+------------+------------+");
        System.out.println("| Good name       | GoodID  | Inventory  | Price(IRR) |");
        System.out.println("+-----------------+---------+------------+------------+");
        for (int i=0;i<goods.size();i++){
            System.out.print("| ");
            System.out.print(goods.get(i).getName());
            int a = 15 - goods.get(i).getName().length();
            while (a >= 0){
                System.out.print(" ");
                a--;
            }
            System.out.print("| ");
            System.out.print(goods.get(i).getId());
            System.out.print("  ");
            System.out.print("| ");
            System.out.print(goods.get(i).getCurrent_inventory());
            System.out.print(" ");
            System.out.print(goods.get(i).getMDFR());
            int aa = 9 - (String.valueOf(goods.get(i).getCurrent_inventory()).length() + goods.get(i).getMDFR().length());
            while (aa >= 0){
                System.out.print(" ");
                aa--;
            }
            System.out.print("| ");
            System.out.print(goods.get(i).getSelling_price());
            int aaa = 10 - String.valueOf(goods.get(i).getSelling_price()).length();
            while (aaa >= 0){
                System.out.print(" ");
                aaa--;
            }
            System.out.print("|");
            System.out.print("\n");
        }
        System.out.println("+-----------------+---------+------------+------------+");
    }

    public void Print_Available_goods(ArrayList<Good> goods){
        System.out.println("+-----------------+---------+------------+------------+");
        System.out.println("| Good name       | GoodID  | Inventory  | Price(IRR) |");
        System.out.println("+-----------------+---------+------------+------------+");
        for (int i=0;i<goods.size();i++) {
            if (goods.get(i).getCurrent_inventory()>0) {
                System.out.print("| ");
                System.out.print(goods.get(i).getName());
                int a = 15 - goods.get(i).getName().length();
                while (a >= 0){
                    System.out.print(" ");
                    a--;
                }
                System.out.print("| ");
                System.out.print(goods.get(i).getId());
                System.out.print("  ");
                System.out.print("| ");
                System.out.print(goods.get(i).getCurrent_inventory());
                System.out.print(" ");
                System.out.print(goods.get(i).getMDFR());
                int aa = 9 - (String.valueOf(goods.get(i).getCurrent_inventory()).length() + goods.get(i).getMDFR().length());
                while (aa >= 0){
                    System.out.print(" ");
                    aa--;
                }
                System.out.print("| ");
                System.out.print(goods.get(i).getSelling_price());
                int aaa = 10 - String.valueOf(goods.get(i).getSelling_price()).length();
                while (aaa >= 0){
                    System.out.print(" ");
                    aaa--;
                }
                System.out.print("|");
                System.out.print("\n");
            }
        }
        System.out.println("+-----------------+---------+------------+------------+");
    }

    public void Print_Unavailable_goods(ArrayList<Good> goods){
        System.out.println("+-----------------+---------+------------+------------+");
        System.out.println("| Good name       | GoodID  | Inventory  | Price(IRR) |");
        System.out.println("+-----------------+---------+------------+------------+");
        for (int i=0;i<goods.size();i++) {
            if (goods.get(i).getCurrent_inventory()==0) {
                System.out.print("| ");
                System.out.print(goods.get(i).getName());
                int a = 15 - goods.get(i).getName().length();
                while (a >= 0){
                    System.out.print(" ");
                    a--;
                }
                System.out.print("| ");
                System.out.print(goods.get(i).getId());
                System.out.print("  ");
                System.out.print("| ");
                System.out.print(goods.get(i).getCurrent_inventory());
                System.out.print(" ");
                System.out.print(goods.get(i).getMDFR());
                int aa = 9 - (String.valueOf(goods.get(i).getCurrent_inventory()).length() + goods.get(i).getMDFR().length());
                while (aa >= 0){
                    System.out.print(" ");
                    aa--;
                }
                System.out.print("| ");
                System.out.print(goods.get(i).getSelling_price());
                int aaa = 10 - String.valueOf(goods.get(i).getSelling_price()).length();
                while (aaa >= 0){
                    System.out.print(" ");
                    aaa--;
                }
                System.out.print("|");
                System.out.print("\n");
            }
        }
        System.out.println("+-----------------+---------+------------+------------+");
    }
}
