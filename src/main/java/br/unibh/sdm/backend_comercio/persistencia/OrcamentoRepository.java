package br.unibh.sdm.backend_comercio.persistencia;


import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_comercio.entidades.Orcamento;

/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
public interface OrcamentoRepository extends CrudRepository<Orcamento, String> {
	
	Optional<Orcamento> findById(String id);
	
}
