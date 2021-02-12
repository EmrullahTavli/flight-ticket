package com.finartz.flightticket.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    @NotNull
    private Long id;

    @NotBlank
    private String pnrNo;

    @NotBlank
    private String airlineName;

    @NotBlank
    private String flightNo;

    @NotBlank
    private String departureLine;

    @NotBlank
    private String arrivalLine;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @NotBlank
    private String creditCardNumber;
}
