package mx.com.eixy.utilities.zos.ftp;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServerTest {

	private static ServerConfig sc;

	@BeforeClass
	public static void createServerConfig() {
		sc = new ServerConfig();
		sc.setIp("150.23.1.37");
		sc.setPort("21");
		sc.setUser("TN6EAM");
		sc.setPassword("chetos10");
		sc.setServerName("INFOPRE");
	}

	@Test
	public void testFetchConnection() {

		FTPClient a = null;

		try {
			a = FtpClientFactory.fetchConnection(sc);
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			assertNotNull(a);
		}
	}

}
