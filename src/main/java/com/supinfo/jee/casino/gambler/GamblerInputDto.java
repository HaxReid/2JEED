package com.supinfo.jee.casino.gambler;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamblerInputDto {
    private String pseudo;
    private String password;

}