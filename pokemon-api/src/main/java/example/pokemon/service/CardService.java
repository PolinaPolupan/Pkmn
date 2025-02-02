package example.pokemon.service;

import example.pokemon.dto.CardsPage;
import example.pokemon.exception.CardNotFoundException;
import example.pokemon.exception.StudentNotFoundException;
import example.pokemon.dto.CardDto;
import example.pokemon.dto.StudentDto;
import example.pokemon.exception.DuplicateCardException;
import example.pokemon.mapper.CardMapper;
import example.pokemon.model.Card;
import example.pokemon.model.Student;
import example.pokemon.repository.CardRepository;
import example.pokemon.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final StudentRepository studentRepository;
    private final CardMapper cardMapper;

    public void save(CardDto card) {
        Optional<Card> existingCard = cardRepository.findByName(card.getName());
        existingCard.ifPresent(c ->
            { throw new DuplicateCardException("A card with the same name already exists."); }
        );

        if (card.getPokemonOwner() == null) {
            throw new StudentNotFoundException("Invalid null student");
        }

        studentRepository.findByFirstNameAndLastNameAndStudentGroup(
                card.getPokemonOwner().getFirstName(),
                card.getPokemonOwner().getLastName(),
                card.getPokemonOwner().getStudentGroup()).ifPresent(c ->
                { throw new DuplicateCardException("A card with the same owner already exists."); }
        );

        cardRepository.save(cardMapper.mapDtoToCard(card));
    }

    public CardsPage getAll(Pageable page) {

        Page<Card> pagedResult = cardRepository.findAll(page);
        List<CardDto> cards = pagedResult
                .getContent()
                .stream()
                .map(cardMapper::mapToDto)
                .toList();

        CardsPage response = new CardsPage();
        response.setPage(pagedResult.getNumber());
        response.setTotal(pagedResult.getTotalElements());
        response.setPages(pagedResult.getTotalPages());
        response.setCards(cards);

        return response;
    }

    public CardDto getByName(String name) {
        Card card = cardRepository.findByName(name).orElseThrow(
            () -> { throw new CardNotFoundException("Card not found"); }
        );
        return cardMapper.mapToDto(card);
    }

    public CardDto getByOwner(UUID ownerId) {
        Student student = studentRepository.findById(ownerId).orElseThrow(
            () -> { throw new StudentNotFoundException("Student not found"); }
        );

        Card card = cardRepository.findByPokemonOwner(student).orElseThrow(
            () -> { throw new CardNotFoundException("Card not found"); }
        );

        return cardMapper.mapToDto(card);
    }

    public CardDto getById(UUID id) {
        Card card = cardRepository.findById(id).orElseThrow(
                () -> { throw new CardNotFoundException("Card not found"); }
        );
        return cardMapper.mapToDto(card);
    }
}
