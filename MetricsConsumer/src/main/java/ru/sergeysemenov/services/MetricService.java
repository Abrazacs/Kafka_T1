package ru.sergeysemenov.services;

import ru.sergeysemenov.dto.MetricDTO;
import lombok.RequiredArgsConstructor;
import ru.sergeysemenov.model.Metric;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MetricService {
    private Map<String, Metric> metrics = new HashMap<>();

    public void addMetric(MetricDTO metricDTO) {
        Metric metric = new Metric(metricDTO);
        metrics.put(metric.getName(), metric);
    }

    public Set<String> getAvailableMetrics() {
        return metrics.keySet();
    }

    public Metric getMetricByName(String name) {
        return metrics.get(name);
    }


}
