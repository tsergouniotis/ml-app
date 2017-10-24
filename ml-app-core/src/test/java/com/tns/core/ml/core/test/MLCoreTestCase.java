package com.tns.core.ml.core.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MLCoreTestCase {

	// @Inject
	// private RServer rServer;

	@Deployment
	public static JavaArchive createDeployment() {

		// System.getenv().put("R_SERVER_HOST", "localhost");
		// System.getenv().put("R_SERVER_PORT", "6311");
		// System.getenv().put("R_SERVER_HOST", "localhost");

		JavaArchive genericArchive = ShrinkWrap.create(JavaArchive.class, "rServer.jar");
		// genericArchive.addClass(Property.class);
		// genericArchive.addClass(RServer.class);
		// genericArchive.addClass(RServerEnvironmentProducer.class);
		// genericArchive.addPackages(true, "com.tns.core");
		genericArchive.addPackages(true, "org.rosuda");
		genericArchive.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(genericArchive.toString(true));

		return genericArchive;
	}

	@Test
	public void testRServer() {

	}

}
