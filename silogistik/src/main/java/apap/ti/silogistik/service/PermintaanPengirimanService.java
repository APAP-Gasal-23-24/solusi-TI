package apap.ti.silogistik.service;

import apap.ti.silogistik.model.PermintaanPengirimanModel;

import java.time.LocalDateTime;
import java.util.List;

public interface PermintaanPengirimanService {
    PermintaanPengirimanModel getPermintaanPengirimanById(Long id);

    PermintaanPengirimanModel addPermintaanPengiriman(PermintaanPengirimanModel permintaanPengiriman);

    void deletePermintaanPengiriman(PermintaanPengirimanModel permintaanPengiriman);

    List<PermintaanPengirimanModel> getPermintaanPengirimanList();

    List<PermintaanPengirimanModel> getPermintaanPengirimanByPeriod(LocalDateTime startDate, LocalDateTime endDate);

}
