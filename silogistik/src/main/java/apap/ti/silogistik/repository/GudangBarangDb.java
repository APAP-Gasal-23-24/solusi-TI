package apap.ti.silogistik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik.model.GudangBarangModel;

@Repository
public interface GudangBarangDb extends JpaRepository <GudangBarangModel, Long>{

}
