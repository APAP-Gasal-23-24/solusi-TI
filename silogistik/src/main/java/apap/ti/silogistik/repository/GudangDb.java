package apap.ti.silogistik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik.model.GudangModel;

@Repository
public interface GudangDb extends JpaRepository<GudangModel, Long> {
    Optional<GudangModel> findById(Long id);
}
