package io.github.liquidjoo.simplehiberante.session.hibernate;

public interface EventSource {
    void persist(String entityName, Object object);

}
