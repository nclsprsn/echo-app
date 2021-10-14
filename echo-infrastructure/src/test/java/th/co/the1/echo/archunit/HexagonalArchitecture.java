package th.co.the1.echo.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HexagonalArchitecture extends ArchitectureElement {

    private final List<String> domainPackages = new ArrayList<>();
    private Adapters adapters;
    private ApplicationLayer applicationLayer;
    private String infrastructurePackage;

    public HexagonalArchitecture(String basePackage) {
        super(basePackage);
    }

    public static HexagonalArchitecture boundedContext(String basePackage) {
        return new HexagonalArchitecture(basePackage);
    }

    public Adapters withAdaptersLayer(String adaptersPackage) {
        this.adapters = new Adapters(this, fullQualifiedPackage(adaptersPackage));
        return this.adapters;
    }

    public HexagonalArchitecture withDomainLayer(String domainPackage) {
        this.domainPackages.add(fullQualifiedPackage(domainPackage));
        return this;
    }

    public ApplicationLayer withApplicationLayer(String applicationPackage) {
        this.applicationLayer = new ApplicationLayer(fullQualifiedPackage(applicationPackage), this);
        return this.applicationLayer;
    }

    public HexagonalArchitecture withInfrastructure(String packageName) {
        this.infrastructurePackage = fullQualifiedPackage(packageName);
        return this;
    }

    private void domainDoesNotDependOnOtherPackages(JavaClasses classes) {
        denyAnyDependency(
                this.domainPackages, Collections.singletonList(adapters.basePackage), classes);
        denyAnyDependency(
                this.domainPackages, Collections.singletonList(applicationLayer.basePackage), classes);
    }

    public void check(JavaClasses classes) {
        this.adapters.doesNotContainEmptyPackages();
        this.adapters.dontDependOnEachOther(classes);
        this.adapters.doesNotDependOn(this.infrastructurePackage, classes);
        this.applicationLayer.doesNotContainEmptyPackages();
        this.applicationLayer.doesNotDependOn(this.adapters.getBasePackage(), classes);
        this.applicationLayer.doesNotDependOn(this.infrastructurePackage, classes);
        this.applicationLayer.incomingAndOutgoingPortsDoNotDependOnEachOther(classes);
        this.domainDoesNotDependOnOtherPackages(classes);
    }
}
