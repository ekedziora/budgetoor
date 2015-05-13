package budgetoor

import grails.transaction.Transactional

import java.math.RoundingMode

@Transactional
class PaymentService {

    def serviceMethod() {

    }

    BigDecimal getUserBalance(User user) {
        if(!user) {
            return BigDecimal.ZERO
        }

        List<User> activeUsers = User.findAllByActive(true)
        List<Payment> allPayments = Payment.findAllByUserInList(activeUsers)
        List<Payment> userPayments = Payment.findAllByUser(user)

        BigDecimal paymentsSum = allPayments ? allPayments.amount.sum() : BigDecimal.ZERO
        BigDecimal userSum = userPayments ? userPayments.amount.sum() : BigDecimal.ZERO

        BigDecimal numberOfUsers = new BigDecimal(activeUsers.size())
        BigDecimal average = numberOfUsers.compareTo(BigDecimal.ZERO) ? paymentsSum.divide(numberOfUsers, 2, RoundingMode.HALF_UP) : BigDecimal.ZERO

        return userSum.subtract(average)
    }

}
