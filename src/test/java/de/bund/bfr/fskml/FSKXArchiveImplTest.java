package de.bund.bfr.fskml;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FSKXArchiveImplTest {

    @Test
    public void test() {
        String exampleReadme = "Example readme bla bla bla ...";

        FSKXArchive archive = new FSKXArchiveImpl(createExampleSimulations(), createExamplePackages(), exampleReadme, "2.0");
        assertNotNull(archive.getSimulations());
        assertNotNull(archive.getPackages());
        assertEquals(exampleReadme, archive.getReadme());
    }

    private SimulationsImpl createExampleSimulations() {
        int selected = 0;

        Map<String, String> defaultSimulation = new HashMap<>();
        defaultSimulation.put("n_iter", "200");
        defaultSimulation.put("Npos", "30");
        defaultSimulation.put("Ntotal", "100");

        Map<String, Map<String, String>> values = new HashMap<>();
        values.put("defaultSimulation", defaultSimulation);

        return new SimulationsImpl(selected, Collections.singletonList("output"), values);
    }

    private PackagesImpl createExamplePackages(){
        String language = "R";
        Map<String,String> packages = new HashMap<>();
        packages.put("triangle","0.12");
        packages.put("ggplot","1.23");
        return new PackagesImpl(language,packages);
    }
}