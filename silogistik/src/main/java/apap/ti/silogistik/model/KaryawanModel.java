package apap.ti.silogistik.model;


import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "karyawan")
public class KaryawanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private  String nama;

    @NotNull
    @Column(nullable = false)
    private int jenisKelamin;

    @NotNull
    @Column(nullable = false)
    private java.sql.Date tanggalLahir;

    @OneToMany(mappedBy = "karyawan")
    private Set<PermintaanPengirimanModel> listPengiriman;
}
