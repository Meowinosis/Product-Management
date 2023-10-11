package view;

import manager.ProductManager;
import model.Product;
import util.Common;
import util.ReadAndWriteFile;
import validate.Validate;

import java.util.*;

public class Menu {
    ProductManager pm = new ProductManager();
    Validate validate = new Validate();
    ReadAndWriteFile rw = new ReadAndWriteFile();
    Scanner sc = new Scanner(System.in);

    public void showMenu() {
        do {
            System.out.println("=====>>> Chương trình quản lý sản phầm <<<======");
            System.out.println("\t\t1. Xem danh sách \n\t\t2. Thêm mới \n\t\t3. Cập nhật\n\t\t4. Xóa \n\t\t5. Sắp xếp\n\t\t6. Tìm sản phẩm có giá đắt nhất \n\t\t7. Thoát");
            System.out.print("Nhập lựa chọn: ");
            int choice = validate.inputChoice();
            switch (choice) {
                case 1:
                    getAll();
                    break;
                case 2:
                    showMenuAdd();
                    break;
                case 3:
                    showMenuEdit();
                    break;
                case 4:
                    showMenuRemove();
                    break;
                case 5:
                    sortProduct();
                    break;
                case 6:
                    maxPriceProduct();
                    break;
                case 7:
                    return;
            }
        } while (true);
    }

    public void getAll() {
        int enterCount = pm.getAll().size() / 5 + 1;
        int count = 0;
        for (Product p : pm.getAll()) {
            System.out.println(p);
            count++;
            if (count == 5 && enterCount > 0) {
                count = 0;
                Common.promptEnterKey();
                enterCount--;
            }
        }
    }

    public void showMenuRemove() {
        String id;
        do {
            System.out.print("Nhập id sản phẩm cần xóa: ");
            id = sc.nextLine();
            if (pm.getById(id) == null && !id.isEmpty()) {
                System.out.println("Không tìm được sản phẩm với mã sản phẩm trên");
            }
            if (id.isEmpty()) return;
        } while (pm.getById(id) == null);
        System.out.println("Bạn có muốn xóa sản phẩm không(Nhấn 'Y' để đồng ý): ");
        String decision = sc.nextLine().toLowerCase();
        if (decision.equals("y")){
            pm.remove(id);
            System.out.println("Xóa sản phẩm thành công");
        }
    }

    public void showMenuEdit() {
        String id;
        do {
            System.out.print("Nhập id sản phẩm cần cập nhật: ");
            id = sc.nextLine();
            if (pm.getById(id) == null && !id.isEmpty()) {
                System.out.println("Không tìm được sản phẩm với mã sản phẩm trên");
            }
            if (id.isEmpty()) return;
        } while (pm.getById(id) == null);
        Product p = inputForm(id);
        pm.edit(id, p);
        rw.writeProductData(pm.getAll());
        System.out.println("Cập nhật sản phẩm thành công");
    }

    private Product inputForm(String id) {
        String name = validate.validateName();
        int price = validate.validatePrice();
        int quantity = validate.validateQuantity();
        System.out.print("Nhập mô tả sản phẩm: ");
        String description = sc.nextLine();
        if (description.isEmpty()) {
            description = "Chưa có mô tả";
        }
        return new Product(id, name, price, quantity, description);
    }

    public void showMenuAdd() {
        String id;
        do {
            id = validate.validateId();
            if (pm.getById(id) != null) {
                System.out.println("Id đã tồn tại");
            }
        } while (pm.getById(id) != null);
        Product p = inputForm(id);
        pm.add(p);
        rw.writeProductData(pm.getAll());
        System.out.println("Thêm sản phẩm thành công");
    }

    public void sortProduct() {
        do {
            System.out.println("===== Sắp xếp theo ======");
            System.out.println("\t\t1. Sắp xếp theo giá tăng dần \n\t\t2. Sắp xếp theo giá giảm dần\n\t\t3. Quay lại menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = validate.inputChoice();
            switch (choice) {
                case 1:
                    pm.getAll().sort(Comparator.comparing(Product::getPrice));
                    getAll();
                    break;
                case 2:
                    pm.getAll().sort(Comparator.comparing(Product::getPrice).reversed());
                    getAll();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    public void maxPriceProduct(){
        Product maxPrice = Collections.max(pm.getAll(),Comparator.comparing(Product::getPrice));
        System.out.println(maxPrice);
    }
}
