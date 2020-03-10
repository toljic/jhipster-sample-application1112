package rs.synsoft.ala;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("rs.synsoft.ala");

        noClasses()
            .that()
            .resideInAnyPackage("rs.synsoft.ala.service..")
            .or()
            .resideInAnyPackage("rs.synsoft.ala.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..rs.synsoft.ala.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
