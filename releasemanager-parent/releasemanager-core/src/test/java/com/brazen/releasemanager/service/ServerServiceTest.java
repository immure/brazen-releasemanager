package com.brazen.releasemanager.service;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.brazen.releasemanager.ReleaseManagerAppCore;
import com.brazen.releasemanager.domain.Environment;
import com.brazen.releasemanager.domain.EnvironmentType;
import com.brazen.releasemanager.domain.Server;

public class ServerServiceTest {
	
	private static ReleaseManagerAppCore appCore;
	
	private static ServerService serverService;
	private static EnvironmentService environmentService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		appCore = new ReleaseManagerAppCore();
		appCore.start();
	
		serverService = (ServerService) appCore.getBean("serverService");
		environmentService = (EnvironmentService) appCore.getBean("environmentService");
		
	}
	
	/*
	 * 
	 * jodd.db.oom.sqlgen.DbSqlBuilderException: Unable to resolve column reference: environment.environment_type Generated query: select server.ID, server.ADDRESS, server.ENVIRONMENT, server.NAME, server.STATUS, environment.ID, environment.ENVIRONMENT_NAME, environment.ENVIRONMENT_TYPE, environmentType.ID, environmentType.ENVIRONMENT_TYPE_NAME from BZ_SERVER server join BZ_ENVIRONMENT environment on server.ENVIRONMENT=environment.id, join BZ_ENVIRONMENT_TYPE environmentType on environment
	at jodd.db.oom.sqlgen.chunks.ReferenceChunk.process(ReferenceChunk.java:61)
	at jodd.db.oom.sqlgen.DbSqlBuilder.generateQuery(DbSqlBuilder.java:238)
	at jodd.db.oom.DbOomQuery.<init>(DbOomQuery.java:86)
	at jodd.db.oom.DbOomQuery.query(DbOomQuery.java:90)
	at com.brazen.releasemanager.service.ServerService.findServerById(ServerService.java:39)
	at com.brazen.releasemanager.service.ServerService$Proxetta.findServerById$0(Unknown Source)
	at com.brazen.releasemanager.service.ServerService$Proxetta.findServerById(Unknown Source)
	at com.brazen.releasemanager.service.ServerServiceTest.testStoreServer(ServerServiceTest.java:87)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:616)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:45)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:42)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:263)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:68)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:47)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:231)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:60)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:229)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:50)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:222)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:300)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)




	 * 
	 */

	@Test
	public void testStoreServer() {
		Server s = new Server();
		
		s.setAddress("127.0.0.1");
		s.setName("localhost");
		
		EnvironmentType et = environmentService.findEnvironmentTypeByName("STAGING");
		Environment e = new Environment();
		e.setEnvironmentName("test");
		e.setEnvironmentType(et);
		e = environmentService.store(e);
		
		s.setEnvironment(e);
		
		Long id = serverService.storeServer(s).getId();
		
		s = serverService.findServerById(id);
		
		assertEquals("STAGING", s.getEnvironment().getEnvironmentType().getEnvironmentTypeName());
		assertEquals("test", s.getEnvironment().getEnvironmentName());
		assertEquals("127.0.0.1", s.getAddress());
		
		
	}

}
