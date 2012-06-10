package com.brazen.releasemanager.install;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.brazen.releasemanager.ReleaseManagerAppCore;
import com.brazen.releasemanager.exception.BrazenException;

import jodd.db.DbQuery;
import jodd.db.DbSession;
import jodd.db.DbSqlException;
import jodd.io.StreamUtil;
import jodd.io.StringOutputStream;
import jodd.io.UnicodeInputStream;
import jodd.log.Log;
import jodd.petite.meta.PetiteBean;
import jodd.util.ClassLoaderUtil;
import jodd.util.StringUtil;

@PetiteBean
public class DbInstaller {

	private static final Log log = Log.getLogger(DbInstaller.class);
	
	public boolean checkDb() {
		log.debug("Check database.");
		DbSession dbSession = new DbSession(ReleaseManagerAppCore.ref.getConnectionProvider());
		DbQuery query = new DbQuery(dbSession, "select count(1) from bz_environment_type");
		try {
			query.execute();
			log.debug("Database OK.");
			return true;
		} catch (Exception ex) {
			return false;
		} finally {
			query.close();
			dbSession.closeSession();
		}
	}
	
	/**
	 * Installs database from the script.
	 */
	public void installDb() {
		log.info("Creating database...");

		executeScriptQueries("/res/db-hsqldb.sql");

		log.info("Database created.");
	}
	
	public void resetDb() {
		log.info("Resetting database...");
		
		try {
		executeScriptQueries("/res/db-hsqldb-reset.sql");
		} catch (DbSqlException e) {
			log.error("Was not able to reset database - may cause unexpected behaviour");
		}
		
		log.info("Database reset");
	}

	/**
	 * Executes all queries from provided sql file.
	 */
	private void executeScriptQueries(String fileName) {
		log.debug("Executing " + fileName);
		String sql;
		try {
			InputStream in = ClassLoaderUtil.getResourceAsStream(fileName);
			if (in == null) {
				throw new BrazenException(fileName + " not found.");
			}
			UnicodeInputStream uin = new UnicodeInputStream(in);
			StringOutputStream sos = new StringOutputStream(uin.getEncoding());
			StreamUtil.copy(uin, sos);
			sql = sos.toString();
		} catch (IOException ioex) {
			throw new BrazenException(ioex);
		}

		String[] queries = StringUtil.splitc(sql, ";");

		DbSession dbSession = new DbSession(ReleaseManagerAppCore.ref.getConnectionProvider());
		for (int i = 0; i < queries.length; i++) {
			String q = queries[i];
			q = cleanSql(q);
			if (q.isEmpty()) {
				continue;
			}
			DbQuery query = new DbQuery(dbSession, q);
			query.executeUpdate();
			log.debug("executed query #" + (i + 1));
		}
		dbSession.closeSession();
	}



	/**
	 * Cleans single sql query.
	 */
	private String cleanSql(String sql) {
		String[] lines = StringUtil.splitc(sql, "\n");
		StringBuilder sb = new StringBuilder(sql.length());
		
		for (String line : lines) {
			line = line.trim();
			if (line.isEmpty()) {
				continue;
			}
			if (line.startsWith("--")) {
				continue;
			}
			sb.append(line);
			sb.append(' ');
		}

		return sb.toString();
	}

}
