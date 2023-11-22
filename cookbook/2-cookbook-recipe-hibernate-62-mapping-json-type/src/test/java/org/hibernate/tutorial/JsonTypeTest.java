/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.tutorial;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tutorial.entity.Special;


public class JsonTypeTest extends TestCase {
	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.build();
		try {

			sessionFactory = new MetadataSources( registry )
					.addAnnotatedClass(Special.class)
					.buildMetadata()
					.buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			throw e;
//			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}


	public void testJsonType() {
		sessionFactory.inTransaction(session -> {

			String[] names = {"John", "Lisa"};

			Map<String, String> stringMap = new HashMap<>();
			stringMap.put("John", "He");
			stringMap.put("Lisa", "She");

			Special special = new Special();
			special.setNames(names);
			special.setJsonProperty(new Detail("John", 180));
			special.setStringMap(stringMap);

			session.persist(special);

		});


		sessionFactory.inTransaction(session -> {

			session.createNativeQuery("SELECT * FROM special e WHERE e.json_property->>'name' = 'John'", Special.class)
					.getResultList()
					.forEach(special ->
							{
								assertEquals(Integer.valueOf(180), special.getJsonProperty().getHeight());
							}
					);

			session.createNativeQuery("SELECT * FROM special e WHERE e.string_map->>'John' = 'He'", Special.class)
					.getResultList()
					.forEach(special -> {
								assertEquals(Integer.valueOf(180), special.getJsonProperty().getHeight());
							}
					);
		});

	}

}
