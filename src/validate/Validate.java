package validate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class    Validate {
    Scanner sc = new Scanner(System.in);

    public String validateId() {
        String id;
        do {
            System.out.print("Nhập id sản phẩm: ");
            id = sc.nextLine();
            if (!id.isEmpty()) {
                break;
            } else {
                System.out.println("Không được để trống trường id");
            }

        } while (true);
        return id;
    }

    public String validateName() {
        String name;
        do {
            System.out.print("Nhập tên sản phẩm: ");
            name = sc.nextLine();
            if (!name.isEmpty()) {
                break;
            } else {
                System.out.println("Không được để trống tên sản phẩm");
            }
        } while (true);
        return name;
    }

    public int validateQuantity() {
        String quantity;
        do {
            System.out.print("Nhập số lượng sản phẩm: ");
            try {
                quantity = sc.nextLine();
                if(!quantity.isEmpty()) {
                    return Integer.parseInt(quantity);
                }
                else {
                    System.out.println("Không được để trống số lượng");
                }

            } catch (Exception e) {
                System.out.println("Xin hãy nhập số");
            }
        }
        while (true);
    }

    public int validatePrice() {
        String price;
        do {
            System.out.print("Nhập giá sản phẩm: ");
            try {
                price = sc.nextLine();
                if(!price.isEmpty()) {
                    return Integer.parseInt(price);
                }
                else {
                    System.out.println("Không được để trống giá sản phẩm");
                }

            } catch (Exception e) {
                System.out.println("Xin hãy nhập số");
            }
        }
        while (true);
    }

    public int inputChoice() {
        int choice;
        try {
            choice = sc.nextInt();
            sc.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Vui lòng nhập số");
            sc.nextLine();
        }
        return -1;
    }
}
