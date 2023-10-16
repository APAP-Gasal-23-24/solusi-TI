package apap.ti.silogistik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik.model.BarangModel;

@Repository
public interface BarangDb extends JpaRepository<BarangModel, String> {
    Optional<BarangModel> findBySku(String sku);
}
