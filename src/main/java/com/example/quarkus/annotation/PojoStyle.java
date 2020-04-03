package com.example.quarkus.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

import org.immutables.value.Value.Style;
import org.immutables.value.Value.Style.BuilderVisibility;
import org.immutables.value.Value.Style.ImplementationVisibility;

import io.quarkus.runtime.annotations.RegisterForReflection;

/** 
 * A {@link Style} that allows using modifiable representations of 
 * the type POJO-like. As Immutables states in doc for beanFriendlyModifiables:
 * "Note, we are not supporting JavaBean specification in any way except that 
 * Immutables can be used/configured to be partially compatible with some of the conventions."
 * 
 * */
@Inherited
@Target(ElementType.TYPE)
@Style(
    create = "new",
    get = {"get*", "is*"},
    set = "set*",
    beanFriendlyModifiables = true,
    isInitialized = "initialized",
    builderVisibility = BuilderVisibility.PACKAGE,
    passAnnotations = RegisterForReflection.class,
    visibility = ImplementationVisibility.PUBLIC)
public @interface PojoStyle {}
