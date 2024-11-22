package com.coursework.modforge.service;

import com.coursework.modforge.dto.GameCreationDto;
import com.coursework.modforge.dto.GameDto;
import com.coursework.modforge.entity.Game;
import com.coursework.modforge.exception.GameNotFoundException;
import com.coursework.modforge.mapper.GameMapper;
import com.coursework.modforge.repository.GameRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public Page<GameDto> getAll(Long categoryId, Pageable pageable) {
        Specification<Game> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (categoryId != null) {
                predicates.add(criteriaBuilder.equal(root.join("category").get("id"), categoryId));
            }

            return predicates.isEmpty() ? null : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<Game> games = gameRepository.findAll(specification, pageable);
        return games.map(gameMapper::toDto);
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
