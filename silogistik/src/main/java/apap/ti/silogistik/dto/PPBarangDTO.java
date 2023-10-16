package apap.ti.silogistik.dto;

import java.util.List;

import apap.ti.silogistik.model.PermintaanPengirimanBarangModel;
import apap.ti.silogistik.model.PermintaanPengirimanModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PPBarangDTO {
    private PermintaanPengirimanModel permintaanPengiriman;
    private List<PermintaanPengirimanBarangModel> listPPBarang;
}
