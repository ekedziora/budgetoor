package budgetoor

import grails.transaction.Transactional
import org.apache.commons.codec.digest.DigestUtils

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT"]

    def beforeInterceptor = {
        def user = session.user
        if(!user) {
            redirect(controller: 'login', action: 'login')
            return false
        }
        if(!user.admin) {
            flash.message = message(code: 'access.denied')
            redirect(controller: 'payment')
            return false
        }
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model: [userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'create'
            return
        }

        encryptPassword(userInstance)
        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        userInstance.password = null
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'edit'
            return
        }

        encryptPassword(userInstance)
        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def activateOrDeactivate(User userInstance) {

        if(!userInstance) {
            notFound()
            return
        }

        userInstance.active = !userInstance.active

        flash.message = message(code: userInstance.active ? 'default.activate.message' : 'default.deactivate.message', args: [userInstance.firstAndLastName])
        redirect action: "index", method: "GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    private def encryptPassword(User userInstance) {
        def passwordHash = DigestUtils.sha512Hex(userInstance.password)
        userInstance.password = passwordHash
    }
}
