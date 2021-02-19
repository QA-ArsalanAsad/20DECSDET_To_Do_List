
package com.qa.TDL;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.TDL.persistence.domain.Gym;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
class TdlApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.forClass(Gym.class);
//				.withPrefabValues(Equipment.class, new Equipment("Dumbbells", 30), new Equipment("Bench", 90)).verify();

	}

}
