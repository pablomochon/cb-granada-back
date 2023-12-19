package com.basketballticketsproject.basketballticketsproject.utils;


import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import static com.basketballticketsproject.basketballticketsproject.utils.Constants.*;


@Component
public class EnviarEmailUsuarios {


    public String enviarEmailEntrada(Usuario usuario, String fechaPartido) {
        //La dirección de correo de envío
        //La clave de aplicación obtenida según se explica en este artículo:
        String claveemail = CLAVE_GMAIL;

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", "evatallon01@gmail.com");
        props.put("mail.smtp.clave", claveemail);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        File pdfEntrada = getPdfEntrada(fechaPartido);


        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress("evatallon01@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("evatallon01@gmail.com"));   //Se podrían añadir varios de la misma manera
            message.setSubject(EMAIL_ASUNTO);

            MimeMultipart multiParte = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent( String.format(EMAIL_MENSAJE, fechaPartido), "text/html; charset=utf-8");
            multiParte.addBodyPart(bodyPart);

            MimeBodyPart mArchivo = new MimeBodyPart();
            mArchivo.setDataHandler(new DataHandler(new FileDataSource(pdfEntrada.getAbsolutePath())));
            mArchivo.setFileName(pdfEntrada.getName());
            multiParte.addBodyPart(mArchivo);

            message.setContent(multiParte);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "evatallon01@gmail.com", claveemail);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
        return pdfEntrada.getName();
    }

    private static File getPdfEntrada(String fecha) {
        List<File> entradas = new ArrayList<>();
        int result = 0;
        String path = PATH_CARPETA_FECHAS_PARTIDOS + "\\" + fecha;
        System.out.println("RITA: "+ path);
        File entradaPdf = new File(path);
        if (entradaPdf.exists() && entradaPdf.isDirectory()) {
            File[] files = entradaPdf.listFiles();
            if (files != null) {
                entradas.addAll(Arrays.asList(files));
                Random random = new Random();
                System.out.println("NUMERO ENTRADAS: " + entradas.size());
                result = random.nextInt(entradas.size());
            }
        }
        return entradas.get(result);
    }


    public static byte[] decodeBase64ToPdf(Ticket ticket) {
        return Base64.getDecoder().decode(ticket.getPdfBase64());
            //fos.write(decoder);
    }
}
