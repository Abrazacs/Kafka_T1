package ru.sergeysemenov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.sergeysemenov.model.Metric;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergeysemenov.services.MetricService;


import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/metrics")
@Slf4j
public class MetricConsumerController {
    private final MetricService metricService;

    @Operation(summary = "Запрос на предоставления имен метрик, которые доступны в настоящий момент")
    @GetMapping("/")
    public Set<String> getAvailableMetrics() {
        Set<String> availableMetrics = metricService.getAvailableMetrics();
        return availableMetrics;
    }

    @Operation(
            summary = "Запрос на предоставления метрики по имени",
            responses = @ApiResponse(
                    description = "Успешный ответ", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Metric.class))
            )
    )
    @GetMapping("/{name}")
    public Metric getMetricByName(@PathVariable String name) {
        Metric metric = metricService.getMetricByName(name);
        return metric;
    }
}
