package apap.ti.silogistik.service;

import apap.ti.silogistik.model.GudangBarangModel;

public interface GudangBarangService {
    void addGudangBarang(GudangBarangModel gudangBarangModel);
    void updateGudangBarang(GudangBarangModel gudangBarangModel);
    void deleteGudangBarang(GudangBarangModel gudangBarangModel);
}
