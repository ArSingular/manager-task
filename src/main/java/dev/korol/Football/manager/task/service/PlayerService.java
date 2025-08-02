package dev.korol.Football.manager.task.service;

import dev.korol.Football.manager.task.dto.PlayerDTO;
import dev.korol.Football.manager.task.dto.PlayerResponseDTO;

import java.util.List;

/**
 * @author Korol Artur
 * 31.07.2025
 */

public interface PlayerService {
    PlayerResponseDTO createPlayer(PlayerDTO playerDTO);
    PlayerResponseDTO getPlayerById(int playerId);
    PlayerResponseDTO updatePlayer(PlayerDTO playerDTO, int playerId);
    void deletePlayerById(int playerId);
    List<PlayerResponseDTO> getAllPlayers();
    PlayerResponseDTO transferPlayer(int playerId, int toTeamId);
}
