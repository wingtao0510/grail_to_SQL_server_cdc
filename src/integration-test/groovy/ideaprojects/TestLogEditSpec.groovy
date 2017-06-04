package ideaprojects


import grails.test.mixin.integration.Integration
import grails.transaction.Transactional
import spock.lang.*

@Integration
@Stepwise
class TestLogEditSpec extends Specification {

    @Shared
    int numBeforeEdit = 0;

    Employee employee;

//    def insertData() {
//
//        new Employee(
//                employeeID:'newEmployee',
//                firstName:'Tai Man',
//                lastName: 'Chan',
//                dateOfBirth: '23/11/2017'
//        ).save(flush: true)
//    }

    def editData() {
        employee = Employee.findByEmployeeID("newEmployee")
        employee.dateOfBirth = '1/1/2000'
        employee.save(flush: true)
    }

    def cleanup() {

    }

    @Transactional
    void "test log by edit record"(){
        given:
        println "running edit record"
        int employeeCount = Employee.count()
        numBeforeEdit = EmployeeLog.count();
        editData();
        expect:
        assert employeeCount + 1 == Employee.count()
//        the employee num should be the same
    }

    @Transactional
    void "test later"() {
        given:
        println "running test later"
        sleep(20000);

        expect:
        EmployeeLog.count() == previousNum;
//        EmployeeLog.count() == previousNum + 2;

    }



}
