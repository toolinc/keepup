// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager;

import com.udm.identitymanager.infrastructure.cdi.DomainEventPublisherImpl;
import com.udm.identitymanager.infrastructure.cdi.DomainRegistryImpl;
import com.udm.identitymanager.infrastructure.cdi.IdentityManagerModuleTest;

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
                .addPackage("com.udm.common.adapter.jpa")
                .addPackage("com.udm.common.adapter.jpa.event")
                .addPackage("com.udm.common.adapter.jpa.repository")
                .addPackage("com.udm.common.domain")
                .addPackage("com.udm.common.domain.event")
                .addPackage("com.udm.common.domain.model")
                .addPackage("com.udm.common.domain.repository")
                .addPackage("com.udm.common.domain.validation")
                .addPackage("com.udm.common.serializer")
                .addPackage("com.udm.identitymanager")
                .addPackage("com.udm.identitymanager.adapter")
                .addPackage("com.udm.identitymanager.adapter.jpa")
                .addPackage("com.udm.identitymanager.adapter.jpa.repository")
                .addPackage("com.udm.identitymanager.adapter.jpa.repository.access")
                .addPackage("com.udm.identitymanager.adapter.jpa.repository.identity")
                .addPackage("com.udm.identitymanager.domain")
                .addPackage("com.udm.identitymanager.domain.event")
                .addPackage("com.udm.identitymanager.domain.event.access")
                .addPackage("com.udm.identitymanager.domain.event.identity")
                .addPackage("com.udm.identitymanager.domain.model")
                .addPackage("com.udm.identitymanager.domain.model.access")
                .addPackage("com.udm.identitymanager.domain.model.identity")
                .addPackage("com.udm.identitymanager.domain.repository")
                .addPackage("com.udm.identitymanager.domain.repository.access")
                .addPackage("com.udm.identitymanager.domain.repository.identity")
                .addPackage("com.udm.identitymanager.domain.service")
                .addPackage("com.udm.identitymanager.domain.service.access")
                .addPackage("com.udm.identitymanager.domain.service.identity")
                .addPackage("com.udm.identitymanager.domain.validation")
                .addPackage("com.udm.identitymanager.infrastructure")
                .addPackage("com.udm.identitymanager.infrastructure.service")
                .addClass(DomainEventPublisherImpl.class)
                .addClass(DomainRegistryImpl.class)
                .addClass(IdentityManagerModuleTest.class)
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
