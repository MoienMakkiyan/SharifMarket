public class Order {
    private int costumerID;
    private int orderID;
    private int SellPrice;
    private int BuyPrice;
    private String Date;
    private int goodID;
    private int inventory;
    private boolean done;

    public Order(int costumerID, String date, int goodID, int inventory, int orderID, int sellPrice, int buyPrice) {
        this.costumerID = costumerID;
        this.Date = date;
        this.goodID = goodID;
        this.inventory = inventory;
        this.orderID=orderID;
        this.done=false;
        this.SellPrice = sellPrice;
        this.BuyPrice = buyPrice;
    }

    public Order(int costumerID, int orderID, int sellPrice, int buyPrice, String date, int goodID, int inventory, boolean done) {
        this.costumerID = costumerID;
        this.orderID = orderID;
        SellPrice = sellPrice;
        BuyPrice = buyPrice;
        Date = date;
        this.goodID = goodID;
        this.inventory = inventory;
        this.done = done;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getGoodID() {
        return goodID;
    }

    public int getInventory() {
        return inventory;
    }

    public int getSellPrice() {
        return SellPrice;
    }

    public int getBuyPrice() {
        return BuyPrice;
    }

    public int getCostumerID() {
        return costumerID;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDate() {
        return Date;
    }
}
