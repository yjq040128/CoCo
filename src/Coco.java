import java.io.IOException;
import java.util.Scanner;

public class Coco {
    private Scanner input = new Scanner(System.in);
    private Store store = null;

    Coco() {
        store = new Store(1000);
        while (true) {
            menu();
            int opt = input.nextInt();
            boolean shouldExit = false;
            switch (opt) {
                case 1 -> addDriver();
                case 2 -> updateDriver();
                case 3 -> deleteDriver();
                case 4 -> listDriver();
                case 5 -> calculateDriver();
                case 6 -> saveDriver();
                case 7 -> restoreDriver();
                case 8 -> shouldExit = true;
                default -> System.out.println("No helper for opt " + opt);
            }
            if (shouldExit)
                break;
        }
    }

    public void menu() {
        System.out.println("=========== Coco ===========");
        System.out.println("1. Add a new milk tea");
        System.out.println("2. Update a milk tea by name");
        System.out.println("3. Delete a milk tea by name");
        System.out.println("4. List milk tea by various properties");
        System.out.println("5. Calculate profit");
        System.out.println("6. Save current states to file");
        System.out.println("7. Restore store from file");
        System.out.println("8. Exit");
    }

    public boolean parseBooleanInput(String msg) {
        System.out.println(msg);
        String userInput = input.nextLine();
        boolean result;
        while (true) {
            if (userInput.equalsIgnoreCase("y")) {
                result = true;
                break;
            } else if (userInput.equalsIgnoreCase("n")) {
                result = false;
                break;
            } else {
                System.out.println("Invalid input, please try it again");
                System.out.println(msg);
                userInput = input.nextLine();
            }
        }
        return result;
    }

    public void addDriver() {
        input.nextLine();
        System.out.println("Input the name of this milk tea");
        String name = input.nextLine();
        System.out.println("Input the cost of this milk tea");
        double cost = input.nextDouble();
        System.out.println("Input the sellingPrice of this milk tea");
        double sellingPrice = input.nextDouble();
        input.nextLine();
        boolean isInWarehouse = parseBooleanInput("Input whether this milk tea is in warehouse [Y/n]");
        Product product = new Product(name, cost, sellingPrice, isInWarehouse);
        boolean result = store.addProduct(product);
        if (result)
            System.out.println("Successfully add product " + product);
        else
            System.out.println("Cannot add this product to store, the store is full now");
    }

    public void updateDriver() {
        input.nextLine();
        System.out.println("Input the name of target milk tea");
        String productName = input.nextLine();
        System.out.println("Input the key you want to modify");
        String key = input.nextLine();
        switch (key) {
            case "cost", "sellingPrice" -> {
                System.out.println("Input the value you want to modify");
                double updateValue = input.nextDouble();
                String result = store.updateProduct(productName, key, updateValue);
                System.out.println(result);
            }
            case "inWarehouse" -> {
                boolean inWarehouse = parseBooleanInput("Input the value you want to modify[Y/n]");
                String result = store.updateProduct(productName, key, inWarehouse);
                System.out.println(result);
            }
            default -> System.out.println("Unsupported key: " + key);
        }
    }

    public void deleteDriver() {
        input.nextLine();
        System.out.println("Input the name of the target milk tea");
        String productName = input.nextLine();
        String result = store.deleteProductByName(productName);
        if (!result.isEmpty())
            System.out.println(result);
    }

    public void listDriver() {
        while (true) {
            System.out.println("List Choices:");
            System.out.println("1. List all products");
            System.out.println("2. List products that in warehouse");
            System.out.println("3. List products that contains some string");
            int opt = input.nextInt();
            boolean shouldExit = true;
            switch (opt) {
                case 1 -> System.out.println(store.listProduct());
                case 2 -> System.out.println(store.listWarehouse());
                case 3 -> {
                    input.nextLine();
                    System.out.println("Input the string that you want to query");
                    String filterName = input.nextLine();
                    System.out.println(store.listProductsContaining(filterName));
                }
                default -> {
                    System.out.println("Invalid opt: " + opt + ", try it again.");
                    shouldExit = false;
                }
            }
            if (shouldExit)
                break;
        }
    }

    public void calculateDriver() {
        System.out.println(store.listProfit());
    }

    public void saveDriver() {
        input.nextLine();
        System.out.println("Input output filename");
        String filename = input.nextLine();
        try {
            store.saveToFile(filename);
        } catch (IOException e) {
            System.out.println("Cannot save to file: " + filename);
        }
    }

    public void restoreDriver() {
        input.nextLine();
        System.out.println("Input input filename");
        String filename = input.nextLine();
        try {
            store.restoreFromFile(filename);
            System.out.println("Current Store: \n");
            System.out.println(store.listProduct());
        } catch (IOException e) {
            System.out.println("Cannot restore from file: " + filename);
        }
    }

    public static void main(String[] args) {
        new Coco();
    }
}
