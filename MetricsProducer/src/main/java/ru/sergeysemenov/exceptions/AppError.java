package ru.sergeysemenov.exceptions;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class AppError {
    private String code;
    private String message;
}