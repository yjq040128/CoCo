public class Product {
    private String name = "";
    private double cost = 0;
    private double sellingPrice = 0;
    private boolean inWarehouse = false;

    public Product(String name, double cost, double sellingPrice, boolean inWarehouse) {
        this.name = name;
        this.cost = cost;
        this.sellingPrice = sellingPrice;
        this.inWarehouse = inWarehouse;
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

    public String toString() {
        return "Milk tea name: " + name
                + ", Cost: " + cost
                + ", Selling price: " + sellingPrice
                + ", Whether in the warehouse: " + inWarehouse;
    }
}