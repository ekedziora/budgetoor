package command

import grails.validation.Validateable

@Validateable
class ChangePasswordCommand {

    Long userId

    String newPassword

    String repeatedNewPassword

    static constraints = {
        userId(nullable: false)
        newPassword(nullable: false, blank: false, minSize: 8)
        repeatedNewPassword(nullable: false, blank: false, validator: { value, obj ->
            if(obj.newPassword != value) {
                return 'repeated.password.not.match'
            }
        })
    }

}
