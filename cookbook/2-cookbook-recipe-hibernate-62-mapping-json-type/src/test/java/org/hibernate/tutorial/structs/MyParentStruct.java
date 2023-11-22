package org.hibernate.tutorial.structs;


import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Struct;

@Embeddable
@Struct(name = "parent_struct")
public record MyParentStruct(
    Integer id,
    ChildStruct child

) {

}
