package org.br.mineradora.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.entity.OpportunityEntity;
import org.br.mineradora.entity.QuotationEntity;
import org.br.mineradora.repository.OpportunityRepository;
import org.br.mineradora.repository.QuotationRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService{

    @Inject
    OpportunityRepository opportunityRepository;

    @Inject
    QuotationRepository quotationRepository;

    @Override
    public void buildOpportunity(ProposalDTO proposal) {

        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();
        Collections.reverse(quotationEntities);

        OpportunityEntity opportunity = new OpportunityEntity();
        opportunity.setDate(LocalDateTime.now());
        opportunity.setProposalId(proposal.getProposalID());
        opportunity.setCustomer(proposal.getCustomer());
        opportunity.setPriceTonne(proposal.getPriceTonne());
        opportunity.setLastDollarQuotation(quotationEntities.get(0).getCurrencyPrice());

        opportunityRepository.persist(opportunity);
    }

    @Override
    public void saveQuotation(QuotationDTO quotation) {




    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return null;
    }
}
