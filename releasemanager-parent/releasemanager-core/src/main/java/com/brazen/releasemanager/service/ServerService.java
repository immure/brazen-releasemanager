package com.brazen.releasemanager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brazen.releasemanager.domain.Environment;
import com.brazen.releasemanager.domain.EnvironmentType;
import com.brazen.releasemanager.domain.Server;
import com.brazen.releasemanager.exception.BrazenException;

import jodd.db.oom.DbOomQuery;
import jodd.joy.db.AppDao;
import jodd.jtx.JtxPropagationBehavior;
import jodd.jtx.meta.Transaction;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

import static jodd.db.oom.DbOomQuery.query;
import static jodd.db.oom.sqlgen.DbSqlBuilder.sql;

@PetiteBean
public class ServerService {
	
	private static final Logger log = LoggerFactory.getLogger(ServerService.class);
	
	@PetiteInject
	AppDao appDao;
	
	@Transaction(propagation = JtxPropagationBehavior.PROPAGATION_SUPPORTS, readOnly = false)
	public Server storeServer(Server server) {
		
		return appDao.store(server);
	}
	
	@Transaction(propagation = JtxPropagationBehavior.PROPAGATION_SUPPORTS)
	public Server findServerById(Long id) {
		DbOomQuery q = query(sql("select $C{server.*}, $C{server.environment.*}, $C{server.environment.environmentType.*} " +
				"from $T{Server server} join $T{Environment environment} on $server.environment=environment.id " +
				"join $T{EnvironmentType environmentType} on $environment.environmentType=environmentType.id " +
				"where server.id = :id"));
		q.setLong("id", id);
		List<Server> server = q.withHints(
				"server, server.environment, server.environment.environmentType").list(
						Server.class, Environment.class, EnvironmentType.class);
		if (server.size() == 0) {
			return null;
		} else if (server.size() > 1) {
			throw new BrazenException("Query returned multiple results");
		} else {
			return server.iterator().next();
		}
	}
	
	@Transaction(propagation = JtxPropagationBehavior.PROPAGATION_SUPPORTS)
	public List<Server> findServerByName(String name) {
		log.debug("finding server by name: " + name);
		DbOomQuery dbOom = query(sql("select $C{s.*} from $T{Server s} where name = :name"));
		dbOom.setString("name", name);
		return dbOom.list(Server.class);
	}
	
	public List<Server> list() {
		log.debug("finding all servers");
		DbOomQuery dbOom = query(sql("select $C{s.*} from $T{Server s}"));
		return dbOom.list(Server.class);
	}


}
