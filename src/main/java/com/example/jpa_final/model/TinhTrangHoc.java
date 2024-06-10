package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tinhtranghocs")
@Getter
@Setter
public class TinhTrangHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TinhtranghocID;
    @Column(name = "tentinhtrang")
    @Size(max = 40, message = "ten tinh trang khong qua 40 ky tu!")
    private String Tentinhtrang;

    //lien ket den bang dangkyhoc
    @OneToMany(mappedBy = "tinhTrangHoc")
    @JsonIgnoreProperties(value = "tinhTrangHoc")
    Set<DangKyHoc> dangKyHocs;
}
