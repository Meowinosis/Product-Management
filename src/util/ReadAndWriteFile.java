package util;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFile {
    private final File fileProduct = new File("data/product.csv");
    public void writeProductData(List<Product> productList){
        try {
            FileWriter fileWriter = new FileWriter(fileProduct);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = "";
            for (Product product: productList) {
                line += product.getDataProduct() + "\n";
            }
            bufferedWriter.write(line);
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Product> readProductData(){
        List<Product> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileProduct));
            String line;
            while ((line = bufferedReader.readLine())!= null){
                String[] data = line.split(",");
                Product product = new Product(data[0],data[1],Integer.parseInt(data[2]), Integer.parseInt(data[3]),data[4]);
                list.add(product);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

}
