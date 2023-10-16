package apap.ti.silogistik.utils;

import java.util.HashMap;

public class BarangUtils {
    
    public static String generateSKU(int tipeBarang, int currentId){
         String sku = "";
        // configure sku
        switch (tipeBarang){
            case 1:
                sku = "ELEC";
                break;
            case 2:
                sku = "CLOT";
                break;
            case 3:
                sku = "FOOD";
                break;
            case 4:
                sku = "COSM";
                break;
            case 5:
                sku = "TOOL";
                break;
        }

        // assign latest id
        if (currentId < 10){
            sku += "00" + currentId;
        } else if (currentId < 100){
            sku += "0" + currentId;
        } else {
            sku += currentId;
        }
        return sku;
    }

    public static String generateStringTipeBarang (int tipeBarang){
         // assign tipe barang
         String tipe="";
         switch (tipeBarang){
             case 1:
                 tipe = "Produk Elektronik";
                 break;
             case 2:
                 tipe = "Pakaian & Aksesoris";
                 break;
             case 3:
                 tipe = "Makanan & Minuman";
                 break;
             case 4:
                 tipe = "Kosmetik";
                 break;
             case 5:
                 tipe = "Perlengkapan Rumah";
                 break;
         }
         return tipe;
    }

    public static HashMap<Integer,String> generateHashMapTipeBarang(){
        HashMap<Integer,String> listTipe = new HashMap<>();
        listTipe.put(1,"Produk Elektronik");
        listTipe.put(2,"Pakaian & Aksesoris");
        listTipe.put(3,"Makanan & Minuman");
        listTipe.put(4,"Kosmetik");
        listTipe.put(5,"Perlengkapan Rumah");
        return listTipe;
    }

}
