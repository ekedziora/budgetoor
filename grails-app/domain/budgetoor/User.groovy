package budgetoor

class User {

    Long id

    Long version

    String login

    String password

    String firstName

    String lastName

    Boolean admin

    Boolean active = true

    static mapping = {
        table 'users'
        sort 'login'
    }

    static hasMany = [
            payments : Payment
    ]

    static constraints = {
        login(unique: true, nullable: false, blank: false)
        password(blank: false, password: true)
        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        admin(nullable: false)
        active(nullable: false)
    }

    def getFirstAndLastName() {
        firstName + ' ' + lastName
    }

    @Override
    public String toString() {
        return getFirstAndLastName();
    }
}
