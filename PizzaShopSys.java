import java.time.LocalDate;
import java.util.*;

class PizzaShopSys {

    List<Customer> customerList;
    List<Pizza> pizzaList;
    List<Order> orderList;
    List<Topping> toppingList;
    List<PromoCode> promoCodes;

    public PizzaShopSys() {
        customerList = new ArrayList<>();
        pizzaList = new ArrayList<>();
        orderList = new ArrayList<>();
        toppingList = new ArrayList<>();
        promoCodes = new ArrayList<>();
        initializeToppingMenu();
        initializePromoCodes();
    }

    public void initializePizzas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================");
        System.out.println("         Pizza Menu Setup      ");
        System.out.println("==============================");
        System.out.print("Do you want to initialize the default pizza menu? (Y/N): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("y")) {
            pizzaList.add(new Pizza(1, "Margherita", "Classic cheese pizza", 800));
            pizzaList.add(new Pizza(2, "Pepperoni", "Spicy pepperoni with cheese", 1200));
            pizzaList.add(new Pizza(3, "Veggie", "Loaded with fresh vegetables", 1000));
            System.out.println("\n*** Default pizzas initialized successfully! ***");
        } else {
            System.out.println("\n*** Pizza menu initialization skipped. ***");
        }
    }

    public void initializeToppingMenu() {
        // Crust Types
        toppingList.add(new Topping(1, "Thin Crust", 50));
        toppingList.add(new Topping(2, "Thick Crust", 70));
        toppingList.add(new Topping(3, "Cheese Stuffed Crust", 100));

        // Sauces
        toppingList.add(new Topping(4, "Tomato Sauce", 30));
        toppingList.add(new Topping(5, "Pesto Sauce", 50));
        toppingList.add(new Topping(6, "Barbecue Sauce", 40));

        // Toppings
        toppingList.add(new Topping(7, "Cheese", 50));
        toppingList.add(new Topping(8, "Olives", 40));
        toppingList.add(new Topping(9, "Mushrooms", 60));
        toppingList.add(new Topping(10, "Pepperoni", 80));
        toppingList.add(new Topping(11, "Onions", 30));
    }

    public void initializePromoCodes() {
        promoCodes = new ArrayList<>();
        promoCodes.add(new PromoCode("SAVE50", 50, LocalDate.of(2025, 01, 31), "Save LKR 50 on your order."));
        promoCodes.add(new PromoCode("SAVE75", 75, LocalDate.of(2024, 12, 31), "Save LKR 75 on your order."));
        promoCodes.add(new PromoCode("SAVE100", 100, LocalDate.of(2024, 12, 31), "Save LKR 100 on your order."));
    }

    public void displayPizzas() {
        System.out.println("\n==============================");
        System.out.println("         List of Pizzas        ");
        System.out.println("==============================");
        for (Pizza pizza : pizzaList) {
            pizza.displayPizza();
        }
        System.out.println("==============================\n");
    }

    public void addPizza() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================");
        System.out.println("      Add a New Pizza         ");
        System.out.println("==============================");
        System.out.print("Enter Pizza Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Pizza Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Pizza Price: ");
        int price = scanner.nextInt();
        int id = pizzaList.size() + 1;
        pizzaList.add(new Pizza(id, name, description, price));
        System.out.println("\n*** New Pizza Added Successfully! ***\n");
    }

    public void viewPizza() {
        System.out.println("\n==============================");
        System.out.println("         Available Pizzas      ");
        System.out.println("==============================");
        for (Pizza pizza : pizzaList) {
            System.out.println("ID: " + pizza.getId());
            System.out.println("Name: " + pizza.getName());
            System.out.println("Description: " + pizza.getDescription());
            System.out.println("Price: LKR " + pizza.getPrice());
            System.out.println("-------------------------------");
        }
        System.out.println("==============================\n");
    }

    public void updatePizzaDetails() {
        viewPizza(); // Display all pizzas before updating
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n==============================");
        System.out.println("      Update Pizza Details     ");
        System.out.println("==============================");
        System.out.print("Enter Pizza ID to Update: ");
        int pizzaId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        Pizza pizza = getPizzaById(pizzaId);

        if (pizza == null) {
            System.out.println("Invalid Pizza ID.\n");
            return;
        }

        System.out.print("What do you want to update? (Name/Description/Price): ");
        String fieldToUpdate = scanner.nextLine().toLowerCase();

        switch (fieldToUpdate) {
            case "name":
                System.out.print("Enter New Pizza Name: ");
                pizza.setName(scanner.nextLine());
                System.out.println("\n*** Pizza Name Updated Successfully! ***\n");
                break;
            case "description":
                System.out.print("Enter New Pizza Description: ");
                pizza.setDescription(scanner.nextLine());
                System.out.println("\n*** Pizza Description Updated Successfully! ***\n");
                break;
            case "price":
                System.out.print("Enter New Pizza Price: ");
                int newPrice = scanner.nextInt();
                pizza.setPrice(newPrice);
                System.out.println("\n*** Pizza Price Updated Successfully! ***\n");
                break;
            default:
                System.out.println("Invalid field to update.\n");
        }
    }

    public void displayToppings() {
        System.out.println("\n==============================");
        System.out.println("        List of Features      ");
        System.out.println("==============================");

        // Crust Types
        System.out.println("Crust Types:");
        for (Topping topping : toppingList) {
            if (topping.getId() >= 1 && topping.getId() <= 3) { // IDs for crust types
                System.out.println(topping.getId() + " - " + topping.getName() + " (LKR " + topping.getPrice() + ")");
            }
        }

        // Sauces
        System.out.println("\nSauces:");
        for (Topping topping : toppingList) {
            if (topping.getId() >= 4 && topping.getId() <= 6) { // IDs for sauces
                System.out.println(topping.getId() + " - " + topping.getName() + " (LKR " + topping.getPrice() + ")");
            }
        }

        // Toppings
        System.out.println("\nToppings:");
        for (Topping topping : toppingList) {
            if (topping.getId() >= 7 && topping.getId() <= 11) { // IDs for other toppings
                System.out.println(topping.getId() + " - " + topping.getName() + " (LKR " + topping.getPrice() + ")");
            }
        }

        System.out.println("==============================\n");
    }

    public void registerCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================");
        System.out.println("       Register Customer       ");
        System.out.println("==============================");
        System.out.print("Enter Customer Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(phone, name, address, phone, email);
        customerList.add(customer);

        System.out.println("\n*** Customer Registered Successfully! ***\n");
    }

    public void displayCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("\n*** No Customers Found! ***\n");
            return;
        }
        System.out.println("\n==============================");
        System.out.println("       List of Customers       ");
        System.out.println("==============================");
        for (Customer customer : customerList) {
            customer.showDetails();
            System.out.println("Star Points: " + customer.getStarPoints());
            System.out.println("-------------------------------");
        }
        System.out.println("==============================\n");
    }

    public void placeOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================");
        System.out.println("         Place an Order        ");
        System.out.println("==============================");
        System.out.print("Enter Customer Phone Number: ");
        String phone = scanner.nextLine();

        // Fetch customer by phone
        Customer customer = getCustomerByPhone(phone);
        if (customer == null) {
            System.out.println("\n*** Customer Not Found! Please Register the Customer First. ***\n");
            return;
        }

        // Display available pizzas
        displayPizzas();
        System.out.print("Enter Pizza ID to Order: ");
        int pizzaId = scanner.nextInt();
        Pizza pizza = getPizzaById(pizzaId);
        if (pizza == null) {
            System.out.println("\n*** Invalid Pizza ID. ***\n");
            return;
        }

        // Create a new order
        Order order = new Order(orderList.size() + 1, phone, pizza.getName(), pizza.getPrice(), LocalDate.now());

        // Display available toppings
        System.out.println("\nAvailable Toppings:");
        displayToppings();

        // Add toppings to the order
        while (true) {
            System.out.print("Enter Features ID to Add (or 0 to Finish): ");
            int toppingId = scanner.nextInt();
            if (toppingId == 0) {
                break; // Stop adding toppings
            }
            Topping topping = getToppingById(toppingId);
            if (topping != null) {
                order.addTopping(topping.getName());
                order.addToppingCost(topping.getPrice());
            } else {
                System.out.println("\n*** Invalid Topping ID. ***\n");
            }
        }

        // Prompt to confirm the order
        System.out.println("\nOrder Summary:");
        order.displayOrder(); // Display the current order details
        System.out.print("Order Confirm? (y/n): ");
        scanner.nextLine(); // Consume leftover newline
        String confirmOrder = scanner.nextLine().trim();

        if (confirmOrder.equalsIgnoreCase("y")) {
            // Add the order to the order list
            orderList.add(order);
            System.out.println("\n*** Order Confirmed and Added Successfully! ***\n");
        } else {
            System.out.println("\n*** Order Canceled. ***\n");
            return; // Exit the method as the order is not confirmed
        }

        System.out.print("Would you like to save this combination for future orders? (y/n): ");
        String saveResponse = scanner.next().trim().toLowerCase(); // Read input directly and normalize to lowercase

        if (saveResponse.equals("y")) {
            scanner.nextLine(); // Consume leftover newline
            System.out.print("Enter a unique name for this combination: ");
            String uniqueName = scanner.nextLine().trim();

            // Check if the unique name is already used
            if (customer.getSavedPizzas().containsKey(uniqueName)) {
                System.out.println("\n*** A combination with this name already exists. Please choose another name. ***");
            } else {
                // Save the custom pizza
                String pizzaDetails = "Pizza: " + pizza.getName()
                        + ", Toppings: " + String.join(", ", order.getToppings())
                        + ", Total Cost: LKR " + order.getFinalAmount();
                customer.saveCustomPizza(uniqueName, pizzaDetails);
                System.out.println("\n*** Custom Pizza Saved Successfully! ***");
            }
        } else if (saveResponse.equals("n")) {
            System.out.println("*** Custom Pizza not saved. ***");
        } else {
            System.out.println("*** Invalid Input! Custom Pizza not saved. ***");
        }

        // Prompt to apply promo code
        System.out.println("\n==================================");
        System.out.println("        Apply a Promo Code         ");
        System.out.println("==================================");
        System.out.print("Do you have a promo code? (y/n): ");
        String promoResponse = scanner.nextLine().trim(); // Ensure scanner picks up input correctly

        if (promoResponse.equalsIgnoreCase("y")) {
            System.out.print("Enter Promo Code: ");
            String enteredCode = scanner.nextLine().trim();
            PromoCode validPromo = null;
            for (PromoCode promo : promoCodes) {
                if (promo.getCode().equalsIgnoreCase(enteredCode) && !promo.isExpired()) {
                    validPromo = promo;
                    break;
                }
            }

            if (validPromo != null) {
                System.out.println("\n*** Promo Code Applied: " + validPromo.getDescription() + " ***");
                order.setDiscount(validPromo.getDiscount());
            } else {
                System.out.println("\n*** Invalid or Expired Promo Code. ***");
            }
        } else if (promoResponse.equalsIgnoreCase("n")) {
            System.out.println("*** No Promo Code Applied. ***");
        } else {
            System.out.println("*** Invalid Input! Skipping Promo Code Section. ***");
        }

        // Star points usage
        System.out.println("\n==================================");
        System.out.println("        Redeem Star Points         ");
        System.out.println("==================================");
        int pointsUsed = 0; // Declare pointsUsed within the scope
        int customerPoints = customer.getStarPoints();
        System.out.println("You have " + customerPoints + " Star Points available.");

        System.out.print("Would you like to redeem Star Points? (y/n): ");
        String usePointsResponse = scanner.nextLine().trim();

        if (usePointsResponse.equalsIgnoreCase("y")) {
            if (customerPoints >= 100) {
                System.out.print("Do you want to use 100 Star Points for LKR 100 discount? (y/n): ");
                String confirmPointsUse = scanner.nextLine().trim();
                if (confirmPointsUse.equalsIgnoreCase("y")) {
                    pointsUsed = 100;
                    customer.setStarPoints(customerPoints - pointsUsed);
                    order.setDiscount(order.getDiscount() + 100);
                    System.out.println("\n*** 100 Star Points Applied! LKR 100 Discount Added. ***");
                } else {
                    System.out.println("\n*** No Star Points Applied. ***");
                }
            } else {
                System.out.println("\n*** Not Enough Star Points to Redeem a Discount. ***");
            }
        } else {
            System.out.println("*** No Star Points Redemption Requested. ***");
        }

        // Order Type
        System.out.println("\n==================================");
        System.out.println("         Select Order Type         ");
        System.out.println("==================================");
        System.out.print("Enter Order Type (1 for Delivery, 2 for Takeaway): ");
        int orderTypeChoice = scanner.nextInt();
        scanner.nextLine();
        String orderType = orderTypeChoice == 1 ? "Delivery" : "Takeaway";
        int deliveryFee = orderType.equals("Delivery") ? 350 : 0;

        int totalOrderCost = order.getFinalAmount() + deliveryFee;
        order.setDeliveryFee(deliveryFee);

        System.out.println("\n==================================");
        System.out.println("          Order Summary           ");
        System.out.println("==================================");
        System.out.println("Order Type: " + orderType);
        System.out.println("Delivery Fee: LKR " + deliveryFee);
        System.out.println("Total Order Cost: LKR " + totalOrderCost);
        System.out.println("==================================\n");

        System.out.println("*** Order Placed Successfully! Proceeding to Payment... ***");

        // Process payment
        processPayment(customer, order, pointsUsed);

        // Award Star Points
        if (order.getFinalAmount() >= 1000) {
            customer.addStarPoints(25);
            System.out.println("\n==================================");
            System.out.println("   Congratulations! You Earned   ");
            System.out.println("          25 Star Points!         ");
            System.out.println("==================================");
        }

        // Rating the Order
        System.out.println("\n==================================");
        System.out.println("      Please Rate Your Order");
        System.out.println("==================================");
        System.out.println("1 - Poor");
        System.out.println("2 - Average");
        System.out.println("3 - Good");
        System.out.println("4 - Very Good");
        System.out.println("5 - Excellent");
        System.out.print("Rate (1-5): ");
        int rating = 0;

        while (true) {
            if (scanner.hasNextInt()) { // Check for valid integer input
                rating = scanner.nextInt();
                if (rating >= 1 && rating <= 5) {
                    String ratingDescription = switch (rating) {
                        case 1 ->
                            "Poor";
                        case 2 ->
                            "Average";
                        case 3 ->
                            "Good";
                        case 4 ->
                            "Very Good";
                        case 5 ->
                            "Excellent";
                        default ->
                            "";
                    };
                    System.out.println("\n==================================");
                    System.out.println("*** Thank You for Your Feedback! ***");
                    System.out.println("*** You Rated the Order as: " + ratingDescription + " ***");
                    System.out.println("==================================");
                    order.setRating(rating); // Set the numeric rating for internal use
                    break; // Valid rating entered
                } else {
                    System.out.println("*** Invalid Rating! Please Enter a Number Between 1 and 5. ***");
                    scanner.next(); // Consume invalid input
                }
            } else {
                System.out.println("*** Invalid Input! Please Enter a Number Between 1 and 5. ***");
                scanner.next(); // Consume invalid input
            }
        }
    }

    public void processPayment(Customer customer, Order order, int pointsUsed) {
        Scanner scanner = new Scanner(System.in);

        int totalCost = order.getFinalAmount();

        // Payment Integration
        System.out.println("Select Payment Method:");
        System.out.println("1 - Credit Card");
        System.out.println("2 - Digital Wallet");
        System.out.println("3 - Cash on Delivery");
        System.out.print("Enter Choice: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        PaymentStrategy payment;
        String paymentType = "";
        if (paymentChoice == 1) {
            System.out.print("Enter Credit Card Number: ");
            String cardNumber = scanner.nextLine();
            payment = new CreditCardPayment(cardNumber);
            paymentType = "Credit Card";
        } else if (paymentChoice == 2) {
            System.out.print("Enter Digital Wallet ID: ");
            String walletId = scanner.nextLine();
            payment = new DigitalWalletPayment(walletId);
            paymentType = "Digital Wallet";
        } else if (paymentChoice == 3) {
            payment = new CashOnDeliveryPayment();
            paymentType = "Cash on Delivery";
        } else {
            System.out.println("Invalid payment method selected. Payment aborted.");
            return;
        }

        payment.pay(totalCost); // Polymorphic call

        // Generate Invoice
        System.out.println("===============================");
        System.out.println("            INVOICE            ");
        System.out.println("===============================");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer Phone: " + order.getCustomerId());
        System.out.println("Pizza: " + order.getPizzaName());
        System.out.println("Base Cost: LKR " + order.getBaseCost());
        System.out.println("Toppings: " + String.join(", ", order.getToppings())
                + " (LKR " + order.getToppingsCost() + ")");
        System.out.println("Discount: LKR " + order.getDiscount());
        System.out.println("Star Points Used: " + pointsUsed);
        System.out.println("Delivery Fee: LKR " + order.getDeliveryFee());
        System.out.println("Total: LKR " + order.getFinalAmount());
        System.out.println("Payment Method: " + paymentType);
        System.out.println("===============================");
        System.out.println("Thank you for your payment!");
        System.out.println("===============================");
    }

    public void viewOrders() {
        if (orderList.isEmpty()) {
            System.out.println("\n*** No Orders Found! ***\n");
            return;
        }
        System.out.println("\n==============================");
        System.out.println("        List of Orders         ");
        System.out.println("==============================");
        for (Order order : orderList) {
            order.displayOrder();
            System.out.println("Rating: " + (order.getRating() > 0 ? order.getRating() : "Not Rated Yet"));
            System.out.println("-------------------------------");
        }
        System.out.println("==============================\n");
    }

    public void viewSavedPizzas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================");
        System.out.println("      View Saved Pizzas       ");
        System.out.println("==============================");
        System.out.print("Enter Customer Phone Number: ");
        String phone = scanner.nextLine();

        Customer customer = getCustomerByPhone(phone);
        if (customer == null) {
            System.out.println("\n*** Customer Not Found! ***\n");
            return;
        }

        customer.showSavedPizzas();

        System.out.print("Would you like to reorder any saved combination? (y/n): ");
        String reorderResponse = scanner.nextLine();
        if (reorderResponse.equalsIgnoreCase("Yes")) {
            System.out.print("Enter the name of the saved combination to reorder: ");
            String pizzaName = scanner.nextLine();
            String savedPizzaDetails = customer.getSavedPizzas().get(pizzaName);
            if (savedPizzaDetails != null) {
                System.out.println("\n==============================");
                System.out.println("    Reordering Saved Pizza    ");
                System.out.println("==============================");
                System.out.println(savedPizzaDetails);
                System.out.println("*** Order Placed Successfully! ***\n");
            } else {
                System.out.println("\n*** No Saved Combination Found with That Name! ***\n");
            }
        }
    }

    public void updateOrderState() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================");
        System.out.println("      Update Order State      ");
        System.out.println("==============================");
        System.out.print("Enter Order ID to Update State: ");
        int orderId = scanner.nextInt();

        Order order = null;
        for (Order o : orderList) {
            if (o.getOrderId() == orderId) {
                order = o;
                break;
            }
        }

        if (order == null) {
            System.out.println("\n*** Order Not Found! ***\n");
            return;
        }

        // Add observer for the customer associated with the order
        OrderContext orderContext = order.getOrderContext(); // Assuming getOrderContext() returns the context
        Customer customer = getCustomerByPhone(order.getCustomerId());
        if (customer != null) {
            orderContext.addObserver(new CustomerObserver(customer));
        }

        System.out.println("\nCurrent Order Status:");
        order.printStatus();

        System.out.println("\nChoose an Option:");
        System.out.println("1 - Move to Next State");
        System.out.println("2 - Move to Previous State");
        System.out.print("Enter Choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            order.nextState();
            System.out.println("\n*** Order State Updated to Next Stage! ***\n");
        } else if (choice == 2) {
            order.previousState();
            System.out.println("\n*** Order State Reverted to Previous Stage! ***\n");
        } else {
            System.out.println("\n*** Invalid Choice. ***\n");
        }
    }

    public void viewOrderStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================");
        System.out.println("       View Order Status       ");
        System.out.println("==============================");
        System.out.print("Enter Customer Phone Number: ");
        String phone = scanner.nextLine();

        Customer customer = getCustomerByPhone(phone);
        if (customer == null) {
            System.out.println("\n*** Customer Not Found! ***\n");
            return;
        }

        boolean hasOrders = false;
        for (Order order : orderList) {
            if (order.getCustomerId().equals(phone)) {
                hasOrders = true;
                System.out.println("\nOrder ID: " + order.getOrderId());
                System.out.println("Pizza: " + order.getPizzaName());
                System.out.println("Order Status:");
                order.printStatus();
                System.out.println("-----------------------------");
            }
        }

        if (!hasOrders) {
            System.out.println("\n*** No Orders Found for This Customer. ***\n");
        }
    }

    // Helper Methods
    public Pizza getPizzaById(int id) {
        for (Pizza pizza : pizzaList) {
            if (pizza.getId() == id) {
                return pizza;
            }
        }
        return null;
    }

    public Topping getToppingById(int id) {
        for (Topping topping : toppingList) {
            if (topping.getId() == id) {
                return topping;
            }
        }
        return null;
    }

    public Customer getCustomerByPhone(String phone) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(phone)) {
                return customer;
            }
        }
        return null;
    }

    private boolean authenticateStaff() {
        Scanner scanner = new Scanner(System.in);
        String hardcodedUsername = "admin"; // Hardcoded Username
        String hardcodedPassword = "pass123"; // Hardcoded Password

        System.out.print("\nEnter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Check if the input matches the hardcoded credentials
        if (username.equals(hardcodedUsername) && password.equals(hardcodedPassword)) {
            System.out.println("*** Authentication Successful! Welcome, Staff. ***");
            return true;
        } else {
            System.out.println("*** Invalid Username or Password! ***");
            return false;
        }
    }

    public static void main(String[] args) {
        PizzaShopSys ps = new PizzaShopSys();
        ps.mainMenu();
    }

    // Main Menu
    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n==============================");
            System.out.println("       Pizza Shop System       ");
            System.out.println("==============================");
            System.out.println("1 - Customer Management");
            System.out.println("2 - Staff Management");
            System.out.println("3 - Exit");
            System.out.println("==============================");
            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    customerManagementMenu();
                    break;
                case 2:
                    if (authenticateStaff()) { // Authenticate Staff
                        staffManagementMenu();
                    } else {
                        System.out.println("*** Authentication Failed! Returning to Main Menu. ***");
                    }
                    break;
                case 3:
                    System.out.println("\n*** Thank you for using the Pizza Shop System! ***\n");
                    return;
                default:
                    System.out.println("Invalid Option. Please Try Again.\n");
            }
        }
    }

    public void customerManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n==============================");
            System.out.println("      Customer Dashboard     ");
            System.out.println("==============================");
            System.out.println("1 - Initialize Available Pizzas");
            System.out.println("2 - Register Customer");
            System.out.println("3 - Place Order");
            System.out.println("4 - View Order Status");
            System.out.println("5 - View Notification");
            System.out.println("6 - View Saved Custom Pizzas");
            System.out.println("7 - Back to Main Menu");
            System.out.println("==============================");
            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    initializePizzas();
                    break;
                case 2:
                    registerCustomer();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    viewOrderStatus();
                    break;
                case 5:
                    System.out.print("Enter Customer Phone Number: ");
                    scanner.nextLine(); // Consume newline
                    String phone = scanner.nextLine();
                    Customer customer = getCustomerByPhone(phone);
                    if (customer != null) {
                        customer.viewNotifications();
                    } else {
                        System.out.println("*** Customer Not Found. ***");
                    }
                    break;
                case 6:
                    viewSavedPizzas();
                    break;
                case 7:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid Option. Please Try Again.");
            }
        }
    }

    public void staffManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n==============================");
            System.out.println("       Staff Management        ");
            System.out.println("==============================");
            System.out.println("1 - Add (Create) New Pizza");
            System.out.println("2 - View Pizza");
            System.out.println("3 - View Toppings");
            System.out.println("4 - Display Customers");
            System.out.println("5 - Update Pizza Details");
            System.out.println("6 - Update Order State");
            System.out.println("7 - Display Orders");
            System.out.println("8 - Back to Main Menu");
            System.out.println("==============================");
            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addPizza();
                    break;
                case 2:
                    viewPizza();
                    break;
                case 3:
                    displayToppings();
                    break;
                case 4: // Display Customers functionality
                    displayCustomers();
                    break;
                case 5:
                    updatePizzaDetails();
                    break;
                case 6:
                    updateOrderState();
                    break;
                case 7:
                    viewOrders();
                    break;
                case 8:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid Option. Please Try Again.");
            }
        }
    }

    class Pizza {

        private int id;
        private String name;
        private String description;
        private int price;

        public Pizza(int id, String name, String description, int price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getPrice() {
            return price;
        }

        public void displayPizza() {
            System.out.println(id + " - " + name + " (LKR " + price + ")");
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    class Topping {

        private int id;
        private String name;
        private int price;

        public Topping(int id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public void displayTopping() {
            System.out.println(id + " - " + name + " (LKR " + price + ")");
        }
    }

    class Order {

        private int orderId;
        private String customerId;
        private String pizzaName;
        private int baseCost;
        private LocalDate orderDate;
        private List<String> toppings;
        private int toppingsCost;
        private int rating;
        private int discount; // Added field to store the discount amount
        private int deliveryFee; // Added field for delivery fee
        private OrderContext stateContext;

        public Order(int orderId, String customerId, String pizzaName, int baseCost, LocalDate orderDate) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.pizzaName = pizzaName;
            this.baseCost = baseCost;
            this.orderDate = orderDate;
            this.toppings = new ArrayList<>();
            this.toppingsCost = 0;
            this.rating = 0;
            this.discount = 0; // Initialize discount
            this.deliveryFee = 0; // Initialize delivery fee
            this.stateContext = new OrderContext();
        }

        public int getOrderId() {
            return orderId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public OrderContext getOrderContext() {
            return stateContext; // Return the state context
        }

        public String getPizzaName() {
            return pizzaName;
        }

        public int getBaseCost() {
            return baseCost;
        }

        public List<String> getToppings() {
            return toppings;
        }

        public int getToppingsCost() {
            return toppingsCost;
        }

        public void addTopping(String toppingName) {
            toppings.add(toppingName);
        }

        public void addToppingCost(int cost) {
            toppingsCost += cost;
        }

        public int getFinalAmount() {
            return (baseCost + toppingsCost + deliveryFee - discount); // Adjust final amount with discount and delivery fee
        }

        public void setDiscount(int discount) { // New method to set discount
            this.discount = discount;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDeliveryFee(int fee) { // New method to set delivery fee
            this.deliveryFee = fee;
        }

        public int getDeliveryFee() {
            return deliveryFee;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public void displayOrder() {
            System.out.println("Order ID: " + orderId);
            System.out.println("Customer Phone: " + customerId);
            System.out.println("Pizza: " + pizzaName);
            System.out.println("Base Cost: LKR " + baseCost);
            System.out.println("Toppings: " + String.join(", ", toppings) + " (LKR " + toppingsCost + ")");
            System.out.println("Discount: LKR " + discount);
            System.out.println("Delivery Fee: LKR " + deliveryFee);
            System.out.println("Total Cost: LKR " + getFinalAmount());
        }

        public OrderContext getStateContext() {
            return stateContext;
        }

        public void nextState() {
            stateContext.nextState();
        }

        public void previousState() {
            stateContext.previousState();
        }

        public void printStatus() {
            stateContext.printStatus();
        }
    }

    class Customer {

        private String id;
        private String name;
        private String address;
        private String contact;
        private String email;
        private int starPoints; // Tracks the customer's star points
        private Map<String, String> savedPizzas; // Key: Unique Name, Value: Pizza Details
        private List<String> notifications; // List of notifications

        public Customer(String id, String name, String address, String contact, String email) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.starPoints = 0; // Initialize with 0 points
            this.savedPizzas = new HashMap<>();
            this.notifications = new ArrayList<>();
        }

        // Getter for the customer ID (phone number)
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        // Getter for star points
        public int getStarPoints() {
            return starPoints;
        }

        // Setter for star points
        public void setStarPoints(int points) {
            if (points < 0) {
                System.out.println("*** Star points cannot be negative! ***");
                this.starPoints = 0; // Set to 0 if negative value is provided
            } else {
                this.starPoints = points;
            }
        }

        // Add star points to the customer
        public void addStarPoints(int points) {
            if (points > 0) {
                this.starPoints += points;
                System.out.println("*** Added " + points + " Star Points! New Balance: " + this.starPoints + " ***");
            } else {
                System.out.println("*** Invalid points to add. Must be greater than 0. ***");
            }
        }

        // Subtract star points from the customer
        public void subtractStarPoints(int points) {
            if (points > this.starPoints) {
                System.out.println("*** Insufficient Star Points! ***");
            } else {
                this.starPoints -= points;
                System.out.println("*** Subtracted " + points + " Star Points. Remaining Balance: " + this.starPoints + " ***");
            }
        }

        // Display customer details
        public void showDetails() {
            System.out.println("Customer Phone: " + id);
            System.out.println("Name: " + name);
            System.out.println("Address: " + address);
            System.out.println("Contact: " + contact);
            System.out.println("Email: " + email);
            System.out.println("Star Points: " + starPoints);
        }

        // Save custom pizza
        public void saveCustomPizza(String uniqueName, String pizzaDetails) {
            savedPizzas.put(uniqueName, pizzaDetails);
        }

        // Retrieve saved pizzas
        public Map<String, String> getSavedPizzas() {
            return savedPizzas;
        }

        // Display saved pizzas
        public void showSavedPizzas() {
            if (savedPizzas.isEmpty()) {
                System.out.println("*** No saved custom pizzas. ***");
            } else {
                System.out.println("*** Saved Custom Pizzas ***");
                for (Map.Entry<String, String> entry : savedPizzas.entrySet()) {
                    System.out.println("Name: " + entry.getKey());
                    System.out.println("Details: " + entry.getValue());
                    System.out.println("-----------------------------");
                }
            }
        }

        public void addNotification(String message) {
            notifications.add(message);
        }

        public void viewNotifications() {
            System.out.println("\n==============================");
            System.out.println("      Notifications for " + name);
            System.out.println("==============================");
            if (notifications.isEmpty()) {
                System.out.println("*** No Notifications Available. ***");
            } else {
                for (String notification : notifications) {
                    System.out.println("- " + notification);
                }
            }
            System.out.println("==============================");
        }
    }

    class PromoCode {

        private String code;
        private int discount;
        private LocalDate expiryDate;
        private String description;

        public PromoCode(String code, int discount, LocalDate expiryDate, String description) {
            this.code = code;
            this.discount = discount;
            this.expiryDate = expiryDate;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public int getDiscount() {
            return discount;
        }

        public boolean isExpired() {
            return LocalDate.now().isAfter(expiryDate);
        }

        public LocalDate getExpiryDate() {
            return expiryDate;
        }

        public String getDescription() {
            return description;
        }

    }

    interface PaymentStrategy {

        void pay(int amount);

        String getPaymentMethod();
    }

    class CreditCardPayment implements PaymentStrategy {

        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public void pay(int amount) {
            System.out.println("Paid LKR " + amount + " using Credit Card: " + cardNumber);
        }

        @Override
        public String getPaymentMethod() {
            return "Credit Card";
        }
    }

    class DigitalWalletPayment implements PaymentStrategy {

        private String walletId;

        public DigitalWalletPayment(String walletId) {
            this.walletId = walletId;
        }

        @Override
        public void pay(int amount) {
            System.out.println("Paid LKR " + amount + " using Digital Wallet: " + walletId);
        }

        @Override
        public String getPaymentMethod() {
            return "Digital Wallet";
        }
    }

    class CashOnDeliveryPayment implements PaymentStrategy {

        @Override
        public void pay(int amount) {
            System.out.println("Payment of LKR " + amount + " will be collected upon delivery.");
        }

        @Override
        public String getPaymentMethod() {
            return "Cash on Delivery";
        }
    }

    interface OrderObserver {

        void update(String message);
    }

    class CustomerObserver implements OrderObserver {

        private Customer customer;

        public CustomerObserver(Customer customer) {
            this.customer = customer;
        }

        @Override
        public void update(String message) {
            customer.addNotification(message);
            System.out.println("Notification sent to " + customer.getName() + ": " + message);
        }
    }

    class OrderContext {

        private OrderState state;
        private List<OrderObserver> observers; // List of observers

        public OrderContext() {
            state = new PlacedState(); // Default state
            observers = new ArrayList<>(); // Initialize the observers list
        }

        public void setState(OrderState state) {
            this.state = state;
            notifyObservers("Order state changed to: " + state.getClass().getSimpleName());
        }

        public void nextState() {
            state.next(this);
        }

        public void previousState() {
            state.previous(this);
        }

        public void printStatus() {
            state.printStatus();
        }

        // Observer methods
        public void addObserver(OrderObserver observer) {
            observers.add(observer);
        }

        public void removeObserver(OrderObserver observer) {
            observers.remove(observer);
        }

        private void notifyObservers(String message) {
            for (OrderObserver observer : observers) {
                observer.update(message);
            }
        }
    }

    interface OrderState {

        void next(OrderContext context);

        void previous(OrderContext context);

        void printStatus();
    }

    class PlacedState implements OrderState {

        @Override
        public void next(OrderContext context) {
            context.setState(new InPreparationState());
            System.out.println("Order moved to In Preparation.");
        }

        @Override
        public void previous(OrderContext context) {
            System.out.println("The order is already in the Placed state.");
        }

        @Override
        public void printStatus() {
            System.out.println("Order has been placed.");
        }
    }

    class InPreparationState implements OrderState {

        @Override
        public void next(OrderContext context) {
            context.setState(new OutForDeliveryState());
            System.out.println("Order is now Out for Delivery.");
        }

        @Override
        public void previous(OrderContext context) {
            context.setState(new PlacedState());
            System.out.println("Order reverted to Placed state.");
        }

        @Override
        public void printStatus() {
            System.out.println("Order is being prepared.");
        }
    }

    class OutForDeliveryState implements OrderState {

        @Override
        public void next(OrderContext context) {
            context.setState(new DeliveredState());
            System.out.println("Order has been Delivered.");
        }

        @Override
        public void previous(OrderContext context) {
            context.setState(new InPreparationState());
            System.out.println("Order reverted to In Preparation.");
        }

        @Override
        public void printStatus() {
            System.out.println("Order is out for delivery.");
        }
    }

    class DeliveredState implements OrderState {

        @Override
        public void next(OrderContext context) {
            System.out.println("Order is already in the Delivered state.");
        }

        @Override
        public void previous(OrderContext context) {
            context.setState(new OutForDeliveryState());
            System.out.println("Order reverted to Out for Delivery.");
        }

        @Override
        public void printStatus() {
            System.out.println("Order has been delivered.");
        }
    }
}
