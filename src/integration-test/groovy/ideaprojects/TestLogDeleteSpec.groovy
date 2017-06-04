package ideaprojects


import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import grails.transaction.Transactional
import spock.lang.*

@Integration
@Stepwise
class TestLogDeleteSpec extends Specification {

    @Shared
    int numBeforeDelete = 0;



    def insertData() {

        new Employee(
                employeeID:'newEmployee',
                firstName:'Tai Man',
                lastName: 'Chan',
                dateOfBirth: '23/11/2017'
        ).save(flush: true)
    }

    def deleteData() {
        def employee = Employee.findByEmployeeID("newEmployee")
        employee.delete(flush: true)
    }

    def cleanup() {

    }

//    @Transactional
//    void "test insert"() {
//        given:
//        previousNum = Employee.count();
//        insertData();
//        expect:
//        Employee.count() == previousNum+1;
//    }

    @Transactional
    void "test log by insert record"(){
        given:
        println "running delete record"
        int employeeCount = Employee.count()
        numBeforeDelete = EmployeeLog.count();
        deleteData();
        expect:
        assert employeeCount == Employee.count()
//        assert employeeCount-1 == Employee.count()


    }

    @Transactional
    void "test later"() {
        given:
        println "running test later"
        sleep(20000);

        expect:
        EmployeeLog.count() == numBeforeDelete;
//        EmployeeLog.count() == numBeforeDelete-1;

    }
}
