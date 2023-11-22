package org.hibernate.tutorial.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.tutorial.structs.MyParentStruct;
import org.hibernate.tutorial.structs.MyStruct;
import org.hibernate.type.SqlTypes;

@Entity
@Table( name = "special_struct" )
public class SpecialStruct {

  private Long id;

  private MyStruct myStruct;

  private MyParentStruct myParentStruct;


  public SpecialStruct() {
    // this form used by Hibernate
  }


  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  public Long getId() {
    return id;
  }

  private void setId(Long id) {
    this.id = id;
  }

  @JdbcTypeCode( SqlTypes.JSON )
  @Column(name = "my_struct")
  // https://in.relation.to/2023/02/13/hibernate-orm-62-composite-aggregates/
  public MyStruct getMyStruct() {
    return myStruct;
  }

  public void setMyStruct(MyStruct myStruct) {
    this.myStruct = myStruct;
  }


  @JdbcTypeCode( SqlTypes.JSON )
  @Column(name = "parent_struct")
  public MyParentStruct getMyParentStruct() {
    return myParentStruct;
  }

  public void setMyParentStruct(MyParentStruct myParentStruct) {
    this.myParentStruct = myParentStruct;
  }

}
