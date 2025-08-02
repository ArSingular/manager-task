package dev.korol.Football.manager.task.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Korol Artur
 * 31.07.2025
 */

@Getter
@Setter
public class TeamDTO {

    @NotBlank(message = "Team name cannot be empty")
    private String teamName;

    @DecimalMin(value = "0.0", message = "Balance cannot be negative")
    private BigDecimal balance;

    @Min(value = 0, message = "Transfer commission percent cannot be negative")
    @Max(value = 100, message = "Transfer commission percent cannot exceed 100")
    private int transferCommissionPercent;

}
