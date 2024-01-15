package org.br.mineradora.dto;

import io.quarkus.arc.All;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@Jacksonized
public class QuotationDTO {

    private LocalDateTime date;

    private BigDecimal currencyPrice;
}
