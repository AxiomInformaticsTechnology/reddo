package com.aidos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResourceApplication.class)
public class ResourceApplicationTest {

	@Test
	public void applicationTest() {
		assertTrue("ResourceApplication context does not initialize!", true);
	}

}