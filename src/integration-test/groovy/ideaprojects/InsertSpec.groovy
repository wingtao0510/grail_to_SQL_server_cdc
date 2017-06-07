package ideaprojects


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class InsertSpec extends Specification {
    @Shared
    int employeeLogCount = 0;
    int employeeCount = 0;

    def setup() {
        println "running insert record"
        employeeCount = Employee.count()
        employeeLogCount = EmployeeLog.count();
        new Employee(
                employeeID:'newEmployee',
                firstName:'Tai Man',
                lastName: 'Chan',
                dateOfBirth: '23/11/2017'
        ).save(flush: true)
        employeeLogCount++;
    }

    def cleanup() {
        def employee = Employee.findByEmployeeID("newEmployee")
        if (employee) {
            employee.delete(flush: true)
        }
    }

    void "test log by insert record"() {
        given:
        println "running inserting record"
        sleep(20000);
        expect:
        assert employeeCount + 1 == Employee.count()
        EmployeeLog.count() == employeeLogCount+1;
    }
}
