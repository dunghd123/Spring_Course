package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "quyenhans")
@Getter
@Setter
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int QuyenhanID;
    @Column(name = "tenquyenhan")
    @Size(max = 50,message = "ten quyen han khong qua 50 ky tu!")
    private String Tenquyenhan;

    // lien ket voi bang Taikhoan
    @OneToMany(mappedBy = "quyenHan")
    @JsonIgnoreProperties(value = "quyenHan")
    Set<TaiKhoan> taiKhoans;
}
