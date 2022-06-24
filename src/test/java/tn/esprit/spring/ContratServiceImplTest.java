package tn.esprit.spring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IContratService;

@SpringBootTest
class ContratServiceImplTest {

	private static final Logger l = LogManager.getLogger(ContratServiceImplTest.class);

	@Autowired
	IContratService contratService;

	Contrat Contrat;

	@Test
	@Order(1)
	public void testAddContrat() {

		l.info("***Test Add Contract ***");
		Contrat contrat = new Contrat();
		contrat.setSalaire(1800);
		contrat.setTypeContrat("CDI");
		contrat.setDateDebut(new Date(Calendar.DAY_OF_MONTH));
		contrat = contratService.addContrat(contrat);
		this.Contrat = contrat;
		l.info("*****Test contrat ajouté *****");
		try {
			assertTrue(Contrat != null);
			l.info("*****Contrat ajouté *****");
			l.info(Contrat.toString());
		} catch (Throwable e) {
			l.error("Contart non Ajouté");
		}

	}

	
	@Order(2)
	@Test
	public void retrieveContratWithWrongId() {
		Long ref = (long) 0;
		Contrat m = contratService.getContratById(ref);
		assertFalse(m instanceof Contrat);
	}
	
	@Order(3)
	@Test
	public void ContratDeleteTest() {
		l.info("////////Pursuing Persistence Tests/////////////////");
		l.info("////////////Deleting Contrat from DB///////////////");
		Contrat contrat = new Contrat();
		contrat.setSalaire(1800);
		contrat.setTypeContrat("CDI");
		contrat.setDateDebut(new Date(Calendar.DAY_OF_MONTH));
		contrat = contratService.addContrat(contrat);
		try {
			
			contratService.deleteContratById(contrat.getReference());
		} catch (Exception e) {
			l.error(e.getMessage());
		}

		assertTrue((contratService.getContratById(contrat.getReference()) == null));
	}
}
