package no.ssb.lds.neo4j.result;

import org.neo4j.graphdb.Relationship;

public class RelationshipResult {
    public final Relationship rel;

    public RelationshipResult(Relationship rel) {
        this.rel = rel;
    }
}
