package budgetoor

class User {

    Long id

    Long version

    String login

    String password

    String firstName

    String lastName

    Boolean admin

    static mapping = {
        table 'users'
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
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", version=" + version +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
