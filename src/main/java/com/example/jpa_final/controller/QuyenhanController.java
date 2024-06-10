package com.example.jpa_final.controller;

import com.example.jpa_final.model.QuyenHan;
import com.example.jpa_final.model.TaiKhoan;
import com.example.jpa_final.repo.IQuyenhanRep;
import com.example.jpa_final.repo.ITaikhoanRep;
import com.example.jpa_final.services.QuyenhanServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.validation.Validator;
import java.util.List;

@RestController
@RequestMapping(value = "api/quyenhan")
public class QuyenhanController {
   @Autowired
    QuyenhanServices qhServices;
    @PostMapping(value = "themquyen")
   public void themQH(@RequestBody  QuyenHan quyenHan){
       qhServices.themQuyenHan(quyenHan);
   }
   @PutMapping(value = "suaquyen")
    public ResponseEntity<?> suaQH(@RequestBody QuyenHan quyenHan){
        if(qhServices.suaQuyenHan(quyenHan)){
            return ResponseEntity.ok("sua thanh cong quyen co ma: "+quyenHan.getQuyenhanID());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sua khong thanh cong quyen co ma: "+quyenHan.getQuyenhanID());
   }
   @DeleteMapping(value = "xoaquyen")
    public ResponseEntity<?> xoaQH(@RequestParam int quyenid){
        if(qhServices.xoaQuyenHan(quyenid)){
            return ResponseEntity.ok("xoa thanh cong quyen co ma: "+quyenid);
        }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai quyen co ma: "+quyenid);
   }
   @GetMapping(value = "hienthiquyen")
    public List<QuyenHan> hienthiQH(@RequestParam int pagenum){
        return qhServices.hienthiQuyen(pagenum);
   }
}
