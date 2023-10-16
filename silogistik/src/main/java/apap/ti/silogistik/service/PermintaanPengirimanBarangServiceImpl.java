package apap.ti.silogistik.service;

import apap.ti.silogistik.model.PermintaanPengirimanBarangModel;
import apap.ti.silogistik.repository.PermintaanPengirimanBarangDb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService{


    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public void addPermintaanPengirimanBarang(PermintaanPengirimanBarangModel permintaanPengirimanBarangModel) {
        permintaanPengirimanBarangDb.save(permintaanPengirimanBarangModel);
    }

    @Override
    public void deletePermintaanPengirimanBarang(PermintaanPengirimanBarangModel permintaanPengirimanBarangModel){
        permintaanPengirimanBarangDb.delete(permintaanPengirimanBarangModel);
    }
}
