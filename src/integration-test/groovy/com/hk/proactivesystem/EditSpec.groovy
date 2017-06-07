package com.hk.proactivesystem

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Shared
import spock.lang.Specification

@Integration
@Rollback
class EditSpec extends Specification {
    @Shared
    int logNumBeforeEdit = 0;
    int eNumBeforeEdit = 0;

    def setup() {
        logNumBeforeEdit = EmployeeLog.count();
        eNumBeforeEdit = Employee.count();
        def employee = Employee.findByEmployeeID("toBeEdit")
        if (employee){
            employee.dateOfBirth = "New Date Of Birth"
            employee.save(flush: true)
        }else{
            println "create employee"
            new Employee(
                    employeeID:'toBeEdit',
                    firstName:'Marco',
                    lastName: 'Li',
                    dateOfBirth: '09/03/1993'
            ).save(flush: true)
            eNumBeforeEdit++
            logNumBeforeEdit++
            sleep(20000);
            employee = Employee.findByEmployeeID("toBeEdit")
            employee.dateOfBirth = "New Date Of Birth"
            employee.save(flush: true)
        }
    }

    def cleanup() {
        def employee = Employee.findByEmployeeID("toBeEdit")
        employee.delete(flush:true)
    }

    void "test log by edit record"() {
        given:
        println "running editing record"
        sleep(20000);
        expect:
        assert eNumBeforeEdit == Employee.count()
        assert EmployeeLog.count() == logNumBeforeEdit+2;
    }
}
