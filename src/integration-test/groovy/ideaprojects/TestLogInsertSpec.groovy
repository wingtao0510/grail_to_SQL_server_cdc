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

    def editData() {
        def employee = Employee.findByEmployeeID("newEmployee")
//        employee.dateOfBirth = '1/1/2000'
        //employee.save(flush: true)
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
        println "running insert record"
        int employeeCount = Employee.count()

        previousNum = EmployeeLog.count();
        insertData();
        expect:
        assert employeeCount + 1 == Employee.count()

    }

    @Transactional
    void "test later"() {
        given:
        println "running test later"
        sleep(20000);

        expect:
        EmployeeLog.count() == previousNum + 1;
    }
}
