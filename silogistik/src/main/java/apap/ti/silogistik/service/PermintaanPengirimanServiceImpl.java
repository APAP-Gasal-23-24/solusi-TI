package apap.ti.silogistik.service;

import apap.ti.silogistik.model.PermintaanPengirimanModel;
import apap.ti.silogistik.repository.PermintaanPengirimanDb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService{
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;
    
    @Override
    public PermintaanPengirimanModel getPermintaanPengirimanById(Long id){
        Optional<PermintaanPengirimanModel> permintaanPengiriman = permintaanPengirimanDb.findById(id);
        if (permintaanPengiriman.isPresent()){
            return permintaanPengiriman.get();
        }
        return null;
    }

    @Override
    public PermintaanPengirimanModel addPermintaanPengiriman(PermintaanPengirimanModel permintaanPengiriman) {
        return permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public void deletePermintaanPengiriman(PermintaanPengirimanModel permintaanPengiriman) {
        permintaanPengirimanDb.delete(permintaanPengiriman);
    }

    @Override
    public List<PermintaanPengirimanModel> getPermintaanPengirimanList() {
        return permintaanPengirimanDb.findAll();
    }

    @Override
    public List<PermintaanPengirimanModel> getPermintaanPengirimanByPeriod(LocalDateTime startDate, LocalDateTime endDate){
        return permintaanPengirimanDb.findByWaktuPermintaanBetween(startDate, endDate);
    }

}
