package com.rafa.resourcetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rafa.resourcetracker.entity.ProcessRankEntity;

public interface ProcessRankRepository extends JpaRepository<ProcessRankEntity, Long> {
    @Query(
        nativeQuery=true,
        value = "SELECT * FROM PROCESS_RANK ORDER BY CPU_USAGE DESC"
    )
    List<ProcessRankEntity> findAllOrderByCpuUsage();
}
