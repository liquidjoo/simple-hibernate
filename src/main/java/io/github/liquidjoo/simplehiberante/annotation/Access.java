package io.github.liquidjoo.simplehiberante.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { TYPE, METHOD, FIELD })
@Retention(RUNTIME)
public @interface Access {
    AccessType value();
}
