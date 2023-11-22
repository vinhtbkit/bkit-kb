package org.hibernate.tutorial;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

public class MyJsonType implements UserType<Detail> {

  public static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public int getSqlType() {
    return SqlTypes.JSON;
  }

  @Override
  public Class<Detail> returnedClass() {
    return Detail.class;
  }

  @Override
  public boolean equals(Detail x, Detail y) {
    return false;
  }

  @Override
  public int hashCode(Detail x) {
    return 0;
  }

  @Override
  public Detail nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
      throws SQLException {
    final String cellContent = rs.getString(position);
    if (cellContent == null) {
      return null;
    }
    try {
      return MAPPER.readValue(cellContent.getBytes("UTF-8"), returnedClass());
    } catch (final Exception ex) {
      throw new RuntimeException("Failed to convert String to MyJson: " + ex.getMessage(), ex);
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Detail value, int index, SharedSessionContractImplementor session)
      throws SQLException {
    if (value == null) {
      st.setNull(index, Types.OTHER);
      return;
    }
    try {
      final StringWriter w = new StringWriter();
      MAPPER.writeValue(w, value);
      w.flush();
      st.setObject(index, w.toString(), Types.OTHER);
    } catch (final Exception ex) {
      throw new RuntimeException("Failed to convert MyJson to String: " + ex.getMessage(), ex);
    }
  }

  @Override
  public Detail deepCopy(Detail value) {
    try {
      // use serialization to create a deep copy
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(value);
      oos.flush();
      oos.close();
      bos.close();

      ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
      Detail obj = (Detail) new ObjectInputStream(bais).readObject();
      bais.close();
      return obj;
    } catch (ClassNotFoundException | IOException ex) {
      throw new HibernateException(ex);
    }
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Detail value) {
    return null;
  }

  @Override
  public Detail assemble(Serializable cached, Object owner) {
    return null;
  }

}
