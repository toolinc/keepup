// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence;

import com.udm.health.persistence.inject.PersistentModuleTest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Specifies the behavior of an integration test.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@RunWith(Arquillian.class)
public abstract class PersistenceTest {

    @Inject
    protected EntityManager em;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.udm.health.persistence")
                .addPackage("com.udm.health.persistence.dao")
                .addPackage("com.udm.health.persistence.dao.impl")
                .addPackage("com.udm.health.persistence.model")
                .addPackage("com.udm.health.persistence.model.domain")
                .addPackage("com.udm.health.persistence.validation")
                .addClass(PersistentModuleTest.class)
                .addAsLibraries(
                        DependencyResolvers.use(MavenDependencyResolver.class)
                                .goOffline()
                                .artifact("org.jboss.slf4j:slf4j-jboss-logmanager:1.0.3.GA")
                                .artifact("com.google.guava:guava:13.0.1")
                                .resolveAsFiles())
                .addAsWebInfResource("log4j.xml")
                .addAsWebInfResource("ValidationMessages.properties")
                .addAsWebInfResource("jboss-ds.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return webArchive;
    }
}
