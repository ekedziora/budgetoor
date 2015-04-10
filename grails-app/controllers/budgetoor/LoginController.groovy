package budgetoor

import org.apache.commons.codec.digest.DigestUtils

class LoginController {

    def index() { redirect(action: "login") }

    def login() {
        if(session.user) {
            redirect(action: "sample")
        }
    }

    def doLogin() {
        if(session.user) {
            redirect(action: "sample")
        }

        def passwordSha = DigestUtils.sha512Hex(params['password'])
        def user = User.findWhere(login: params['login'], password: passwordSha)
        session.user = user

        if(user) {
            redirect(action: "sample")
        }
        else {
            redirect(action: "login")
        }
    }

    def sample() {
    }
}
