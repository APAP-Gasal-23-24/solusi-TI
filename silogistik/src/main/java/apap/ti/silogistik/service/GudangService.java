package apap.ti.silogistik.service;

import apap.ti.silogistik.model.GudangModel;

import java.util.List;

public interface GudangService {
    GudangModel getGudangById(Long id);
    List<GudangModel> getGudangList();
    void addGudang(GudangModel gudang);
}
