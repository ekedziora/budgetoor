package budgetoor

class Payment {

    Long id

    Long version

    String description

    BigDecimal amount

    User user

    static mapping = {
        table 'payments'
    }

    static constraints = {
        user(nullable: false)
        description(nullable: false, blank: false)
        amount(min: 0.0)
    }
}
