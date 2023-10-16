package apap.ti.silogistik.service;

import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import apap.ti.silogistik.model.KaryawanModel;
import apap.ti.silogistik.repository.KaryawanDb;


@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{

    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public List<KaryawanModel> getKaryawanList(){
        return karyawanDb.findAll(Sort.by("nama").ascending());
    }

    @Override
    public void addKaryawan(KaryawanModel karyawan) {
        karyawanDb.save(karyawan);
    }

}
