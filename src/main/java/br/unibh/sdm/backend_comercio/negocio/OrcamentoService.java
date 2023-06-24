package br.unibh.sdm.backend_comercio.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_comercio.entidades.Orcamento;
import br.unibh.sdm.backend_comercio.persistencia.OrcamentoRepository;

/**
 * Classe contendo a lógica de negócio para Cotação
 * @author jhcru
 *
 */
@Service
public class OrcamentoService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    
    private final OrcamentoRepository orcamentoRepo;

    public OrcamentoService(OrcamentoRepository orcamentoRepository){
        this.orcamentoRepo=orcamentoRepository;
    }
    
    public List<Orcamento> getOrcamentos(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os Orcamentos");
        }
        Iterable<Orcamento> lista = this.orcamentoRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Orcamento>();
        }
        return IteratorUtils.toList(lista.iterator());
    }  

    public Orcamento getOrcamentoById(String id) {
        if (logger.isInfoEnabled())
            logger.info("Buscando Orcamento com o id {}", id);

        Optional<Orcamento> orcamento = this.orcamentoRepo.findById(id);

        if (!orcamento.isPresent())
            throw new RuntimeException("Orcamento com o id " + id + " nao encontrada");

        return orcamento.get();
    }
    

    public Orcamento criandoOrcamento(Orcamento orcamento){
        if(logger.isInfoEnabled()){
            logger.info("Orcamento criado {}",orcamento.toString());
        }
        return this.orcamentoRepo.save(orcamento);
    } 
    
    public Orcamento saveOrcamento(Orcamento orcamento){
        if(logger.isInfoEnabled()){
            logger.info("Orcamento alterado para {}",orcamento.toString());
        }
        return this.orcamentoRepo.save(orcamento);
    }
    
    public void deleteOrcamentoById(String id) {
        if (logger.isInfoEnabled()) {
            logger.info("Excluindo Orcamento com id {}", id);
        }

        Optional<Orcamento> orcamento = this.orcamentoRepo.findById(id);

        if (!orcamento.isPresent())
            throw new RuntimeException("Orcamento com o id " + id + " nao encontrada");

        this.orcamentoRepo.deleteById(id);
    }

    

}