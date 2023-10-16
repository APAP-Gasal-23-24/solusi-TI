package apap.ti.silogistik.service;

import apap.ti.silogistik.model.KaryawanModel;
import java.util.List;

public interface KaryawanService {

    List<KaryawanModel> getKaryawanList();
    void addKaryawan(KaryawanModel karyawan);
}
