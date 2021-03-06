package com.anelarge.ontology.util;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.anelarge.ontology.utils.ObjectProperty;
import com.anelarge.ontology.utils.OntologyUtil;
import com.anelarge.utils.io.ResourceUtils;

public class OntologyUtilTest
{
	private static String read(String pathname)
	{
		return ResourceUtils.readResource(pathname, OntologyUtilTest.class);
	}

	@Test
	public void testInitializeOntologyWithFilePath()
			throws Exception
	{

		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLOntology ontology = util.getOntology();
		assertTrue(ontology != null);
	}

	@Test
	public void testInitOntologyWithFile()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLOntology ontology = util.getOntology();
		assertTrue(ontology != null);
	}

	@Test
	public void testInitOntologyWithStream()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLOntology ontology = util.getOntology();
		assertTrue(ontology != null);
	}

	@Test
	public void testAddDataProperty()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLNamedIndividual createInvidual = util.createInvidual("Kisi", "kisi1");
		util.addDataProperty("adi", "sinan", createInvidual);
		util.addDataProperty("soyadi", "kordemir", createInvidual);
		assertTrue(true);
	}

	@Test
	public void testGetIndividualByIndividualByName()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLNamedIndividual createInvidual = util.createInvidual("Kisi", "kisi1");
		util.addDataProperty("adi", "sinan", createInvidual);
		util.addDataProperty("soyadi", "kordemir", createInvidual);

		OWLNamedIndividual individual = util.getIndividual("kisi1");
		assertTrue(individual != null);
	}

	@Test
	public void testGetIndividualByIndividualByOntologyClassName()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLNamedIndividual createInvidual = util.createInvidual("Kisi", "kisi1");
		util.addDataProperty("adi", "sinan", createInvidual);
		util.addDataProperty("soyadi", "kordemir", createInvidual);

		OWLNamedIndividual individual = util.getIndividual("Kisi");
		assertTrue(individual != null);
	}

	@Test
	public void testGetDataProperty()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLNamedIndividual createInvidual = util.createInvidual("Kisi", "kisi1");
		util.addDataProperty("adi", "sinan", createInvidual);
		util.addDataProperty("soyadi", "kordemir", createInvidual);

		OWLDataProperty dataProperty = util.getDataProperty("adi");

		assertTrue(dataProperty != null);
	}

	@Test
	public void testGetMultipleDataProperty()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLNamedIndividual createInvidual = util.createInvidual("Kisi", "kisi1");
		util.addDataProperty("ad", "sinan", createInvidual);
		util.addDataProperty("ad", "kordemir", createInvidual);

		HashMap<String, List<String>> dataPropertiesWithList = util.getDataPropertiesWithList(createInvidual);
		List<String> list = dataPropertiesWithList.get("ad");
		String ad1 = list.get(0);
		String ad2 = list.get(1);
		assertTrue((ad1.equals("sinan") || ad2.equals("sinan")) && (ad1.equals("kordemir") || ad2.equals("kordemir")));
	}

	@Test
	public void testGetDataPropertyByIndividual()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLNamedIndividual createInvidual = util.createInvidual("Kisi", "kisi1");
		util.addDataProperty("adi", "sinan", createInvidual);
		util.addDataProperty("soyadi", "kordemir", createInvidual);

		OWLNamedIndividual individual = util.getIndividual("kisi1");
		HashMap<String, String> dataProperties = util.getDataProperties(individual);

		String adi = dataProperties.get("adi");
		String soyadi = dataProperties.get("soyadi");

		assertTrue(adi.equals("sinan") && soyadi.equals("kordemir"));
	}

	@Test
	public void testAddObjectProperty()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLNamedIndividual createInvidual = util.createInvidual("Kisi", "kisi1");
		util.addDataProperty("adi", "sinan", createInvidual);
		util.addDataProperty("soyadi", "kordemir", createInvidual);

		OWLNamedIndividual createInvidual2 = util.createInvidual("Adres", "adres1");
		util.addDataProperty("il", "ankara", createInvidual2);

		util.addObjectProperty("hasAddress", createInvidual2, createInvidual);

		assertTrue(true);

	}

	@Test
	public void testGetObjectProperty()
			throws Exception
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		OWLNamedIndividual createInvidual = util.createInvidual("Kisi", "kisi1");
		util.addDataProperty("adi", "sinan", createInvidual);
		util.addDataProperty("soyadi", "kordemir", createInvidual);

		OWLNamedIndividual createInvidual2 = util.createInvidual("Adres", "adres1");
		util.addDataProperty("il", "ankara", createInvidual2);

		util.addObjectProperty("hasAddress", createInvidual2, createInvidual);

		HashMap<String, List<OWLNamedIndividual>> objectProperties = util.getObjectProperties(createInvidual);
		List<OWLNamedIndividual> list = objectProperties.get("Adres");

		boolean objectpropertyFound = false;
		for (OWLNamedIndividual owlNamedIndividual : list)
		{
			HashMap<String, String> dataProperties = util.getDataProperties(owlNamedIndividual);
			String string = dataProperties.get("il");
			if (string.equals("ankara"))
			{
				;
			}
			objectpropertyFound = true;
		}
		assertTrue(objectpropertyFound);
	}

	@Test
	public void testGetObjectProperties()
			throws OWLOntologyCreationException, OWLOntologyStorageException
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));

		List<ObjectProperty> objectProperties = util.getObjectProperties();
		String domainName = "Node";
		String rangeName = "Port";
		String objectPropertyName = "nodeHasPort";
		boolean rangeFound = false;
		boolean domainFound = false;
		for (ObjectProperty objectProperty : objectProperties)
		{

			if (objectProperty.name.equals(objectPropertyName))
			{
				List<String> domains = objectProperty.getDomains();
				for (String string : domains)
				{
					if (string.equals(domainName))
					{
						domainFound = true;
					}
				}

				List<String> ranges = objectProperty.getRanges();
				for (String string : ranges)
				{

					if (string.equals(rangeName))
					{
						rangeFound = true;
					}

				}
			}
		}

		assertTrue(domainFound && rangeFound);

	}

	@Test
	public void testPrintOntology()
			throws OWLOntologyCreationException, OWLOntologyStorageException
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));
		util.printOntology();
		assertTrue(true);
	}

	@Test
	public void testSaveOntology()
			throws OWLOntologyCreationException, OWLOntologyStorageException
	{
		OntologyUtil util = new OntologyUtil(read("anelargeBSGS.owl"));

		OutputStream stream2 = new ByteArrayOutputStream();

		util.saveOntology(stream2);

		ByteArrayInputStream bytInput2 = new ByteArrayInputStream(stream2.toString().getBytes());

		OntologyUtil util2 = new OntologyUtil(bytInput2);

		OWLOntology ontology = util2.getOntology();

		assertTrue(ontology != null);
	}
}
