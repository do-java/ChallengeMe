package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.event.amqp.util.AmqpEventUtil;
import com.dj.cm.model.entity.Echo;
import com.dj.cm.persistence.repo.echo.EchoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * EchoServiceImpl Test.
 */
@RunWith(MockitoJUnitRunner.class)
public class EchoServiceImplTest {

	@Mock
	private Logger logger;

	@Mock
	private AmqpEventUtil eventUtil;

	@Mock
	private EchoRepository repository;

	@InjectMocks
	private EchoServiceImpl service;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void getAllEchos() {
		// Given
		Echo echo1 = new Echo(1L, "E1");
		Echo echo2 = new Echo(2L, "E2");


		// When
		when(repository.findAllByOrderByIdAsc()).thenReturn(Arrays.asList(echo1, echo2));
		List<Echo> result = service.getAllEchos();

		// Then
		verify(repository).findAllByOrderByIdAsc();
		assertEquals(2, result.size());
		assertTrue(result.contains(echo1));
		assertTrue(result.contains(echo2));
	}

	@Test
	public void getEchoById() {
		// Given
		Echo echo1 = new Echo(1L, "E1");

		// When
		when(repository.findById(echo1.getId())).thenReturn(Optional.of(echo1));
		Echo result = service.getEchoById(echo1.getId());

		// Then
		verify(repository).findById(echo1.getId());
		assertEquals(echo1, result);
	}

	@Test(expected = NotFoundBizException.class)
	public void getEchoByIdThrowsNotFoundBizException() {
		// Given
		Long id = 9999L;

		// When
		when(repository.findById(id)).thenReturn(Optional.empty());
		service.getEchoById(id);

		// Then
		// Exception expected

	}

	@Test
	public void createEcho() {
		// Given
		Echo echo = new Echo(null, "E1");
		Echo echoCreated = new Echo(1L, "E1");

		// When
		when(repository.save(echo)).thenReturn(echoCreated);
		Echo result = service.createEcho(echo);

		// Then
		verify(repository).save(echo);
		assertEquals(echoCreated, result);
	}

	@Test
	public void updateEcho() {
	}

	@Test
	public void deleteEcho() {
	}

	@Test
	public void doEcho() {
	}

	@Test
	public void doEcho1() {
	}
}