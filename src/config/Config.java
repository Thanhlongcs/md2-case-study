package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config<T> {

    public static Scanner scanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
    public static int getValidInteger() {
        int integer;
        while (true) {
            System.out.println("Enter choice / 0 to back");
            String s = Config.scanner().nextLine();

            if (Integer.parseInt(s) == 0) return 0;

            if (s.matches("\\d+")) {
                integer = Integer.parseInt(s);
                break;
            } else {
                System.out.println("Invalid number, try again!");
            }
        }
        return integer;
    }

    public List<T> readFile(String pathFile) {
        List<T> tList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(pathFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tList = (List<T>) ois.readObject();
            fis.close();
            ois.close();

        }catch (Exception e){
            e.getMessage();

        }
            return tList;
        }


    public void writeFile(String pathFile,List<T> tList  ) {
        try {
            FileOutputStream fos = new FileOutputStream(pathFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tList);
            fos.close();
            oos.close();
        }
         catch (Exception e) {
           e.getMessage();
        }
    }

}
