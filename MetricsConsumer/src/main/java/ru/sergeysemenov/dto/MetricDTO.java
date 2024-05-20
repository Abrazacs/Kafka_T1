package ru.sergeysemenov.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MetricDTO {

    private String name;
    private String description;
    private String baseUnit;
    private MeasurementDTO[] measurements;
    private Object availableTags;

}
