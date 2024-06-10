package com.example.jpa_final.repo;

import com.example.jpa_final.model.LoaiKhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaikhoahocRep extends JpaRepository<LoaiKhoaHoc,Integer> {
}
