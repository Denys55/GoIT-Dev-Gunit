package com.misterproduct.controller;

import com.misterproduct.model.impl.Cart;
import com.misterproduct.model.impl.Product;
import com.misterproduct.repository.BaseRepository;
import com.misterproduct.repository.impl.ProductRepository;
import com.misterproduct.util.Util;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

public class Controller {
    private static final String START = "Write line with basket or exit";
    private static final String EXIT = "exit";
    private static final String WRONG = "You write not exist product %s. please check and try again";
    private static final String TOTAL_PRICE = "Total price = %.2f";

    Cart cart;
    BaseRepository repository;

    public Controller() {
        cart = new Cart();
        repository = new ProductRepository();
    }

    private void startProgram() {
        System.out.println(START);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String request = scanner.nextLine();
            if (request.equalsIgnoreCase(EXIT)) {
                break;
            } else {
                char[] productsFormRequest = Util.separateRequestWithCartFromClient(request);
                boolean flag = true;
                for (char product : productsFormRequest) {
                    boolean productExist = checkProduct(product);
                    if (!productExist) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    BigDecimal totalPrice = cart.getTotalPrice();
                    System.out.format(TOTAL_PRICE, totalPrice);
                }
            }
        }
    }

    private boolean checkProduct(char product) {
        Optional<Product> productByName = repository.getByName(String.valueOf(product));

        if (productByName.isPresent()) {
            cart.addProductToCart(productByName.get());
        } else {
            cart.clearCart();
            System.out.format(WRONG, product);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.startProgram();
    }

}
