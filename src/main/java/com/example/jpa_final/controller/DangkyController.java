package com.example.jpa_final.controller;

import com.example.jpa_final.model.DangKyHoc;
import com.example.jpa_final.services.DangkyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/dangky")
public class DangkyController {
    @Autowired
    DangkyServices dkServices;
    @PostMapping(value = "themdangkyhoc")
    public ResponseEntity<?> themDKH(@RequestBody DangKyHoc dangKyHoc){
        if (dkServices.themDangKyHoc(dangKyHoc)){
            return ResponseEntity.ok("them thanh cong!!!");
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("them that bai!!!!");
    }
    @PutMapping(value = "suadangkyhoc")
    public ResponseEntity<?> suaDKH(@RequestBody DangKyHoc dangKyHoc){
        if (dkServices.suaDangKy(dangKyHoc)){
            return ResponseEntity.ok("sua thanh cong id: "+dangKyHoc.getId());
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sua khong thanh cong id: "+dangKyHoc.getId());
    }
    @DeleteMapping(value = "xoadangkyhoc")
    public ResponseEntity<?> xoaDKH(@RequestParam int id){
        if (dkServices.xoaDangKyHoc(id)){
            return ResponseEntity.ok("xoa thanh cong id: "+id);
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai id: "+id);
    }
    @GetMapping(value = "hienthidanhsachdangkyhoc")
    public List<DangKyHoc> hienTHiDS(@RequestParam int pagenum, @RequestParam int pagesize ){
        return dkServices.hienThiDKy(pagenum,pagesize);
    }

}
