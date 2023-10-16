package apap.ti.silogistik.service;

import apap.ti.silogistik.model.GudangModel;
import apap.ti.silogistik.repository.GudangDb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;


    @Override
    public GudangModel getGudangById(Long id) {
        Optional<GudangModel> gudang = gudangDb.findById(id);
        if (gudang.isPresent()){
            return gudang.get();
        }
        return null;
    }

    @Override
    public List<GudangModel> getGudangList() {
        return gudangDb.findAll();
    }

    @Override
    public void addGudang(GudangModel gudang) {
        gudangDb.save(gudang);
    }
}
