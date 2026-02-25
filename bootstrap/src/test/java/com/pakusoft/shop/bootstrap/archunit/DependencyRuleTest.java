package com.pakusoft.shop.bootstrap.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTest {

    private static final String ROOT_PACKAGE = "com.pakusoft.shop";
    private static final String MODEL_PACKAGE = "model";
    private static final String APPLICATION_PACKAGE = "application";
    private static final String PORT_PACKAGE = "application.port";
    private static final String SERVICE_PACKAGE = "application.service";
    private static final String ADAPTER_PACKAGE = "adapter";
    private static final String BOOTSTRAP_PACKAGE = "bootstrap";

    private static final JavaClasses ALL_CLASSES =
            new ClassFileImporter().importPackages(ROOT_PACKAGE + "..");

    @Test
    void modelShouldNotDependOnApplication() {
        checkNoDependencyFromTo(MODEL_PACKAGE, APPLICATION_PACKAGE);
    }

    @Test
    void modelShouldNotDependOnAdapter() {
        checkNoDependencyFromTo(MODEL_PACKAGE, ADAPTER_PACKAGE);
    }

    @Test
    void modelShouldNotDependOnBootstrap() {
        checkNoDependencyFromTo(MODEL_PACKAGE, BOOTSTRAP_PACKAGE);
    }

    @Test
    void applicationShouldNotDependOnAdapter() {
        checkNoDependencyFromTo(APPLICATION_PACKAGE, ADAPTER_PACKAGE);
    }

    @Test
    void applicationShouldNotDependOnBootstrap() {
        checkNoDependencyFromTo(APPLICATION_PACKAGE, BOOTSTRAP_PACKAGE);
    }

    @Test
    void portsShouldNotDependOnServices() {
        checkNoDependencyFromTo(PORT_PACKAGE, SERVICE_PACKAGE);
    }

    @Test
    void adapterShouldNotDependOnServices() {
        checkNoDependencyFromTo(ADAPTER_PACKAGE, SERVICE_PACKAGE);
    }

    @Test
    void adapterShouldNotDependOnBootstrap() {
        checkNoDependencyFromTo(ADAPTER_PACKAGE, BOOTSTRAP_PACKAGE);
    }

    private void checkNoDependencyFromTo(String fromPackage, String toPackage) {
        noClasses()
                .that()
                .resideInAPackage(fullyQualified(fromPackage))
                .should()
                .dependOnClassesThat()
                .resideInAPackage(fullyQualified(toPackage))
                .check(ALL_CLASSES);
    }

    private static String fullyQualified(String packageName) {
        return ROOT_PACKAGE + '.' + packageName + "..";
    }
}