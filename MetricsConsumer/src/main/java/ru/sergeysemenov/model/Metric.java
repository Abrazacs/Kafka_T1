package ru.sergeysemenov.model;

import ru.sergeysemenov.dto.MeasurementDTO;
import ru.sergeysemenov.dto.MetricDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Metric {

    private String name;
    private String unitOfMeasure;
    private Double value;

    public Metric(MetricDTO metricDTO) {
        this.name = metricDTO.getName();
        this.unitOfMeasure = metricDTO.getBaseUnit();
        MeasurementDTO[] measurements = metricDTO.getMeasurements();
        this.value = measurements[0].getValue();
    }
}
