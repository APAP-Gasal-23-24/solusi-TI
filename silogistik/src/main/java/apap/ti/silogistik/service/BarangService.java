package apap.ti.silogistik.service;
import java.util.List;

import apap.ti.silogistik.model.BarangModel;

public interface BarangService {
    BarangModel getBarangBySku(String sku);
    void addBarang(BarangModel barang);
    void updateBarang(BarangModel barang);
    List<BarangModel> getBarangList();
}
