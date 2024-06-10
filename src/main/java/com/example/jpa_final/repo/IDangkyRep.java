package com.example.jpa_final.repo;

import com.example.jpa_final.model.DangKyHoc;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDangkyRep extends JpaRepository<DangKyHoc,Integer> {
    @Query(value = "select * from dangkyhocs",nativeQuery = true)
    public List<DangKyHoc> findAllBy(Pageable pageable);
}
