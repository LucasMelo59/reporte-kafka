package org.br.mineradora.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "opportunity")
@Data
@NoArgsConstructor
public class OpportunityEntity {

    @Id
    @GeneratedValue
    Long id;

    private LocalDateTime date;
    @Column(name = "proposal_id")
    private Long proposalId;
    private String customer;
    @Column(name = "price_tonne")
    private BigDecimal priceTonne;
    @Column(name = "last_currency_quotation")
    private BigDecimal lastDollarQuotation;


}
