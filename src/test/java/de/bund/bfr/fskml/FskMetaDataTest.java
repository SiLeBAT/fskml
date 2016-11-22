package de.bund.bfr.fskml;

import de.bund.bfr.pmfml.ModelClass;
import de.bund.bfr.pmfml.ModelType;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class FskMetaDataTest {

    @Test
    public void test() {
        // Test default values
        FskMetaData metadata = new FskMetaData();
        assertNull(metadata.modelName);
        assertNull(metadata.modelId);
        assertNull(metadata.modelLink);
        assertNull(metadata.organism);
        assertNull(metadata.organismDetails);
        assertNull(metadata.matrix);
        assertNull(metadata.matrixDetails);
        assertNull(metadata.creator);
        assertNull(metadata.familyName);
        assertNull(metadata.contact);
        assertNull(metadata.software);
        assertNull(metadata.referenceDescription);
        assertNull(metadata.referenceDescriptionLink);
        assertNull(metadata.createdDate);
        assertNull(metadata.modifiedDate);
        assertNull(metadata.rights);
        assertNull(metadata.notes);
        assertFalse(metadata.curated);
        assertNull(metadata.type);
        assertEquals(ModelClass.UNKNOWN, metadata.subject);
        assertNull(metadata.foodProcess);
        assertNotNull(metadata.dependentVariable);
        assertNotNull(metadata.independentVariables);
        assertTrue(metadata.independentVariables.isEmpty());
        assertFalse(metadata.hasData);
    }

    @Test
    public void testEquals() {
        FskMetaData fmd = new FskMetaData();
        fmd.modelName = "ESBL Ecoli in Broiler";
        fmd.modelId = "initialize_parents_animals.R";
        fmd.modelLink = "";
        fmd.organism = "Escherichia coli o157:h7 || Broiler";
        fmd.organismDetails = "";
        fmd.matrix = "Broiler";
        fmd.matrixDetails = "Broiler as living organism";
        fmd.creator = "Carolina Plaza-Rodríguez, Guido Correia Carreira";
        fmd.familyName = "";
        fmd.contact = "";
        fmd.software = FskMetaData.Software.R;
        fmd.referenceDescription = "C. Plaza-Rodríguez, H. Sharp, U. Roesler, A. Friese, A. Kaesbohrer (2015), Development of a model for the spread of ESBL/AmpC E.coli in broiler production. Poster presented at the National Symposium on zoonosis Research, Berlin, Germany";
        fmd.referenceDescriptionLink = "";
        fmd.createdDate = new Date(2018, 11, 18);
        fmd.modifiedDate = new Date(2016, 6, 9);
        fmd.rights = "Public";
        fmd.notes = "This module initializes the prevalence of ESBL E.Coli among animals in a parent flock. Initialization is beeing done by drawing a random sample (with a sample size of n.iter) with values between 0 and 100 from a Beta distribution whose parameters are given by the values Npos and Ntotal (see below).";
        fmd.type = ModelType.PRIMARY_MODEL_WODATA;
        fmd.subject = ModelClass.UNKNOWN;
        fmd.foodProcess = "cooking";
        // dep var
        fmd.dependentVariable.name = "Prevalence";
        fmd.dependentVariable.unit = "% (percent)";
        fmd.dependentVariable.type = FskMetaData.DataType.character;
        fmd.dependentVariable.min = "0.0";
        fmd.dependentVariable.max = "100.0";
        // indep vars
        {
            Variable v = new Variable();
            v.name = "n.iter";
            v.unit = "[] (no name)";
            v.type = FskMetaData.DataType.integer;
            v.min = "1";
            v.max = "1000";
            v.value = "200";
            fmd.independentVariables.add(v);
        }
        fmd.hasData = false;

        // equals with same object should return true
        assertTrue(fmd.equals(fmd));

        // equals with null should return false
        assertFalse(fmd.equals(null));

        // equals with a different class object should return false
        assertFalse(fmd.equals(0));

        // equals with matching
        FskMetaData fmd2 = new FskMetaData();
        fmd2.modelName = fmd.modelName;
        fmd2.modelId = fmd.modelId;
        fmd2.modelLink = fmd.modelLink;
        fmd2.organism = fmd.organism;
        fmd2.organismDetails = fmd.organismDetails;
        fmd2.matrix = fmd.matrix;
        fmd2.matrixDetails = fmd.matrixDetails;
        fmd2.creator = fmd.creator;
        fmd2.familyName = fmd.familyName;
        fmd2.contact = fmd.contact;
        fmd2.software = fmd.software;
        fmd2.referenceDescription = fmd.referenceDescription;
        fmd2.referenceDescriptionLink = fmd.referenceDescriptionLink;
        fmd2.createdDate = fmd.createdDate;
        fmd2.modifiedDate = fmd.modifiedDate;
        fmd2.rights = fmd.rights;
        fmd2.notes = fmd.notes;
        fmd2.type = fmd.type;
        fmd2.subject = fmd.subject;
        fmd2.foodProcess = fmd.foodProcess;
        fmd2.dependentVariable = fmd.dependentVariable;
        fmd2.independentVariables = fmd.independentVariables;
        fmd2.hasData = fmd.hasData;
        assertEquals(fmd2, fmd);

        // equals with different model name
        fmd2.modelName = "other model name";
        assertFalse(fmd2.equals(fmd));
        fmd2.modelName = fmd.modelName;

        // equals with different model id
        fmd2.modelId = "other model id";
        assertFalse(fmd2.equals(fmd));
        fmd2.modelId = fmd.modelId;

        // equals with different model link
        fmd2.modelLink = "other model link";
        assertFalse(fmd2.equals(fmd));
        fmd2.modelLink = fmd.modelLink;

        // equals with different organism
        fmd2.organism = "other organism";
        assertFalse(fmd2.equals(fmd));
        fmd2.organism = fmd.organism;

        // equals with different organism details
        fmd2.organismDetails = "other organism details";
        assertFalse(fmd2.equals(fmd));
        fmd2.organismDetails = fmd.organismDetails;

        // equals with different matrix
        fmd2.matrix = "other matrix";
        assertFalse(fmd2.equals(fmd));
        fmd2.matrix = fmd.matrix;

        // equals with different matrix details
        fmd2.matrixDetails = "other matrix details";
        assertFalse(fmd2.equals(fmd));
        fmd2.matrixDetails = fmd.matrixDetails;

        // equals with different creator
        fmd2.creator = "other creator";
        assertFalse(fmd2.equals(fmd));
        fmd2.creator = fmd.creator;

        // equals with different family name
        fmd2.familyName = "other family name";
        assertFalse(fmd2.equals(fmd));
        fmd2.familyName = fmd.familyName;

        // equals with different contact
        fmd2.contact = "other contact";
        assertFalse(fmd2.equals(fmd));
        fmd2.contact = fmd.contact;

        // equals with different software
        fmd2.software = FskMetaData.Software.Matlab;
        assertFalse(fmd2.equals(fmd));
        fmd2.software = fmd.software;

        // equals with different reference description
        fmd2.referenceDescription = "a reference description";
        assertFalse(fmd2.equals(fmd));
        fmd2.referenceDescription = fmd.referenceDescription;

        // equals with different reference description link
        fmd2.referenceDescriptionLink = "http://mynameisralph.com";
        assertFalse(fmd2.equals(fmd));
        fmd2.referenceDescriptionLink = fmd.referenceDescriptionLink;

        // equals with different created date
        fmd2.createdDate = null;
        assertFalse(fmd2.equals(fmd));
        fmd2.createdDate = fmd.createdDate;

        // equals with different modified date
        fmd2.modifiedDate = null;
        assertFalse(fmd2.equals(fmd));
        fmd2.modifiedDate = fmd.modifiedDate;

        // equals with different rights
        fmd2.rights = "other rights";
        assertFalse(fmd2.equals(fmd));
        fmd2.rights = fmd.rights;

        // equals with different notes
        fmd2.notes = "other notes";
        assertFalse(fmd2.equals(fmd));
        fmd2.notes = fmd.notes;

        // equals with different curated status
        fmd2.curated = !fmd.curated;
        assertFalse(fmd2.equals(fmd));
        fmd2.curated = fmd.curated;

        // equals with different type
        fmd2.type = ModelType.MANUAL_TERTIARY_MODEL;
        assertFalse(fmd2.equals(fmd));
        fmd2.type = fmd.type;

        // equals with different subject
        fmd2.subject = ModelClass.AW;
        assertFalse(fmd2.equals(fmd));
        fmd2.subject = fmd.subject;

        // equals with different food process
        fmd2.foodProcess = "other process";
        assertFalse(fmd2.equals(fmd));
        fmd2.foodProcess = fmd.foodProcess;

        // equals with dependent variable
        fmd2.dependentVariable = null;
        assertFalse(fmd2.equals(fmd));
        fmd2.dependentVariable = fmd.dependentVariable;

        // equals with independent variables
        fmd2.independentVariables = null;
        assertFalse(fmd2.equals(fmd));
        fmd2.independentVariables = fmd.independentVariables;

        // equals with different has data
        fmd2.hasData = !fmd.hasData;
        assertFalse(fmd2.equals(fmd));
        fmd2.hasData = !fmd.hasData;
    }

    @Test
    public void testHashCode() throws Exception {
        FskMetaData fmd = new FskMetaData();
        fmd.modelName = "ESBL Ecoli in Broiler";
        fmd.modelId = "initialize_parents_animals.R";
        fmd.modelLink = "";
        fmd.organism = "Escherichia coli o157:h7 || Broiler";
        fmd.organismDetails = "";
        fmd.matrix = "Broiler";
        fmd.matrixDetails = "Broiler as living organism";
        fmd.creator = "Carolina Plaza-Rodríguez, Guido Correia Carreira";
        fmd.familyName = "";
        fmd.contact = "";
        fmd.software = FskMetaData.Software.R;
        fmd.referenceDescription = "C. Plaza-Rodríguez, H. Sharp, U. Roesler, A. Friese, A. Kaesbohrer (2015), Development of a model for the spread of ESBL/AmpC E.coli in broiler production. Poster presented at the National Symposium on zoonosis Research, Berlin, Germany";
        fmd.referenceDescriptionLink = "";
        fmd.createdDate = new Date(2018, 11, 18);
        fmd.modifiedDate = new Date(2016, 6, 9);
        fmd.rights = "Public";
        fmd.notes = "This module initializes the prevalence of ESBL E.Coli among animals in a parent flock. Initialization is beeing done by drawing a random sample (with a sample size of n.iter) with values between 0 and 100 from a Beta distribution whose parameters are given by the values Npos and Ntotal (see below).";
        fmd.type = ModelType.PRIMARY_MODEL_WODATA;
        fmd.subject = ModelClass.UNKNOWN;
        fmd.foodProcess = "cooking";
        // dep var
        fmd.dependentVariable.name = "Prevalence";
        fmd.dependentVariable.unit = "% (percent)";
        fmd.dependentVariable.type = FskMetaData.DataType.character;
        fmd.dependentVariable.min = "0.0";
        fmd.dependentVariable.max = "100.0";
        // indep vars
        {
            Variable v = new Variable();
            v.name = "n.iter";
            v.unit = "[] (no name)";
            v.type = FskMetaData.DataType.integer;
            v.min = "1";
            v.max = "1000";
            v.value = "200";
            fmd.independentVariables.add(v);
        }
        fmd.hasData = false;

        FskMetaData fmd2 = new FskMetaData();
        fmd2.modelName = fmd.modelName;
        fmd2.modelId = fmd.modelId;
        fmd2.modelLink = fmd.modelLink;
        fmd2.organism = fmd.organism;
        fmd2.organismDetails = fmd.organismDetails;
        fmd2.matrix = fmd.matrix;
        fmd2.matrixDetails = fmd.matrixDetails;
        fmd2.creator = fmd.creator;
        fmd2.familyName = fmd.familyName;
        fmd2.contact = fmd.contact;
        fmd2.software = fmd.software;
        fmd2.referenceDescription = fmd.referenceDescription;
        fmd2.referenceDescriptionLink = fmd.referenceDescriptionLink;
        fmd2.createdDate = fmd.createdDate;
        fmd2.modifiedDate = fmd.modifiedDate;
        fmd2.rights = fmd.rights;
        fmd2.notes = fmd.notes;
        fmd2.type = fmd.type;
        fmd2.subject = fmd.subject;
        fmd2.foodProcess = fmd.foodProcess;
        fmd2.dependentVariable = fmd.dependentVariable;
        fmd2.independentVariables = fmd.independentVariables;
        fmd2.hasData = fmd.hasData;

        assertTrue(fmd.hashCode() == fmd2.hashCode());
    }
}
