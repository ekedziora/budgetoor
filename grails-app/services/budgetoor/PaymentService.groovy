package budgetoor

import grails.transaction.Transactional

import java.math.RoundingMode

@Transactional
class PaymentService {

    def serviceMethod() {

    }

    String getUserBalance(User user) {
        if(!user) {
            return BigDecimal.ZERO
        }

        List<User> activeUsers = User.findAllByActive(true)
        List<Payment> allPayments = Payment.findAllByUserInList(activeUsers)
        List<Payment> userPayments = Payment.findAllByUser(user)

        BigDecimal paymentsSum = allPayments.amount.sum()
        BigDecimal userSum = userPayments.amount.sum()

        BigDecimal numberOfUsers = new BigDecimal(activeUsers.size())
        BigDecimal average = paymentsSum.divide(numberOfUsers, 2, RoundingMode.HALF_UP)

        def balance = userSum.subtract(average)
        def compare = balance.compareTo(BigDecimal.ZERO)
        def prefix = ''

        if(compare > 0) {
            prefix = "+"
        }
        else if(compare < 0) {
            prefix = "-"
        }

        return prefix + balance
    }

}
