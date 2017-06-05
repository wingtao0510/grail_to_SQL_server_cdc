package ideaprojects

class EmployeeController {
    def scaffold = Employee

    def abc() {
        def employee = Employee.findByEmployeeID("231")

        if (employee) {
            render employee.firstName
        } else{
            render "nothing found"
        }
    }
}
