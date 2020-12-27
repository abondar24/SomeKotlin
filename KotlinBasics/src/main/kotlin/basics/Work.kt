package basics

class Work(emp: Employee) : AbstractWork(emp) {
    override fun doWork() {
        emp.doWork()
    }




}
