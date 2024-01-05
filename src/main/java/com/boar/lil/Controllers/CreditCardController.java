package com.boar.lil.Controllers;

import com.boar.lil.Services.*;
import com.boar.lil.h2entity.CreditCard;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreditCardController {

    private final ICreditCardService service;

    @Autowired
    public CreditCardController(ICreditCardService service) {
        this.service = service;
    }

    @GetMapping("/creditcards")
    public List<Integer> findAllCreditCards() {
        return service.GetAll();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CreditCard.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/creditcard/{id}")
    public CreditCard getCreditCard(@PathVariable int id) {
        return service.Get(id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CreditCard.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/creditcards")
    public ResponseEntity<Long> addCreditCard(@Valid @RequestBody CreditCard creditCard) {
        long generatedId = service.Insert(creditCard);
        return new ResponseEntity<>(generatedId, HttpStatus.CREATED);
    }

    @PutMapping("/creditcards")
    public int updateCreditCard(@RequestBody int creditCard) {
        return service.Update();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CreditCard.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/creditcards/{id}")
    public String deleteCreditCard(@PathVariable int id) {
        return service.Delete(id);
    }
}