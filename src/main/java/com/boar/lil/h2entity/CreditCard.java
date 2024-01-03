package com.boar.lil.h2entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    public Long id;

    @NotNull
    @Max(value = 9223372036854775807L, message = "CVV must be at most 99999999999999999999")
    public Long CardNumber;

    @NotNull
    @Size(min = 1, max = 16)
    public String FirstName;

    @NotNull
    @Size(min = 1, max = 16)
    public String LastName;

    public String Address;

    @Size(min = 1, max = 64)
    public String PhoneNumber;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/YYYY")
    public String ExpireDate;

    @NotNull
    @Min(value = 100, message = "CVV must be at least 100")
    @Max(value = 999, message = "CVV must be at most 999")
    public int CVV;

    public String CreatedDate;

    public String UpdatedDate;
}