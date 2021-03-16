package io.celeri.nok.service

import io.celeri.nok.domain.EmailSender
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SimpleSmtpSender : EmailSender {

    override fun send(senderAddress: String, recipientAddress: String, subject: String, body: String) {

        val username = ""
        val password = ""

        val properties = Properties()
        properties.put("mail.smtp.host", "smtp.gmail.com")
        properties.put("mail.smtp.port", "587")
        properties.put("mail.smtp.auth", "true")
        properties.put("mail.smtp.starttls.enable", "true")

        val session: Session = Session.getInstance(properties,
                object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(username, password)
                    }
                })

        try {
            val message: Message = MimeMessage(session)
            message.setFrom(InternetAddress(senderAddress))
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientAddress)
            )
            message.subject = subject
            message.setText(body)
            Transport.send(message)

        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}
