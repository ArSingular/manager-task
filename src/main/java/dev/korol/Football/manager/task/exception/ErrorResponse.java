package dev.korol.Football.manager.task.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Korol Artur
 * 31.07.2025
 */

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;
    private String details;
    private Date timestamp;

}
