import budgetoor.User

class BootStrap {

    def init = { servletContext ->
        new User(login: "ekedziora", password: "5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25",
            firstName: 'Emil', lastName: 'K�dziora', admin: true).save()
    }
    def destroy = {
    }
}
