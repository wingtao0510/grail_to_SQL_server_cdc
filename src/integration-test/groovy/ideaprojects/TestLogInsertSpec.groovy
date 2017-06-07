package ideaprojects


import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import grails.transaction.Transactional
import spock.lang.*

@Integration
@Stepwise
class TestLogInsertSpec extends Specification {
    @Shared
    int previousNum = 0;

    def insertData() {

        new Employee(
                employeeID:'newEmployee',
                firstName:'Tai Man',
                lastName: 'Chan',
                dateOfBirth: '23/11/2017'
        ).save(flush: true)
    }

//    @Transactional
//    void "test log by insert record"(){
//        given:
//        println "running insert record"
//        int employeeCount = Employee.count()
//        sleep(10000);
//        previousNum = EmployeeLog.count();
//        insertData();
//        expect:
//        assert employeeCount + 1 == Employee.count()
//
//    }
//
//    @Transactional
//    void "test later"() {
//        given:
//        println "running test later"
//        sleep(10000);
//
//        expect:
//        EmployeeLog.count() == previousNum + 1;
//    }

//    def cleanup() {
//        sleep(20000);
//        def employee = Employee.findByEmployeeID("newEmployee")
//        if (employee){
//            employee.delete(flush:true)
//        }
//    }
}
