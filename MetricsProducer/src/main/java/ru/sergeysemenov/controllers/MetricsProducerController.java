package ru.sergeysemenov.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sergeysemenov.dto.MetricDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergeysemenov.exceptions.AppError;
import ru.sergeysemenov.services.MetricsProducerService;


import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/metrics")
@Slf4j
public class MetricsProducerController {

    private final MetricsProducerService metricsProducerService;
    private final KafkaTemplate<String, MetricDTO> kafkaTemplate;

    @Operation(
            summary = "Запрос на отправку метрик в кафку",
            description = "Post метод для отправки метрик в кафку, в качестве переменно name использует" +
                    "имена endpoint'ов, доступных в Spring actuator",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Метрика с заданным именем не существует", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )

    @PostMapping("/{name}")
    public ResponseEntity<HttpStatus> sendMetric(@PathVariable String name) throws IOException, InterruptedException {
        log.info("sending metric: "+name);
        MetricDTO metric = metricsProducerService.getMetric(name);
        kafkaTemplate.send("metrics", metric.getName(), metric);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
