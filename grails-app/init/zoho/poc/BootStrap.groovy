package zoho.poc

import grails.util.Holders

class BootStrap {

    def init = { servletContext ->
        println "***** Bootstrap Start *****"
        println "apiKey : ${Holders.config.zoho.apiKey}"
        println "apiUrl : ${Holders.config.zoho.partner.apiUrl}"
        println "***** Bootstrap End *****"
    }
    def destroy = {
    }
}
