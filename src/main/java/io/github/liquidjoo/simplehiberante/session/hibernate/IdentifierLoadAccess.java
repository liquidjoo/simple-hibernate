package io.github.liquidjoo.simplehiberante.session.hibernate;

import java.io.Serializable;
import java.util.Optional;

public interface IdentifierLoadAccess<T> {

    T load(Serializable id);

    Optional<T> loadOptional(Serializable id);
}
