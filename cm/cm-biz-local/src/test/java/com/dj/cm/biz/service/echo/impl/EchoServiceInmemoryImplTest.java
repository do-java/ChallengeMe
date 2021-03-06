package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.model.entity.Echo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * EchoServiceInmemoryImpl Test.
 */
public class EchoServiceInmemoryImplTest {

	private EchoServiceInmemoryImpl service;

	@Before
	public void setUp() throws Exception {
		service = new EchoServiceInmemoryImpl();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void getAllEchos() {
		// Given
		Echo echo1 = new Echo(null, "E1");
		Echo echo2 = new Echo(null, "E2");

		// When
		Echo echo1Created = service.createEcho(echo1);
		Echo echo2Created = service.createEcho(echo2);
		List<Echo> result = service.getAllEchos();

		// Then
		assertEquals(2, result.size());
		assertTrue(result.contains(echo1Created));
		assertTrue(result.contains(echo2Created));
	}

	@Test
	public void getEchoById() {
		// Given
		Echo echo1 = new Echo(null, "E1");
		Echo echo2 = new Echo(null, "E2");

		// When
		Echo echo1Created = service.createEcho(echo1);
		Echo echo2Created = service.createEcho(echo2);
		Echo result1 = service.getEchoById(echo1Created.getId());
		Echo result2 = service.getEchoById(echo2Created.getId());

		// Then
		assertEquals(echo1Created, result1);
		assertEquals(echo2Created, result2);
	}

	@Test(expected = NotFoundBizException.class)
	public void getEchoByIdThrowsNotFoundBizException() {
		// Given
		Long id = 9999L;

		// When
		service.getEchoById(id);

		// Then
		// Exception expected

	}

	@Test
	public void createEcho() {
		// Given
		Echo echo = new Echo(null, "E1");

		// When
		Echo result = service.createEcho(echo);

		// Then
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals("E1", result.getValue());
	}

	@Test
	public void updateEcho() {
		// Given
		Echo echo = new Echo(null, "E1");

		// When
		Echo resultEcho = service.createEcho(echo);
		Long resultEchoId = resultEcho.getId();
		final Echo echoToUpdate = new Echo(resultEchoId, "E2");
		Echo result1 = service.updateEcho(echoToUpdate);
		Echo result2 = service.getEchoById(resultEchoId);

		// Then
		assertEquals(resultEchoId, result1.getId());
		assertEquals("E2", result1.getValue());
		assertEquals(resultEchoId, result2.getId());
		assertEquals("E2", result2.getValue());
	}

	@Test
	public void deleteEcho() {
		// Given
		Echo echo = new Echo(null, "E1");

		// When
		Echo resultEcho = service.createEcho(echo);
		Long resultEchoId = resultEcho.getId();
		service.deleteEcho(resultEchoId);
		List<Echo> resultList = service.getAllEchos();

		// Then
		assertTrue(!resultList.contains(resultEcho));
	}

	@Test
	public void doEcho() {
	}

	@Test
	public void doEcho1() {
	}
}