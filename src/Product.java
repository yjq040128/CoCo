import com.alibaba.fastjson2.JSONObject;

public class Product {
    private String name = "";
    private double cost = 0;
    private double sellingPrice = 0;
    private boolean inWarehouse = false;

    private boolean isDeleted = false;

    public Product(String name, double cost, double sellingPrice, boolean inWarehouse) {
        this.name = name;
        this.cost = cost;
        this.sellingPrice = sellingPrice;
        this.inWarehouse = inWarehouse;
        this.isDeleted = false;
    }
    public Product(JSONObject jsonObject) {
        this.name = jsonObject.getString("name");
        this.cost = jsonObject.getDouble("cost");
        this.sellingPrice = jsonObject.getDouble("sellingPrice");
        this.inWarehouse = jsonObject.getBoolean("inWarehouse");
        this.isDeleted = false;
    }


    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public boolean isInWarehouse() {
        return inWarehouse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setInWarehouse(boolean inWarehouse) {this.inWarehouse = inWarehouse;}

    public double getProfit() { return sellingPrice - cost; }

    public boolean match(String filter) {
        return this.name.contains(filter);
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String toString() {
        return "Milk tea name: " + name
                + ", Cost: " + cost
                + ", Selling price: " + sellingPrice
                + ", Whether in the warehouse: " + inWarehouse;
    }
}