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


import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tutorial.entity.SpecialStruct;
import org.hibernate.tutorial.structs.ChildStruct;
import org.hibernate.tutorial.structs.MyParentStruct;
import org.hibernate.tutorial.structs.MyStruct;


public class JsonStructTest extends TestCase {
	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.build();
		try {

			sessionFactory = new MetadataSources( registry )
					.addAnnotatedClass(SpecialStruct.class)
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

	public void testSpecialType() {
		// create a couple of events...
		sessionFactory.inTransaction(session -> {

			SpecialStruct specialStruct = new SpecialStruct();

			MyStruct myStruct = new MyStruct("My struct attribute a", "My struct attribute b");
			specialStruct.setMyStruct(myStruct);

			ChildStruct childStruct = new ChildStruct(15, "Hi I'm a child");
			MyParentStruct myParentStruct = new MyParentStruct(100, childStruct);
			specialStruct.setMyParentStruct(myParentStruct);

			session.persist(specialStruct);

		});


		sessionFactory.inTransaction(session -> {
			session.createQuery("SELECT e FROM SpecialStruct e WHERE e.myParentStruct.child.str = 'Hi I''m a child'", SpecialStruct.class)
					.getResultList()
					.forEach(specialStruct -> {
						assertEquals(Integer.valueOf(100), specialStruct.getMyParentStruct().id());
						assertEquals(Integer.valueOf(15), specialStruct.getMyParentStruct().child().num());
					}
					);


			session.createQuery("SELECT e FROM SpecialStruct e WHERE e.myStruct.attr1 = 'My struct attribute b'", SpecialStruct.class)
					.getResultList()
					.forEach(specialStruct -> {
								assertEquals("My struct attribute a", specialStruct.getMyStruct().attr2());
							}
					);

		});

	}

}
