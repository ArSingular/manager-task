package dev.korol.Football.manager.task.util;

import dev.korol.Football.manager.task.dto.PlayerDTO;
import dev.korol.Football.manager.task.dto.PlayerResponseDTO;
import dev.korol.Football.manager.task.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Korol Artur
 * 31.07.2025
 */

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
@Component
public interface PlayerMapper {

    Player toPlayer(PlayerDTO playerDTO);
    void updatePlayerFromDTO(PlayerDTO playerDTO, @MappingTarget Player player);

    @Mapping(source = "team.teamName", target = "teamName")
    PlayerResponseDTO toPlayerResponseDTO(Player player);
}
