package apap.ti.silogistik.controller;


import apap.ti.silogistik.dto.restockDTO;
import apap.ti.silogistik.model.BarangModel;
import apap.ti.silogistik.model.GudangBarangModel;
import apap.ti.silogistik.model.GudangModel;
import apap.ti.silogistik.service.BarangService;
import apap.ti.silogistik.service.GudangBarangService;
import apap.ti.silogistik.service.GudangService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GudangController {

    @Autowired
    GudangService gudangService;
    
    @Autowired
    BarangService barangService;

    @Autowired
    GudangBarangService gudangBarangService;

    @GetMapping("/gudang")
    public String listGudang(Model model){
        List<GudangModel> listGudang = gudangService.getGudangList();
        model.addAttribute("listGudang", listGudang);
        return "view-all-gudang";
    }

    @GetMapping("/gudang/{id}")
    public String detailGudang(@PathVariable Long id, Model model){
        GudangModel gudang = gudangService.getGudangById(id);
        model.addAttribute("gudang", gudang);
        return "detail-gudang";
    }

   @GetMapping("/gudang/{idGudang}/restock-barang")
   public String restockBarangGet(@PathVariable Long idGudang, Model model) {
        GudangModel gudang = gudangService.getGudangById(idGudang);
        List<BarangModel> listBarang = barangService.getBarangList();
        Set<GudangBarangModel> listRestock = gudang.getGudangBarang();


        // create dto and assign value to dto
        restockDTO dto = new restockDTO();
        dto.setGudang(gudang);
        // check jika gudang sudah/belum memiliki barang
        if (dto.getListRestock() == null || dto.getListRestock().size() == 0){
            dto.setListRestock(new ArrayList<>());
        }
        for (GudangBarangModel x : listRestock){
            dto.getListRestock().add(x);
        }
        
        model.addAttribute("dto", dto);
        model.addAttribute( "listBarang", listBarang);
        return "form-restock";
   }

   // add row
   @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = {"addRow"})
   public String addRowRestockBarang(@PathVariable Long idGudang, Model model, @ModelAttribute restockDTO dto){
    if (dto.getListRestock() == null || dto.getListRestock().size() == 0) {
        dto.setListRestock(new ArrayList<>());
        // listRestock.add(new GudangBarangModel());
    }    
    dto.getListRestock().add(new GudangBarangModel());
    List<BarangModel> listBarang = barangService.getBarangList();
    model.addAttribute("dto", dto);
    model.addAttribute("gudang", dto.getGudang());
    model.addAttribute( "listBarang", listBarang);
    return "form-restock";
   }

   // delete row
   @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = {"deleteRow"})
    public String deleteRowPenulisBuku(
            @PathVariable Long idGudang,        
            @ModelAttribute restockDTO dto,
            @RequestParam("deleteRow") int row,
            Model model
    ) {
        List<BarangModel> listBarang = barangService.getBarangList();
        dto.getListRestock().remove(row);
        model.addAttribute("dto", dto);
        model.addAttribute("gudang", dto.getGudang());
        model.addAttribute( "listBarang", listBarang);
        return "form-restock";
    }

   // submit
   @PostMapping("/gudang/{idGudang}/restock-barang")
   public String restockBarangPost(@PathVariable Long idGudang, Model model, @ModelAttribute restockDTO restockDTO){

        // create hashmap to avoid duplicate
        HashMap<String, Integer> inputRestock = new HashMap<>();
        // check id and current item
        GudangModel gudang = gudangService.getGudangById(idGudang);
        Set<GudangBarangModel> listRestock = gudang.getGudangBarang();
        
        for (GudangBarangModel x : restockDTO.getListRestock()){
            if (inputRestock.containsKey(x.getBarang().getSku())){
                inputRestock.put(x.getBarang().getSku(), inputRestock.get(x.getBarang().getSku())+x.getStok());
            }
            else{
                inputRestock.put(x.getBarang().getSku(), x.getStok());
            }
        }

        // delete element that exist in db but not exist in hashmap

        for (GudangBarangModel x: listRestock){
            if (!inputRestock.containsKey(x.getBarang().getSku())){
                gudangBarangService.deleteGudangBarang(x);
            }
        }

        // add new element and updated stock
        inputRestock.forEach((key, value) -> {
            for (GudangBarangModel x: listRestock){
                if (key.equals(x.getBarang().getSku())){
                    x.setStok(inputRestock.get(key));
                    gudangBarangService.addGudangBarang(x);
                    return;
                }
            }
            gudangBarangService.addGudangBarang(new GudangBarangModel(null, gudang, barangService.getBarangBySku(key), inputRestock.get(key)));
        });

        // return "form-restock";
        return "redirect:/gudang/{idGudang}";
   }

    @GetMapping("/gudang/cari-barang")
    public String cariBarangGet(Model model,
                                @RequestParam(value = "skuBarang", required = false) String skuBarang) {
        List<BarangModel> listBarang = barangService.getBarangList();

        List<GudangModel> listGudangAll = gudangService.getGudangList();
        List<GudangModel> listGudangFiltered = new ArrayList<GudangModel>();
        Set<GudangBarangModel> listGB = new HashSet<>();

        boolean isActive = false;

        if (skuBarang!=null){
            isActive=true;
            listGB = barangService.getBarangBySku(skuBarang).getGudangBarang();
            for (GudangModel gudang: listGudangAll
            ) {
                List<BarangModel> listBarangPerGudang = new ArrayList<BarangModel>();
                for (GudangBarangModel gb: gudang.getGudangBarang()
                     ) {
                    listBarangPerGudang.add(gb.getBarang());
                }
                if(listBarangPerGudang.contains(barangService.getBarangBySku(skuBarang))){
                    listGudangFiltered.add(gudang);
                }
            }
        }

        model.addAttribute("isFilterActive", isActive);
        model.addAttribute( "listBarang", listBarang);
        model.addAttribute( "listGudangFiltered", listGB);
        return "cari-barang";
    }

}
