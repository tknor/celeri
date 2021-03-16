package io.celeri.nok.service

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.GmailScopes
import com.google.api.services.gmail.model.Message
import io.celeri.nok.domain.EmailSender
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@Service
@Profile("production")
class GMail: EmailSender {

    private val applicationName = "NOK"
    private val jsonFactory: JsonFactory
    private val tokensDirectoryPath = "unversioned/tokens"
    private val credentialsFilePath = "unversioned/credentials.json"
    private val user = "me" // special value "me" can be used to indicate the authenticated user
    private val httpTransport: NetHttpTransport

    private val SCOPES = setOf(
            GmailScopes.GMAIL_READONLY,
            GmailScopes.GMAIL_METADATA,
            GmailScopes.GMAIL_LABELS
    )

    init {
        jsonFactory = JacksonFactory.getDefaultInstance()
        httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    }

    override fun send(senderAddress: String, recipientAddress: String, subject: String, body: String) {
        createEmail(senderAddress, recipientAddress, subject, body)
    }

    private fun createEmail(
            from: String,
            to: String,
            subject: String,
            body: String): MimeMessage {

        val properties = Properties()
        val session = Session.getDefaultInstance(properties, null)
        val email = MimeMessage(session)

        email.setFrom(InternetAddress(from))
        email.addRecipient(javax.mail.Message.RecipientType.TO, InternetAddress(to))
        email.subject = subject
        email.setText(body)

        return email
    }

    private fun sendEmail(email: MimeMessage): Message {

        val buffer = ByteArrayOutputStream()
        email.writeTo(buffer)
        val encodedEmail = encodeBase64(buffer.toByteArray())

        val message = Message()
        message.raw = encodedEmail

        val service = Gmail.Builder(httpTransport, jsonFactory, getCredentials())
                .setApplicationName(applicationName)
                .build()

        return service.users().messages().send(user, message).execute()
    }

    /**
     * Creates an authorized Credential object
     */
    fun getCredentials(): Credential {

        val credentialsFileInputStream = this.javaClass.getResourceAsStream(credentialsFilePath)
        val clientSecrets = GoogleClientSecrets.load(jsonFactory, InputStreamReader(credentialsFileInputStream))
        val flow = GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, SCOPES)
                .setDataStoreFactory(FileDataStoreFactory(File(tokensDirectoryPath)))
                .setAccessType("offline")
                .build()
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize(user)
    }

    private fun encodeBase64(bytes: ByteArray): String {
        return String(java.util.Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8)
    }

    private fun decodeBase64(encoded: String): String {
        return String(java.util.Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8)
    }
}

fun main() {

}
