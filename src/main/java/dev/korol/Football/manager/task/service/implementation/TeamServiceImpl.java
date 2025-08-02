package dev.korol.Football.manager.task.service.implementation;

import dev.korol.Football.manager.task.dto.TeamDTO;
import dev.korol.Football.manager.task.dto.TeamResponseDTO;
import dev.korol.Football.manager.task.entity.Team;
import dev.korol.Football.manager.task.repository.TeamRepository;
import dev.korol.Football.manager.task.service.TeamService;
import dev.korol.Football.manager.task.util.TeamMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Korol Artur
 * 31.07.2025
 */
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public TeamResponseDTO createTeam(TeamDTO teamDTO) {
        Team team = teamMapper.toTeam(teamDTO);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toTeamResponseDTO(savedTeam);
    }

    @Override
    public TeamResponseDTO getTeamById(int teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team with id " + teamId + " not found"));
        return teamMapper.toTeamResponseDTO(team);
    }

    @Override
    public List<TeamResponseDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(teamMapper::toTeamResponseDTO).collect(Collectors.toList());
    }

    @Override
    public TeamResponseDTO updateTeam(TeamDTO teamDTO, int teamId) {
        Team existingTeam = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team with id " + teamId + " not found"));
        teamMapper.updateTeamFromDTO(teamDTO, existingTeam);
        Team updatedTeam = teamRepository.save(existingTeam);
        return teamMapper.toTeamResponseDTO(updatedTeam);
    }

    @Override
    public void deleteTeamById(int teamId) {
        teamRepository.deleteById(teamId);
    }
}
