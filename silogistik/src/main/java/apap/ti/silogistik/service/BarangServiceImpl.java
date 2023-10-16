package apap.ti.silogistik.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik.model.BarangModel;
import apap.ti.silogistik.repository.BarangDb;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Override
    public BarangModel getBarangBySku(String sku) {
        Optional<BarangModel> barang = barangDb.findBySku(sku);
        if (barang.isPresent()){
            return barang.get();
        }
        return null;
    }

    @Override
    public void addBarang(BarangModel barang) {
        barangDb.save(barang);
    }

    @Override
    public void updateBarang(BarangModel barang) {
        barangDb.save(barang);
    }

    @Override
    public List<BarangModel> getBarangList() {
        return barangDb.findAll();
    }
}
