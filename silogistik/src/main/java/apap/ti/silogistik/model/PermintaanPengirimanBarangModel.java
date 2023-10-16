package apap.ti.silogistik.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarangModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barang_id")
    private BarangModel barang2;

    @NotNull
    @Column(nullable = false)
    private int kuantitasPermintaan;

    @ManyToOne
    @JoinColumn(name = "permintaan_pengiriman_id")
    private PermintaanPengirimanModel permintaanPengiriman;

}
