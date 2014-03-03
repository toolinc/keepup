// Copyright 2014 University of Detroit Mercy.

package com.udm.health;

import com.udm.health.inject.PersistentModuleTest;

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
                .addPackage("com.udm.common")
                .addPackage("com.udm.common.adapter")
                .addPackage("com.udm.common.adapter.persistence")
                .addPackage("com.udm.common.domain")
                .addPackage("com.udm.common.domain.model")
                .addPackage("com.udm.common.domain.repository")
                .addPackage("com.udm.common.serializer")
                .addPackage("com.udm.health")
                .addPackage("com.udm.health.domain")
                .addPackage("com.udm.health.domain.model")
                .addPackage("com.udm.health.validation")
                .addClass(PersistentModuleTest.class)
                .addAsLibraries(
                        DependencyResolvers.use(MavenDependencyResolver.class)
                                .goOffline()
                                .artifact("org.jboss.slf4j:slf4j-jboss-logmanager:1.0.3.GA")
                                .artifact("com.google.guava:guava:13.0.1")
                                .artifact("com.google.code.gson:gson:2.2.4")
                                .artifact("com.udm.health:keepUp-common:1.0")
                                .resolveAsFiles())
                .addAsWebInfResource("log4j.xml")
                .addAsWebInfResource("ValidationMessages.properties")
                .addAsWebInfResource("jboss-ds.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return webArchive;
    }
}
