package ru.sergeysemenov.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetricDTO {

    private String name;

    private String description;
    private String baseUnit;
    private MeasurementDTO[] measurements;
    private Object availableTags;

}
