package dev.korol.Football.manager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Korol Artur
 * 01.08.2025
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDTO {

    private int playerId;
    private String name;
    private String surname;
    private int age;
    private int experience;
    private String teamName;

}
