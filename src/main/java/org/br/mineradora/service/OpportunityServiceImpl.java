package org.br.mineradora.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.entity.OpportunityEntity;
import org.br.mineradora.entity.QuotationEntity;
import org.br.mineradora.repository.OpportunityRepository;
import org.br.mineradora.repository.QuotationRepository;
import org.br.mineradora.utils.CSVHelper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.br.mineradora.utils.CSVHelper.OpportunitiesToCSV;

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
    @Transactional
    public void saveQuotation(QuotationDTO quotation) {

        QuotationEntity createQuotation = new QuotationEntity();
        createQuotation.setDate(LocalDateTime.now());
        createQuotation.setCurrencyPrice(quotation.getCurrencyPrice());

        quotationRepository.persist(createQuotation);


    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return null;
    }

    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() throws IOException {

        List<OpportunityDTO> opportunityList = new ArrayList<>();

        opportunityRepository.findAll().list().forEach(item -> {
            opportunityList.add(OpportunityDTO.builder()
                    .proposalId(item.getProposalId())
                    .customer(item.getCustomer())
                    .priceTonne(item.getPriceTonne())
                    .lastDollarQuotation(item.getLastDollarQuotation())
                    .build());
        });

        return CSVHelper.OpportunitiesToCSV(opportunityList);
    }
}
