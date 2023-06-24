package br.unibh.sdm.backend_comercio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.unibh.sdm.backend_comercio.entidades.Orcamento;
import br.unibh.sdm.backend_comercio.persistencia.OrcamentoRepository;

/**
 * Classe de testes para a entidade Cotacao.
 *  <br>
 * Para rodar, antes sete a seguinte variável de ambiente: -Dspring.config.location=C:/Users/jhcru/sdm/
 *  <br>
 * Neste diretório, criar um arquivo application.properties contendo as seguitnes variáveis:
 * <br>
 * amazon.aws.accesskey=<br>
 * amazon.aws.secretkey=<br>
 * @author jhcru
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, OrcamentoTests.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrcamentoTests {

    private static Logger LOGGER = LoggerFactory.getLogger(OrcamentoTests.class);
	    
    @Configuration
	@EnableDynamoDBRepositories(basePackageClasses = { OrcamentoRepository.class })
	public static class DynamoDBConfig {

		@Value("${amazon.aws.accesskey}")
		private String amazonAWSAccessKey;

		@Value("${amazon.aws.secretkey}")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}
    
	@Autowired
	private OrcamentoRepository repository;	

	@Test
	public void teste1Criacao() throws ParseException{
		


		LOGGER.info("Criando objetos Orcamentos ...");
		Orcamento c1 = new Orcamento("123","lucas","Mesa quebrada",33.0);
		repository.save(c1);

		Orcamento c2 = new Orcamento("321","lucas","Mesa quebrada",22.0);
		repository.save(c2);
		
		LOGGER.info("Pesquisado todos Orcamentos ");
		Iterable<Orcamento> lista = repository.findAll();
		assertNotNull(lista.iterator());

		for (Orcamento orcamento : lista) {
			LOGGER.info(orcamento.toString());
		}

		List<Orcamento> orcamentos = IteratorUtils.toList(lista.iterator());

		assertEquals(orcamentos.size(), 2);
		LOGGER.info("Encontrado: {}", orcamentos.size());
	}
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos Orcamentos...");

		Iterable<Orcamento> resultAntesDeDeletar = repository.findAll();

		for (Orcamento orcamento : resultAntesDeDeletar) {
			LOGGER.info("Excluindo orcamento  = " + orcamento.getNome());
			repository.delete(orcamento);
		}

		List<Orcamento> resultDepoisDeDeletar = IteratorUtils.toList(repository.findAll().iterator());

		assertEquals(resultDepoisDeDeletar.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}

	

	
	
	
}
