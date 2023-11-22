package org.hibernate.tutorial.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Map;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.tutorial.Detail;
import org.hibernate.type.SqlTypes;

@Entity
@Table( name = "special" )
public class Special  {

  private Long id;

  private String[] names;

  private Detail jsonProperty;

  private Map<String, String> stringMap;

  public Special() {
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

  @Column(name = "names", columnDefinition = "text[]")
  @JdbcTypeCode( SqlTypes.ARRAY )
  public String[] getNames() {
    return names;
  }

  public void setNames(String[] names) {
    this.names = names;
  }

  @JdbcTypeCode( SqlTypes.JSON )
  @Column(columnDefinition = "jsonb", name = "json_property")
  public Detail getJsonProperty() {
    return jsonProperty;
  }

  public void setJsonProperty(Detail jsonProperty) {
    this.jsonProperty = jsonProperty;
  }


  @JdbcTypeCode( SqlTypes.JSON )
  @Column(columnDefinition = "jsonb", name = "string_map")
  public Map<String, String> getStringMap() {
    return stringMap;
  }

  public void setStringMap(Map<String, String> stringMap) {
    this.stringMap = stringMap;
  }

}
