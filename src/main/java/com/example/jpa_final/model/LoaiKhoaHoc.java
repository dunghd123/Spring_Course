package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import javax.xml.transform.Source;
import java.util.Set;

@Entity
@Table(name = "loaikhoahocs")
@Getter
@Setter
public class LoaiKhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LoaikhoahocID;
    @Column(name = "tenloai")
    @Size(max = 30,message = "ten loai khong qua 30 ky tu")
    private String Tenloai;

    //lien ket den bang khoahoc
    @OneToMany(mappedBy = "loaiKhoaHoc")
    @JsonIgnoreProperties(value = "loaiKhoaHoc")
    Set<KhoaHoc> khoaHocs;
}
