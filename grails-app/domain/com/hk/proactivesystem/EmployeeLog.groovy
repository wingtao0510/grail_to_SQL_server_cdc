package com.hk.proactivesystem

class EmployeeLog implements Serializable {

    byte[] start_lsn
    byte[] end_lsn
    byte[] seqval
    Integer operation
    byte[] update_mask
    BigDecimal originalId
    BigDecimal version
    String date_of_birth
    String employeeid
    String first_name
    String last_name

    static mapping = {
        table name: "dbo_employee_CT", schema:'cdc'
        start_lsn column: '__$start_lsn'
        end_lsn column: '__$end_lsn'
        seqval column: '__$seqval'
        operation column: '__$operation'
        update_mask column: '__$update_mask'
        version column: 'version'
        date_of_birth column: 'date_of_birth'
        employeeid column: 'employeeid'
        first_name column: 'first_name'
        last_name column: 'last_name'
        id composite: ['start_lsn', 'seqval', 'operation'], generator: 'assigned'
        originalId column: 'id'
    }

    static constraints = {
    }
}
