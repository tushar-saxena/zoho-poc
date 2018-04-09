package com.poc

import com.google.common.base.Splitter
import com.mashape.unirest.http.Unirest
import grails.core.GrailsApplication
import grails.util.Holders
import org.apache.tomcat.util.http.fileupload.FileItem
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload

import java.util.regex.Matcher
import java.util.regex.Pattern

class ZohoPartnerController {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    def utilService

    def open(){
        def r = Unirest.post("https://writer.zoho.com/writer/remotedoc.im")
                .queryString("output", "url")
                .queryString("mode", "normaledit")
                .queryString("apikey", Holders.config.zoho.apiKey)
                .queryString("filename", "Untitled Document")
                .queryString("format", "docx")
                .queryString("id", "1234")
                .asString()
        Map response = utilService.generateResponse(r.body)
        println "Response : ${response.dump()}"
        [url: response.URL]
    }

    def edit(){
        def r = Unirest.post("https://writer.zoho.com/writer/remotedoc.im")
                .queryString("output", "url")
                .queryString("mode", "normaledit")
                .queryString("apikey", Holders.config.zoho.apiKey)
//                .queryString("filename", "Edit2")
                .queryString("format", "docx")
                .queryString("url","${Holders.config.zoho.partner.apiUrl}/data/abc.docx")
                .queryString("saveurl", "${Holders.config.zoho.partner.apiUrl}/util/test")
                .queryString("id", "12234")
                .asString()
        Map response = utilService.generateResponse(r.body)
        println "Response : ${response.dump()}"
        render (view: 'open',model: [url: response.URL])
    }

}
