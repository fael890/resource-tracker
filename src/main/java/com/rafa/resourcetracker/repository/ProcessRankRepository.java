package com.rafa.resourcetracker.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rafa.resourcetracker.entity.ProcessRankEntity;

public interface ProcessRankRepository extends JpaRepository<ProcessRankEntity, Long> {
    @Query(
        nativeQuery=true,
        value = "SELECT * FROM PROCESS_RANK ORDER BY CPU_USAGE DESC"
    )
    List<ProcessRankEntity> findAllOrderByCpuUsage();

    @Modifying
    @Query(
        nativeQuery=true,
        value = "UPDATE PROCESS_RANK SET PID=?2, NAME=?3,CPU_USAGE=?4, RAM_USAGE=?5, DISK_READ_USAGE=?6,DISK_WRITE_USAGE=?7, LAST_UPDATE=?8 WHERE ID = ?1 "
    )
    void updateById(
        Long id,
        int pid, 
        String name, 
        double cpuUsage, 
        double ramUsage, 
        double diskReadUsage,
        double diskWriteUsage, 
        LocalDateTime lastUpdate
    );

    @Modifying
    @Query(
        nativeQuery=true,
        value = "UPDATE PROCESS_RANK SET PID=:pid, NAME=:name, CPU_USAGE=:cpuUsage, RAM_USAGE=:ramUsage, DISK_READ_USAGE=:diskReadUsage,DISK_WRITE_USAGE=:diskWriteUsage, LAST_UPDATE=:lastUpdate WHERE NAME = :name "
    )
    void updateByName(
        int pid, 
        String name, 
        double cpuUsage, 
        double ramUsage, 
        double diskReadUsage,
        double diskWriteUsage, 
        LocalDateTime lastUpdate
    );

    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE PROCESS_RANK SET CPU_USAGE=:cpuUsage, RAM_USAGE=:ramUsage, DISK_READ_USAGE=:diskReadUsage, DISK_WRITE_USAGE=:diskWriteUsage, LAST_UPDATE=:lastUpdate WHERE PID=:pid AND NAME=:name"
    )
    void updateByPidAndName(int pid, String name, double cpuUsage, double ramUsage, double diskReadUsage, double diskWriteUsage, LocalDateTime lastUpdate);
 
    @Modifying
    @Query(
        nativeQuery = true,
        value = "TRUNCATE TABLE process_rank RESTART IDENTITY"
    )
    void truncateProcessRank();

    public List<ProcessRankEntity> findAllByOrderByCpuUsageDesc();

    Optional<ProcessRankEntity> findByPidAndName(int pid, String name);

    boolean existsByName(String name);
}
