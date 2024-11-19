package com.coursework.modforge.service;

import com.coursework.modforge.dto.GameCreationDto;
import com.coursework.modforge.dto.GameDto;
import com.coursework.modforge.entity.Game;
import com.coursework.modforge.exception.GameNotFoundException;
import com.coursework.modforge.mapper.GameMapper;
import com.coursework.modforge.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameDto getById(Long id){
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
        return gameMapper.toDto(game);
    }

    public List<GameDto> getAll(){
        List<Game> games = gameRepository.findAll();
        return  games.stream()
                .map(gameMapper::toDto)
                .toList();
    }

    @Transactional
    public GameDto create(GameCreationDto gameCreationDto){
        return gameMapper.toDto(gameRepository.save(gameMapper.toEntity(gameCreationDto)));
    }

    @Transactional
    public GameDto update(Long id, GameDto gameDto) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
        gameMapper.partialUpdate(gameDto, game);
        game.setTitle(gameDto.title());
        return gameMapper.toDto(gameRepository.save(game));
    }

    @Transactional
    public void delete(Long id) {
        gameRepository.deleteById(id);
    }
}
