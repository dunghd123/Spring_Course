package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "baiviets")
@Getter
@Setter
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BaivietID;
    @Column(name = "tenbaiviet")
    @Size(max = 50,message = "ten bai viet khong qua 50 ky tu!")
    private String Tenbaiviet;
    @Column(name = "tentacgia")
    @Size(max = 50,message = "ten tac gia khong qua 50 ky tu!")
    private String Tentacgia;
    @Column(name = "noidung")
    private String Noidung;
    @Column(name = "noidungngan")
    @Size(max = 1000,message = "ten bai viet khong qua 1000 ky tu!")
    private String Noidungngan;
    @Column(name = "thoigiantao")
    private Date Thoigiantao;
    @Column(name = "hinhanh")
    private String Hinhanh;

    //khoa ngoai den bang Chude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chudeid",foreignKey = @ForeignKey(name = "FK_Baiviet_Chude"))
    @JsonIgnoreProperties(value = "baiViets")
    ChuDe chuDe;
    //khoa ngoai den bang Taikhoan
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taikhoanid",foreignKey = @ForeignKey(name = "FK_Baiviet_Taikhoan"))
    @JsonIgnoreProperties(value = "baiViets")
    TaiKhoan taiKhoan;


}
