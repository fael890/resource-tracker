package com.rafa.resourcetracker.entity.gpu;

public interface GPUMonitor<T extends GPUMonitor<T>> {
    public T gpuUsageMonitor();
}
