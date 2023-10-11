package util;

import java.util.Scanner;

public class Common {
    public static void promptEnterKey(){
        System.out.println("Nhấn \"ENTER\" để tiếp tục xem...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
