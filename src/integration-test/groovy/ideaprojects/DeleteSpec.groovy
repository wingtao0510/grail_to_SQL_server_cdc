package ideaprojects


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class DeleteSpec extends Specification {
    @Shared
    int logNumBeforeDelete = 0;
    int eNumBeforeDelete = 0;

    def setup() {
        logNumBeforeDelete = EmployeeLog.count();
        eNumBeforeDelete = Employee.count();
        def employee = Employee.findByEmployeeID("toBeDeleted")
        if (employee){
            employee.delete(flush:true)
        }else{
            println "create employee"
            new Employee(
                    employeeID:'toBeDeleted',
                    firstName:'Tommy',
                    lastName: 'Lam',
                    dateOfBirth: '06/03/1993'
            ).save(flush: true)
            eNumBeforeDelete++
            logNumBeforeDelete++
            sleep(20000);
            employee = Employee.findByEmployeeID("toBeDeleted")
            employee.delete(flush:true)
        }
    }

    def cleanup() {

    }

    void "test log by delete record"(){
        given:
        println "running delete record"
        sleep(20000);
        expect:
//        assert employeeCount == Employee.count()
        assert eNumBeforeDelete-1 == Employee.count()
        assert EmployeeLog.count() == logNumBeforeDelete+1;
    }
}
