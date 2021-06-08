package hust.soict.hedspi.backend.web.controller;

import hust.soict.hedspi.backend.dto.*;
import hust.soict.hedspi.backend.service.CardService;
import hust.soict.hedspi.backend.web.exception.CardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {
    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("/cards/all")
    public ResponseEntity<List<CardBriefDto>> getAllCardBrief(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String provider
    ) {
        try {
            return new ResponseEntity<>(
                    this.service.getAllCardBrief(category, provider),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cards")
    public ResponseEntity<Page<CardBriefDto>> getPageCardBrief(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String provider,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "8") int size
    ) {
        try {
            return new ResponseEntity<>(
                    this.service.getPageCardBrief(category, provider, page, size),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardDetailDto> getCardDetailById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    this.service.getCardDetailById(id),
                    HttpStatus.OK);
        } catch (CardNotFoundException cnfe) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cards")
    public ResponseEntity<CardDetailDto> createNewCard(@RequestBody CardFormDto formDto) {
        try {
            return new ResponseEntity<>(
                    this.service.createNewCard(formDto),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<CardDetailDto> updateCard(
            @PathVariable Long id,
            @RequestBody CardFormDto formDto
    ) {
        try {
            return new ResponseEntity<>(
                    this.service.updateCard(id, formDto),
                    HttpStatus.OK
            );
        } catch (CardNotFoundException cnfe) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        try {
            this.service.deleteCardById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CardNotFoundException cnfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/comparison/cards")
    public ResponseEntity<List<CardComparisonDto>> getListCardComparisonByIds(
            @RequestParam String ids
    ) {
        try {
            return new ResponseEntity<>(
                    this.service.getListCardComparisonByIds(ids),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/cards")
    public ResponseEntity<Page<CardBriefDto>> searchCardBrief(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "1", required = false) int size,
            @RequestBody CardSearchDto searchDto
    ) {
        try {
            return new ResponseEntity<>(
                    this.service.searchCardBrief(searchDto, page, size),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
