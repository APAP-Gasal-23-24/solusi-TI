package apap.ti.silogistik.repository;


import apap.ti.silogistik.model.PermintaanPengirimanBarangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarangModel, Long> {
}
