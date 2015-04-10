

import org.apache.commons.codec.digest.DigestUtils


class Sha512Calculator {

    static void main(String[] args) {
        println(DigestUtils.sha512Hex("pass"));
    }

}
