package budgetoor

class Payment {

    public static final String BIG_DECIMAL_REGEXP = /^\d+((\.|,)\d+)?$/

    Long id

    Long version

    String description

    BigDecimal amount

    User user

    static mapping = {
        table 'payments'
        user lazy: false
    }

    static constraints = {
        user(nullable: false)
        description(nullable: false, blank: false)
        amount(min: 0.0, scale: 2)
    }
}
