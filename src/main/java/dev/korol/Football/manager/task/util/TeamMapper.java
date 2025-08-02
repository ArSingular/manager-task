package dev.korol.Football.manager.task.util;

import dev.korol.Football.manager.task.dto.TeamDTO;
import dev.korol.Football.manager.task.dto.TeamResponseDTO;
import dev.korol.Football.manager.task.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Korol Artur
 * 31.07.2025
 */

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,  uses = PlayerMapper.class)
@Component
public interface TeamMapper {

    Team toTeam(TeamDTO teamDTO);
    void updateTeamFromDTO(TeamDTO teamDTO, @MappingTarget Team team);

    @Mapping(source = "players", target = "players")
    TeamResponseDTO toTeamResponseDTO(Team team);
}
