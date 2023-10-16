package apap.ti.silogistik.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Getter
@Setter
@Entity
@Table(name = "barang")
public class BarangModel {

    @Id
    private String sku;

    @NotNull
    @Column(nullable = false)
    private int tipeBarang;

    @NotNull
    @Column(nullable = false)
    private String merk;

    @NotNull
    @Column(nullable = false)
    private Long hargaBarang;

    @OneToMany(mappedBy = "barang")
    Set<GudangBarangModel> gudangBarang;

    @OneToMany(mappedBy = "barang2")
    Set<PermintaanPengirimanBarangModel> permintaanPengirimanBarang;
}
