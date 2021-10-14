package th.co.the1.echo;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;
import th.co.the1.echo.archunit.HexagonalArchitecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

    @Test
    void validateRegistrationContextArchitecture() {
        HexagonalArchitecture.boundedContext("th.co.the1.echo")

                .withDomainLayer("domain")

                .withAdaptersLayer("adapter")
                .incoming("web")
                .outgoing("httpclient")
                .outgoing("cache")
                .and()

                .withApplicationLayer("application")
                .services("service")
                .incomingPorts("port.in")
                .outgoingPorts("port.out")
                .and()

                .withInfrastructure("infrastructure")
                .check(new ClassFileImporter()
                        .importPackages("th.co.the1.echo.."));
    }

    @Test
    void testPackageDependencies() {
        noClasses()
                .that()
                .resideInAPackage("th.co.the1.echo.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("th.co.the1.echo.application..")
                .check(new ClassFileImporter()
                        .importPackages("th.co.the1.echo.."));
    }

}
