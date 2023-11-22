package org.hibernate.tutorial.structs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Struct;

@Embeddable
@Struct(name = "my_struct")
public record MyStruct (
    @Column(name = "a")
    String attr2,
    @Column(name = "b")
    String attr1
) {

}
