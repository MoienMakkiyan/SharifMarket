package basecode;

import java.lang.ref.PhantomReference;

public class Good {
    private String name;
    private int current_inventory;
    private int selling_price;
    private int buying_price;
    private int id;
    private int sellingUntilNow=0;
    private int buyingUntilNow=0;
    private int profit;
    private String MDFR;

    public Good(String name, int current_inventory, String MDFR, int selling_price, int buying_price, int id) {
        this.name = name;
        this.current_inventory = current_inventory;
        this.selling_price = selling_price;
        this.buying_price = buying_price;
        this.id = id;
        this.MDFR=MDFR;
        this.sellingUntilNow=0;
        this.buyingUntilNow=0;
    }

    public Good(String name, int current_inventory, int selling_price, int buying_price, int id, int sellingUntilNow, int buyingUntilNow, String MDFR) {
        this.name = name;
        this.current_inventory = current_inventory;
        this.selling_price = selling_price;
        this.buying_price = buying_price;
        this.id = id;
        this.sellingUntilNow = sellingUntilNow;
        this.buyingUntilNow = buyingUntilNow;
        this.MDFR = MDFR;
    }

    public String getName() {
        return name;
    }

    public int getCurrent_inventory() {
        return current_inventory;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public int getBuying_price() {
        return buying_price;
    }

    public int getId() {
        return id;
    }

    public int getSellingUntilNow() {
        return sellingUntilNow;
    }

    public int getBuyingUntilNow() {
        return buyingUntilNow;
    }

    public String getMDFR() {
        return MDFR;
    }

    public void setCurrent_inventory(int current_inventory) {
        this.current_inventory = current_inventory;
    }

    public void setSellingUntilNow(int sellingUntilNow) {
        this.sellingUntilNow = sellingUntilNow;
    }

    public void setBuyingUntilNow(int buyingUntilNow) {
        this.buyingUntilNow = buyingUntilNow;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public void setBuying_price(int buying_price) {
        this.buying_price = buying_price;
    }

    public void setMDFR(String MDFR) {
        this.MDFR = MDFR;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit() {
        this.profit = sellingUntilNow-buyingUntilNow;
    }
}
