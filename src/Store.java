public class Store {
    private Product[] products;
    private int sum = 0;
    public Store(int number) {
        products = new Product[number];
    }
    private boolean isFull() {
        return sum == products.length;
    }
    private boolean isEmpty() {
        return sum == 0;
    }

    public boolean add(Product product) {
        if (isFull()) {
            return false;
        } else {
            products[sum] = product;
            sum++;
            return true;
        }
    }
    public String List() {
        if (isEmpty()) {
            return "No milk tea in the store.";
        } else {
            String list = "";
            for (int i = 0; i < sum; i++) {
                list +=  products[i] + "\n";
            }
            return list;
        }
    }
    public String listWarehouse() {
        if (isEmpty()) {
            return "No milk tea in the store.";
        } else {
            String list = "";
            for (int i = 0; i < sum; i++) {
                if (products[i].isInWarehouse())
                    list += products[i] + "\n";
            }
            if (list.isEmpty()){
                return "There is no milk tea left.";
            }
            else{
                return list;
            }
        }
    }

}
