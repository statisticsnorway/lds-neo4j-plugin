package no.ssb.lds.neo4j.util;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import java.io.InputStream;
import java.util.Collection;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Util {

    public static String readResourceFile(String name) {
        InputStream is = Util.class.getClassLoader().getResourceAsStream(name);
        return new Scanner(is).useDelimiter("\\Z").next();
    }

    public static boolean toBoolean(Object value) {
        if ((value == null || value instanceof Number && (((Number) value).longValue()) == 0L || value instanceof String && (value.equals("") || ((String) value).equalsIgnoreCase("false") || ((String) value).equalsIgnoreCase("no") || ((String) value).equalsIgnoreCase("0")) || value instanceof Boolean && value.equals(false))) {
            return false;
        }
        return true;
    }

    public static Long toLong(Object value) {
        if (value == null) return null;
        if (value instanceof Number) return ((Number) value).longValue();
        try {
            String s = value.toString();
            if (s.contains(".")) return (long) Double.parseDouble(s);
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer toInteger(Object value) {
        if (value == null) return null;
        if (value instanceof Number) return ((Number) value).intValue();
        try {
            String s = value.toString();
            if (s.contains(".")) return (int) Double.parseDouble(s);
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Stream<Object> stream(Object values) {
        if (values == null) return Stream.empty();
        if (values instanceof Collection) return ((Collection) values).stream();
        if (values instanceof Object[]) return Stream.of(((Object[]) values));
        if (values instanceof Iterable) {
            Spliterator<Object> spliterator = ((Iterable) values).spliterator();
            return StreamSupport.stream(spliterator, false);
        }
        return Stream.of(values);
    }

    public static Stream<Node> nodeStream(Transaction tx, Object ids) {
        return stream(ids).map(id -> node(tx, id));
    }

    public static Node node(Transaction tx, Object id) {
        if (id instanceof Node) return rebind(tx, (Node) id);
        if (id instanceof Number) return tx.getNodeById(((Number) id).longValue());
        throw new RuntimeException("Can't convert " + id.getClass() + " to a Node");
    }

    public static Node rebind(Transaction tx, Node node) {
        return tx.getNodeById(node.getId());
    }
}
