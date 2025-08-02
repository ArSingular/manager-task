package dev.korol.Football.manager.task.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Korol Artur
 * 31.07.2025
 */


@Getter
@Setter
public class PlayerDTO {

    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotBlank(message = "Surname can't be empty")
    private String surname;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @Min(value = 0, message = "Experience must be positive or 0")
    private int experience;

    @Min(value = 1, message = "Team ID must be provided and > 0")
    private int playerTeamId;


}
