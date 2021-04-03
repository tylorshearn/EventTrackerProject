package com.skilldistillery.dreamtrack.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("DreamTrackPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("Track Mapping")
	void test() {
		assertNotNull(user);
		assertEquals("Founder", user.getUsername());
	}
	
	@Test
	@DisplayName("User-Dream Mapping")
	void test2() {
		assertNotNull(user);
		assertEquals(1, user.getDreams().get(0).getPointsRewarded());
	}
	
	@Test
	@DisplayName("User-Dream-Track Mapping")
	void test3() {
		assertNotNull(user);
		assertEquals("Buy Stock", user.getDreams().get(0).getTracks().get(0).getTitle());
	}
}