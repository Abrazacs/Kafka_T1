package ru.sergeysemenov.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.sergeysemenov.dto.MetricDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sergeysemenov.exceptions.NotFoundException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsProducerService {
    private final static String METRICS_URI = "http://localhost:8080/actuator/metrics/";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper;

    public MetricDTO getMetric(String metricEndpoint) throws IOException, InterruptedException {
        HttpResponse<String> response = getHttpResponseFromActuatorMetricsEndpoint(metricEndpoint);
        if (response.statusCode() != 200) {
            throw new NotFoundException("metricEndpoint: "+metricEndpoint+" haven't been found");
        }
        return mapper.readValue(response.body(), MetricDTO.class);
    }

    private HttpResponse<String> getHttpResponseFromActuatorMetricsEndpoint(String metricEndpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().
                GET()
                .uri(java.net.URI.create(METRICS_URI + metricEndpoint))
                .build();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
