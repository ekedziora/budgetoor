package budgetoor

import org.apache.commons.codec.digest.DigestUtils

class LoginController {

    def index() { redirect(action: "login") }

    def login() {
        if(session.user) {
            redirect(controller: 'payment')
        }
    }

    def doLogin() {
        if(session.user) {
            redirect(controller: 'payment')
            return
        }

        def passwordSha = DigestUtils.sha512Hex(params['password'])
        def user = User.findWhere(login: params['login'], password: passwordSha)
        session.user = user

        if(user) {
            if(!user.active) {
                flash.message = message(code: 'login.not.active')
                redirect(action: 'login')
            }
            else {
                redirect(controller: 'payment')
            }
        }
        else {
            flash.message = message(code: 'login.bad.credentials')
            redirect(action: "login")
        }
    }

    def logout() {
        if(session.user) {
            session.user = null
            redirect(action: "login")
        }
    }
}
