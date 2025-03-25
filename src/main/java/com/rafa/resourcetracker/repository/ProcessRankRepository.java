package com.rafa.resourcetracker.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rafa.resourcetracker.entity.ProcessRankEntity;

public interface ProcessRankRepository extends JpaRepository<ProcessRankEntity, Long> {
    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE PROCESS_RANK SET CPU_USAGE=:cpuUsage, RAM_USAGE=:ramUsage, DISK_READ_USAGE=:diskReadUsage, DISK_WRITE_USAGE=:diskWriteUsage, LAST_UPDATE=:lastUpdate WHERE PID=:pid AND NAME=:name"
    )
    void updateByPidAndName(int pid, String name, double cpuUsage, double ramUsage, double diskReadUsage, double diskWriteUsage, LocalDateTime lastUpdate);

    public List<ProcessRankEntity> findAllByOrderByCpuUsageDesc();

    Optional<ProcessRankEntity> findByPidAndName(int pid, String name);
}
