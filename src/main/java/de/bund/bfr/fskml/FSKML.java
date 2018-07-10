package de.bund.bfr.fskml;

import de.unirostock.sems.cbarchive.CombineArchive;
import de.unirostock.sems.cbarchive.meta.MetaDataObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FSKML {

    public static Map<String, URI> getURIS(int major, int minor, int patch) {

        Map<String, URI> uris = new HashMap<>();

        try {
            if (major == 1 && minor == 0 && (patch == 0 || patch == 2 || patch == 3)) {
                uris.put("r", new URI("http://www-r-project.org"));
                uris.put("zip", new URI("http://www.iana.org/assignments/media-types/application/zip"));
            } else if (major == 1 && minor == 0 && (patch == 4 || patch == 5 || patch == 6 || patch == 7)) {
                uris.put("zip", new URI("http://purl.org/NET/mediatypes/application/zip"));
                uris.put("tgz", new URI("http://purl.org/NET/mediatypes/application/x-tgz"));
                uris.put("tar_gz", new URI("http://purl.org/NET/mediatypes/application/x-tar.gz"));
                uris.put("r", new URI("http://purl.org/NET/mediatypes/application/r"));
                uris.put("pmf", new URI("http://purl.org/NET/mediatypes/application/x-pmf"));
                uris.put("sbml", new URI("http://purl.org/NET/mediatypes/application/sbml+xml"));
                uris.put("matlab", new URI("http://purl.org/NET/mediatypes/text/x-matlab"));
                uris.put("php", new URI("http://purl.org/NET/mediatypes/text/x-php"));
            } else if (major == 1 && minor == 0 && (patch == 8 || patch == 9)) {
                uris.put("zip", new URI("http://purl.org/NET/mediatypes/application/zip"));
                uris.put("tgz", new URI("http://purl.org/NET/mediatypes/application/x-tgz"));
                uris.put("tar_gz", new URI("http://purl.org/NET/mediatypes/application/x-tar.gz"));
                uris.put("r", new URI("http://purl.org/NET/mediatypes/application/r"));
                uris.put("pmf", new URI("http://purl.org/NET/mediatypes/application/x-pmf"));
                uris.put("sbml", new URI("http://purl.org/NET/mediatypes/application/sbml+xml"));
                uris.put("json", new URI("https://wwww.iana.org/assignments/media-types/application/json"));
                uris.put("matlab", new URI("http://purl.org/NET/mediatypes/text/x-matlab"));
                uris.put("php", new URI("http://purl.org/NET/mediatypes/text/x-php"));
                uris.put("plain", new URI("http://purl.org/NET/mediatypes/text-xplain"));
                uris.put("rdata", new URI("http://purl.org/NET/mediatypes/x-RData"));
            } else if (major == 1 && minor == 0 && patch == 10) {
                uris.put("zip", new URI("http://purl.org/NET/mediatypes/application/zip"));
                uris.put("tgz", new URI("http://purl.org/NET/mediatypes/application/x-tgz"));
                uris.put("tar_gz", new URI("http://purl.org/NET/mediatypes/application/x-tar.gz"));
                uris.put("r", new URI("http://purl.org/NET/mediatypes/application/r"));
                uris.put("pmf", new URI("http://purl.org/NET/mediatypes/application/x-pmf"));
                uris.put("sbml", new URI("http://purl.org/NET/mediatypes/application/sbml+xml"));
                uris.put("json", new URI("https://wwww.iana.org/assignments/media-types/application/json"));
                uris.put("matlab", new URI("http://purl.org/NET/mediatypes/text/x-matlab"));
                uris.put("php", new URI("http://purl.org/NET/mediatypes/text/x-php"));
                uris.put("plain", new URI("http://purl.org/NET/mediatypes/text-xplain"));
                uris.put("rdata", new URI("http://purl.org/NET/mediatypes/x-RData"));
                uris.put("csv", new URI("https://www.iana.org/assignments/media-types/text/csv"));
            } else if (major == 1 && minor == 0 && patch == 11) {
                uris.put("zip", new URI("http://purl.org/NET/mediatypes/application/zip"));
                uris.put("tgz", new URI("http://purl.org/NET/mediatypes/application/x-tgz"));
                uris.put("tar_gz", new URI("http://purl.org/NET/mediatypes/application/x-tar.gz"));
                uris.put("r", new URI("http://purl.org/NET/mediatypes/application/r"));
                uris.put("pmf", new URI("http://purl.org/NET/mediatypes/application/x-pmf"));
                uris.put("sbml", new URI("http://purl.org/NET/mediatypes/application/sbml+xml"));
                uris.put("json", new URI("https://wwww.iana.org/assignments/media-types/application/json"));
                uris.put("matlab", new URI("http://purl.org/NET/mediatypes/text/x-matlab"));
                uris.put("php", new URI("http://purl.org/NET/mediatypes/text/x-php"));
                uris.put("plain", new URI("http://purl.org/NET/mediatypes/text-xplain"));
                uris.put("rdata", new URI("http://purl.org/NET/mediatypes/x-RData"));
                uris.put("csv", new URI("https://www.iana.org/assignments/media-types/text/csv"));
                uris.put("sedml", new URI("https://identifiers.org/combine.specifications/sed-ml"));
            } else if (major == 1 && minor == 0 && patch == 12) {
                uris.put("zip", new URI("http://purl.org/NET/mediatypes/application/zip"));
                uris.put("tgz", new URI("http://purl.org/NET/mediatypes/application/x-tgz"));
                uris.put("tar_gz", new URI("http://purl.org/NET/mediatypes/application/x-tar.gz"));
                uris.put("r", new URI("http://purl.org/NET/mediatypes/application/r"));
                uris.put("pmf", new URI("http://purl.org/NET/mediatypes/application/x-pmf"));
                uris.put("sbml", new URI("http://purl.org/NET/mediatypes/application/sbml+xml"));
                uris.put("json", new URI("https://wwww.iana.org/assignments/media-types/application/json"));
                uris.put("matlab", new URI("http://purl.org/NET/mediatypes/text/x-matlab"));
                uris.put("php", new URI("http://purl.org/NET/mediatypes/text/x-php"));
                uris.put("plain", new URI("http://purl.org/NET/mediatypes/text-xplain"));
                uris.put("rdata", new URI("http://purl.org/NET/mediatypes/x-RData"));
                uris.put("csv", new URI("https://www.iana.org/assignments/media-types/text/csv"));
                uris.put("sedml", new URI("https://identifiers.org/combine.specifications/sed-ml"));
                uris.put("png", URI.create("http://purl.org/NET/mediatypes/image/png"));
                uris.put("xlsx", URI.create("https://www.iana.org/assignments/media-types/application/vnd.ms-excel"));
            }

        } catch (URISyntaxException exception) {
            // Does not occur since all the URLS entered here are valid.
        }

        return uris;
    }

    public static Version findVersion(CombineArchive archive) {

        List<MetaDataObject> descriptions = archive.getDescriptions();

        // If archive has RDF metadata with a single description called "metaParent" -> 1.0.0
        if (descriptions.size() > 0 && descriptions.get(0).getXmlDescription().getName().equals("metaParent")) {
            return new Version(1, 0, 0);
        }

        URI newRURI = getURIS(1, 0, 4).get("r");
        URI oldRURI = getURIS(1, 0, 2).get("r");

        // If archive uses old URI for R -> 1.0.2
        if (archive.getNumEntriesWithFormat(oldRURI) > 0) {
            return new Version(1, 0, 2);
        }

        // If archive does not use new URI for R -> throw exception
        if (archive.getNumEntriesWithFormat(newRURI) == 0) {
            throw new IllegalArgumentException("Version cannot be determined");
        }

        // If archive contains SED-ML -> 1.0.11
        if (archive.getNumEntriesWithFormat(getURIS(1, 0, 11).get("sedml")) > 0) {
            return new Version(1, 0, 11);
        }

        // If archive contains CSV -> 1.0.10
        if (archive.getNumEntriesWithFormat(getURIS(1, 0, 10).get("csv")) > 0) {
            return new Version(1, 0, 10);
        }

        // If archive contains JSON, plain text or RData -> 1.0.8
        Map<String, URI> uris108 = getURIS(1, 0, 8);
        if (archive.getNumEntriesWithFormat(uris108.get("json")) > 0) {
            return new Version(1, 0, 8);
        }

        return new Version(1, 0, 4);
    }
}
