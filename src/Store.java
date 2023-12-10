import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;

public class Store {
    private Product[] products;
    private int sum = 0;
    private int deletedSum = 0;
    public Store(int number) {
        products = new Product[number];
    }
    private boolean isFull() {
        return sum == products.length;
    }
    private boolean isEmpty() {
        return sum == deletedSum;
    }

    private String deleteProduct(Product product, String productName) {
        if (product == null) {
            return "No milk tea named " + productName + " in the store\n";
        } else if (product.isDeleted()) {
            return "Milk tea named " + productName + " has been deleted\n";
        }
        else {
            product.setDeleted(true);
            deletedSum += 1;
            return "Successfully deleted " + productName + "\n";
        }
    }

    public boolean addProduct(Product product) {
        if (isFull()) {
            return false;
        } else {
            products[sum] = product;
            sum++;
            return true;
        }
    }
    public String listProduct() {
        if (isEmpty()) {
            return "No milk tea in the store.\n";
        } else {
            String listOfProducts = "";
            for (int i = 0; i < sum; i++) {
                if (!products[i].isDeleted())
                    listOfProducts +=  products[i] + "\n";
            }
            return listOfProducts;
        }
    }
    public String listWarehouse() {
        if (isEmpty()) {
            return "No milk tea in the store.\n";
        } else {
            String list = "";
            for (int i = 0; i < sum; i++) {
                if (!products[i].isDeleted() && products[i].isInWarehouse())
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

    public String listProductsContaining(String filterName) {
        if (isEmpty()) {
            return "No milk tea in the store.\n";
        } else {
            String listOfProducts = "";
            for (int i = 0; i < sum; i++) {
                if (!products[i].isDeleted() && products[i].match(filterName))
                    listOfProducts += products[i] + "\n";
            }
            if (listOfProducts.isEmpty()) {
                return "There is not any milk tea contains " + filterName + "\n";
            }
            else {
                return listOfProducts;
            }
        }
    }

    public String deleteProductByName(String productName) {
        if (isEmpty()) {
            return "No milk tea in the store.\n";
        } else {
            Product product = getProductByName(productName);
            return deleteProduct(product, productName);
        }
    }

    private Product getProductByName(String productName) {
        if (isEmpty()) {
            return null;
        } else {
            Product product = null;
            for (int i = 0; i < sum; ++i)
                if (!products[i].isDeleted() && products[i].getName().equals(productName)) {
                    product = products[i];
                    break;
                }
            return product;
        }
    }

    public String updateProduct(String productName, String key, Object value) {
        Product targetProduct = getProductByName(productName);
        if (targetProduct == null) {
            return "There is no milk tea named " + productName + " in this store\n";
        } else {
            String result = "";
            switch (key) {
                case "cost" -> targetProduct.setCost((double) value);
                case "sellingPrice" -> targetProduct.setSellingPrice((double) value);
                case "inWarehouse" -> targetProduct.setInWarehouse((boolean) value);
                default -> result = "There is no milk tea named " + key + " in the store\n";
            }
            return result;
        }
    }

    public void saveToFile(String fileName) throws IOException {
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Cannot create/find file named " + fileName);
            return;
        }
        ArrayList<Product> shouldSaveProducts = new ArrayList<>();
        for (int i = 0; i < sum; ++i) {
            if (!products[i].isDeleted())
                shouldSaveProducts.add(products[i]);
        }
        bufferedWriter.write(JSON.toJSONString(shouldSaveProducts));
        bufferedWriter.close();
    }

    public void clear() {
        for (int i = 0; i < sum; ++i)
            products[i] = null;
        sum = 0;
        deletedSum = 0;
    }


    public void restoreFromFile(String fileName) throws IOException {
        // we just override current states
        clear();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        JSONArray jsonArray = JSON.parseArray(stringBuilder.toString());
        for (int i = 0; i < jsonArray.size(); ++i)
            addProduct(new Product(jsonArray.getJSONObject(i)));
    }

    public String listProfit() {
        if (isEmpty()) {
            return "There is no milk tea in the store\n";
        } else {
            String result = "";
            for (int i = 0; i < sum; ++i) {
                if (!products[i].isDeleted())
                    result += "Name: " + products[i].getName() + ", profit: " + products[i].getProfit() + "\n";
            }
            return result;
        }
    }
}
