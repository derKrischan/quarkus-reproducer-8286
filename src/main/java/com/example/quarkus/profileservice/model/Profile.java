package com.example.quarkus.profileservice.model;

import java.util.Optional;

import org.immutables.value.Value.Immutable;

import com.example.quarkus.annotation.PojoStyle;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@Immutable
@PojoStyle
@JsonSerialize(as = ImmutableProfile.class)
@JsonDeserialize(as = ImmutableProfile.class)
public interface Profile {

    Optional<String> getAcademicTitle();
    Optional<String> getName();
    Optional<String> getGivenName();
    String getLoginName();

    /**
     * Returns a builder instance for {@link Profile}.
     * 
     * @return a new builder instance for {@link Profile}
     */
    static Builder builder() {
            return new Builder(); 
    }
    
    /**
     * The builder instance.
     */
    class Builder extends ImmutableProfile.Builder{}

}
