package org.hibernate.tutorial.structs;


import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Struct;

@Embeddable
@Struct(name = "child_struct")
public record ChildStruct(
    Integer num,
    String str
) {

}
