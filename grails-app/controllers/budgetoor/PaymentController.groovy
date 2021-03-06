package budgetoor

import grails.transaction.Transactional
import org.apache.commons.lang.StringUtils

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class PaymentController {

    PaymentService paymentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 25, 100)

        def userIds = params.list('userFilter')*.toLong()
        def descriptionFilter = params.descriptionFilter
        def matchExactly = params.boolean('matchExactly', Boolean.FALSE)
        def amountFrom = convertToBigDecimal(params.amountFrom)
        def amountTo = convertToBigDecimal(params.amountTo)
        def isAnyFilterSet = false

        def payments = Payment.createCriteria().list(params) {
            if (userIds) {
                isAnyFilterSet = true
                user {
                    'in'("id", userIds)
                }
            }

            if(amountFrom) {
                isAnyFilterSet = true
                ge("amount", amountFrom)
            }

            if(amountTo) {
                isAnyFilterSet = true
                le("amount", amountTo)
            }

            if (StringUtils.isNotBlank(descriptionFilter)) {
                isAnyFilterSet = true
                if(matchExactly) {
                    eq("description", descriptionFilter)
                }
                else {
                    ilike("description", "%${descriptionFilter}%")
                }
            }
        }

        def balance = paymentService.getUserBalance(session.user)

        respond payments, model: [paymentInstanceCount: payments.totalCount, userBalance: balance, isAnyFilterSet: isAnyFilterSet]
    }

    BigDecimal convertToBigDecimal(String number) {
        if(!number || !(number ==~ Payment.BIG_DECIMAL_REGEXP)) {
            return null
        }

        return new BigDecimal(number.replace(',', '.'))
    }

    def show(Payment paymentInstance) {
        respond paymentInstance
    }

    def create() {
        respond new Payment(params)
    }

    @Transactional
    def save(Payment paymentInstance) {
        if (paymentInstance == null) {
            notFound()
            return
        }

        paymentInstance.user = session.user
        paymentInstance.validate()

        if (paymentInstance.hasErrors()) {
            respond paymentInstance.errors, view: 'create'
            return
        }

        paymentInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'payment.label', default: 'Payment'), paymentInstance.id])
                redirect paymentInstance
            }
            '*' { respond paymentInstance, [status: CREATED] }
        }
    }

    def edit(Payment paymentInstance) {
        respond paymentInstance
    }

    @Transactional
    def update(Payment paymentInstance) {
        if (paymentInstance == null) {
            notFound()
            return
        }

        if (paymentInstance.hasErrors()) {
            respond paymentInstance.errors, view: 'edit'
            return
        }

        paymentInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'payment.label', default: 'Payment'), paymentInstance.id])
                redirect paymentInstance
            }
            '*' { respond paymentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Payment paymentInstance) {

        if (paymentInstance == null) {
            notFound()
            return
        }

        paymentInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'payment.label', default: 'Payment'), paymentInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
