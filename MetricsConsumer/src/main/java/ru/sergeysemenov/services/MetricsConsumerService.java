package ru.sergeysemenov.services;

import ru.sergeysemenov.dto.MetricDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsConsumerService {
    private final MetricService metricService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "metrics", groupId = "metrics-consumer")
    public void listen(String metric) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MetricDTO metricDTO = mapper.readValue(metric, MetricDTO.class);
        metricService.addMetric(metricDTO);
        log.info("Received metric: {}", metric);
    }
}
