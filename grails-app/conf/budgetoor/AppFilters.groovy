package budgetoor

class AppFilters {

    public static final String ACCESS_DENIED_MESSAGE = 'Nie masz dostępu do wybranego zasobu'

    def filters = {
        login(controller: 'payment|user', action: '*') {
            before = {
                if(!session.user) {
                    redirect(controller: 'login', action: 'login')
                    return false
                }
            }
        }

        admin(controller: 'user', action: '*') {
            before = {
                if(!session.user?.admin) {
                    flash.message = ACCESS_DENIED_MESSAGE
                    redirect(controller: 'payment')
                    return false
                }
            }
        }

        paymentSecurity(controller: 'payment', action: 'edit|update|delete') {
            before = {
                def payment = Payment.get(params.id)
                def user = session.user
                if (!payment?.user?.equals(user)) {
                    flash.message = ACCESS_DENIED_MESSAGE
                    redirect(controller: 'payment')
                    return false
                }
            }
        }
    }
}
