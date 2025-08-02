package dev.korol.Football.manager.task.controller;

import dev.korol.Football.manager.task.dto.TeamDTO;
import dev.korol.Football.manager.task.dto.TeamResponseDTO;
import dev.korol.Football.manager.task.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Korol Artur
 * 31.07.2025
 */

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/")
    public ResponseEntity<TeamResponseDTO> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        TeamResponseDTO createdTeam = teamService.createTeam(teamDTO);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable int teamId) {
        TeamResponseDTO teamResponseDTO = teamService.getTeamById(teamId);
        return new ResponseEntity<>(teamResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<TeamResponseDTO> teamDTOList = teamService.getAllTeams();
        return new ResponseEntity<>(teamDTOList, HttpStatus.OK);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable int teamId, @Valid @RequestBody TeamDTO teamDTO) {
        TeamResponseDTO updatedTeam = teamService.updateTeam(teamDTO, teamId);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Integer> deleteTeam(@PathVariable int teamId) {
        teamService.deleteTeamById(teamId);
        return ResponseEntity.ok(teamId);
    }

}
