package apap.ti.silogistik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik.model.KaryawanModel;

@Repository
public interface KaryawanDb extends JpaRepository<KaryawanModel, Long> {
}
