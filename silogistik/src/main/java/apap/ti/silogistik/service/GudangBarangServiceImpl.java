package apap.ti.silogistik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik.model.GudangBarangModel;
import apap.ti.silogistik.repository.GudangBarangDb;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void addGudangBarang(GudangBarangModel gudangBarangModel) {
        gudangBarangDb.save(gudangBarangModel);
    }

    @Override
    public void updateGudangBarang(GudangBarangModel gudangBarangModel) {
        gudangBarangDb.save(gudangBarangModel);
    }

    @Override
    public void deleteGudangBarang(GudangBarangModel gudangBarangModel){
        gudangBarangDb.delete(gudangBarangModel);
    }

}
