package no.ssb.lds.neo4j.path;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.Evaluator;

import static no.ssb.lds.neo4j.path.TBVConstants.LABEL_EMBEDDED;
import static no.ssb.lds.neo4j.path.TBVConstants.LABEL_INSTANCE;
import static no.ssb.lds.neo4j.path.TBVConstants.LABEL_RESOURCE;


public class TBVLabelEvaluator implements Evaluator {
    @Override
    public Evaluation evaluate(Path path) {
        Node endNode = path.endNode();
        if (endNode.hasLabel(LABEL_INSTANCE)) {
            return Evaluation.INCLUDE_AND_CONTINUE;
        }
        if (endNode.hasLabel(LABEL_EMBEDDED)) {
            return Evaluation.INCLUDE_AND_CONTINUE;
        }
        if (endNode.hasLabel(LABEL_RESOURCE)) {
            return Evaluation.EXCLUDE_AND_CONTINUE;
        }
        return Evaluation.EXCLUDE_AND_PRUNE;
    }
}
