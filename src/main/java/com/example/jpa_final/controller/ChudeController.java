package com.example.jpa_final.controller;

import com.example.jpa_final.model.ChuDe;
import com.example.jpa_final.services.ChudeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/chude")
public class ChudeController {
    @Autowired
    ChudeServices chudeServices;
    @PostMapping(value = "themchude")
    public void themCD(@RequestBody ChuDe chuDe){
        chudeServices.themChude(chuDe);
    }

    @PutMapping(value = "suachude")
    public void suaCD(@RequestBody ChuDe chuDe){
        chudeServices.suaChude(chuDe);
    }

    @DeleteMapping(value = "xoachude")
    public ResponseEntity<?> xoaCD(@RequestParam int chudeid){
        if(chudeServices.xoaChude(chudeid)){
            return ResponseEntity.ok("xoa thanh cong chu de co ma: "+chudeid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai chu de co ma: "+chudeid);
    }
    @GetMapping(value = "hienthichude")
    public ResponseEntity<?> hienthiCD(@RequestParam int pagenum, @RequestParam int pagesize){
        return ResponseEntity.ok(chudeServices.hienthiChuDe(pagenum,pagesize));
    }
}
