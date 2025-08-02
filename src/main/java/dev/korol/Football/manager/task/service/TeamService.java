package dev.korol.Football.manager.task.service;

import dev.korol.Football.manager.task.dto.TeamDTO;
import dev.korol.Football.manager.task.dto.TeamResponseDTO;

import java.util.List;

/**
 * @author Korol Artur
 * 31.07.2025
 */

public interface TeamService {
    TeamResponseDTO createTeam(TeamDTO teamDTO);
    TeamResponseDTO getTeamById(int teamId);
    List<TeamResponseDTO> getAllTeams();
    TeamResponseDTO updateTeam(TeamDTO teamDTO, int teamId);
    void deleteTeamById(int teamId);
}
