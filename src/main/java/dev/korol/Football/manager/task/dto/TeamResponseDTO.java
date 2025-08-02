package dev.korol.Football.manager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Korol Artur
 * 01.08.2025
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDTO {

    private int teamId;
    private String teamName;
    private BigDecimal balance;
    private int transferCommissionPercent;
    private List<PlayerResponseDTO> players;

}
