package no.ssb.lds.neo4j.result;

import org.neo4j.graphdb.Path;

public class PathResult {
    public Path path;

    public PathResult(Path path) {
        this.path = path;
    }
}