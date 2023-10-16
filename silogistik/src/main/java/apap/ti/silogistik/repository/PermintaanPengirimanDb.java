package apap.ti.silogistik.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import apap.ti.silogistik.model.PermintaanPengirimanModel;

public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengirimanModel, Long> {

    Optional<PermintaanPengirimanModel> findById(Long id);
    List<PermintaanPengirimanModel> findByWaktuPermintaanBetween(LocalDateTime startDate, LocalDateTime endDate);
}
