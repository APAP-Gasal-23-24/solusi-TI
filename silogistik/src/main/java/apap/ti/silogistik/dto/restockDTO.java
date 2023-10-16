package apap.ti.silogistik.dto;
import java.util.List;

import apap.ti.silogistik.model.GudangBarangModel;
import apap.ti.silogistik.model.GudangModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class restockDTO {
    private GudangModel gudang;
    private List <GudangBarangModel> listRestock;
}
