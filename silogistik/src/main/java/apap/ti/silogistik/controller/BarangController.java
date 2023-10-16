package apap.ti.silogistik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik.model.BarangModel;
import apap.ti.silogistik.model.GudangBarangModel;
import apap.ti.silogistik.service.BarangService;
import apap.ti.silogistik.utils.BarangUtils;

@Controller
public class BarangController {

    @Autowired
    BarangService barangService;

    // view-all barang
    @GetMapping("/barang")
    public String listBarang(Model model){
        List<BarangModel> listBarang = barangService.getBarangList();
        model.addAttribute("listBarang", listBarang);
        // implementasi total stok
        List<Integer>totalStok = new ArrayList<>();
        for (int i=0; i<listBarang.size(); i++){
            int total = 0;
            for (GudangBarangModel x: listBarang.get(i).getGudangBarang()){
                total += x.getStok();
            }
            totalStok.add(total);
        }
        model.addAttribute("listStok", totalStok);
        return "view-all-barang";
    }

    // add barang
    @GetMapping("/barang/tambah")
    public String formAddBarang(Model model){
        BarangModel barang = new BarangModel();
        model.addAttribute("barang", barang);
        model.addAttribute("listTipe", BarangUtils.generateHashMapTipeBarang());
        return "form-add-barang";
    }

    @PostMapping("/barang/tambah")
    public String addBarangSubmit(
            @ModelAttribute BarangModel barang,
            Model model
    ){
        // get latest id
        List<BarangModel> listBarang = barangService.getBarangList();
        int id = listBarang.size() + 1;
        barang.setSku(BarangUtils.generateSKU(barang.getTipeBarang(), id));
        barangService.addBarang(barang);
        model.addAttribute("merk", barang.getMerk());
        model.addAttribute("sku", barang.getSku());
        return "feedback-add-barang";
    }

    // update barang
    @GetMapping("/barang/ubah/{skuBarang}")
    public String updateBarang(
            @PathVariable String skuBarang,
            Model model
    ){
        BarangModel barang = barangService.getBarangBySku(skuBarang);
        model.addAttribute("listTipe", BarangUtils.generateHashMapTipeBarang());
        model.addAttribute("barang", barang);
        model.addAttribute("tipeBarang", BarangUtils.generateStringTipeBarang(barang.getTipeBarang()));
        return "form-update-barang";
    }

    @PostMapping("/barang/ubah/{skuBarang}")
    public String updateBarangSubmit(
            @PathVariable String skuBarang,
            @ModelAttribute BarangModel barang,
            Model model
    ){
        barangService.updateBarang(barang);
        model.addAttribute("merk", barang.getMerk());
        model.addAttribute("sku", skuBarang);
        model.addAttribute("barang", barang);
        return "feedback-update-barang";
    }

    // detail-barang
    @GetMapping("/barang/{skuBarang}")
    public String detailBarang(
            @PathVariable String skuBarang,
            Model model
    ){
        BarangModel barang = barangService.getBarangBySku(skuBarang);
        // count total stok
        int totalStok = 0;
        for (GudangBarangModel x: barang.getGudangBarang()){
            if (x.getBarang().getSku().equals(barang.getSku())){
                totalStok += x.getStok();
            }
        }
        model.addAttribute("listGudangBarang", barang.getGudangBarang());
        model.addAttribute("totalStok", totalStok);
        model.addAttribute("tipeBarang", BarangUtils.generateStringTipeBarang(barang.getTipeBarang()));
        model.addAttribute("barang", barang);
        return "detail-barang";
    }




}
