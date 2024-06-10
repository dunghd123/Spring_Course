package com.example.jpa_final.repo;

import com.example.jpa_final.model.TinhTrangHoc;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITinhtranghocRep extends JpaRepository<TinhTrangHoc,Integer> {
    @Query(value = "select * from tinhtranghocs",nativeQuery = true)
    public List<TinhTrangHoc> findAllBy(Pageable pageable);
}
