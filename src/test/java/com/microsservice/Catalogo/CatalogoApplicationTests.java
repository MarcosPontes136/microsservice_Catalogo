package com.microsservice.Catalogo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootTest
class CatalogoApplicationTests {
	
    @Mock
    private ConfigurableApplicationContext mockCtx;

    @Mock
    private Environment mockEnv;

    @Mock
    private Logger mockLogger;

	@Test
	void contextLoads() {
		assertThat(true).isTrue();
	}
	
	@Test
	private void testeMain() {
		assertThatCode(() -> {
			CatalogoApplication.main(new String[]{});
		}).doesNotThrowAnyException();
	}

}
