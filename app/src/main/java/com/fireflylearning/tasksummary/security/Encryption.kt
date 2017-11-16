@file:Suppress("DEPRECATION")

package com.fireflylearning.tasksummary.security

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import android.util.Base64
import android.util.Log
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.security.auth.x500.X500Principal


/**
 * Created by Raul on 16/11/2017.
 * Class to encrypt and decrypt text
 */


object Encryption {

    private val CIPHER_TYPE = "RSA/ECB/PKCS1Padding"

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private fun getKeystoreInstance(): KeyStore{
        val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        return keyStore
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private fun createNewKeys(alias: String, context: Context) {

        val keyStore = getKeystoreInstance()

        try {
            // Create new key if needed
            if (!keyStore.containsAlias(alias)) {
                val generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore")

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    val start = Calendar.getInstance()
                    val end = Calendar.getInstance()
                    end.add(Calendar.YEAR, 1)

                    val spec = KeyPairGeneratorSpec.Builder(context)
                            .setAlias(alias)
                            .setSubject(X500Principal("CN=Sample Name, O=Android Authority"))
                            .setSerialNumber(BigInteger.ONE)
                            .setStartDate(start.time)
                            .setEndDate(end.time)
                            .build()
                    generator.initialize(spec)
                } else {

                    generator.initialize(
                            KeyGenParameterSpec.Builder(
                                    alias,
                                    KeyProperties.PURPOSE_DECRYPT)
                                    .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                                    .build())

                }
                generator.generateKeyPair()
            }
        } catch (e: Exception) {
            Log.e("ppp", Log.getStackTraceString(e))
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Suppress("unused")
    @Throws(KeyStoreException::class)
    private fun deleteKey(alias: String) {
        val keyStore = getKeystoreInstance()
        keyStore.deleteEntry(alias)
    }

    fun encryptString(context: Context, text: String, alias: String): String {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return text
        }
        val keyStore = getKeystoreInstance()
        var encryptedText = ""
        try {
            createNewKeys(alias, context)
            val privateKeyEntry = keyStore.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            val publicKey = privateKeyEntry.certificate.publicKey as RSAPublicKey


            val inCipher = Cipher.getInstance(CIPHER_TYPE)
            inCipher.init(Cipher.ENCRYPT_MODE, publicKey)

            val outputStream = ByteArrayOutputStream()
            val cipherOutputStream = CipherOutputStream(
                    outputStream, inCipher)
            cipherOutputStream.write(text.toByteArray(Charsets.UTF_8))
            cipherOutputStream.close()

            val vals = outputStream.toByteArray()
            encryptedText = Base64.encodeToString(vals, Base64.DEFAULT)
        } catch (e: Exception) {
            Log.e("ppp", Log.getStackTraceString(e))
        }

        return encryptedText
    }

    fun decryptString(text: String, alias: String): String {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return text
        }
        val keyStore = getKeystoreInstance()
        var decryptedText = ""
        try {
            val privateKeyEntry = keyStore.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            //RSAPrivateKey privateKey = (RSAPrivateKey) privateKeyEntry.getPrivateKey();

            val output = Cipher.getInstance(CIPHER_TYPE)
            output.init(Cipher.DECRYPT_MODE, privateKeyEntry.privateKey)

            val cipherInputStream = CipherInputStream(
                    ByteArrayInputStream(Base64.decode(text, Base64.DEFAULT)), output)
            val values = ArrayList<Byte>()
            var nextByte: Int = cipherInputStream.read()
            while (nextByte != -1) {
                values.add(nextByte.toByte())
                nextByte = cipherInputStream.read()
            }

            val bytes = ByteArray(values.size)
            for (i in bytes.indices) {
                bytes[i] = values[i]
            }

            val finalText = String(bytes, 0, bytes.size, Charsets.UTF_8)
            decryptedText = finalText

        } catch (e: Exception) {
            Log.e("", Log.getStackTraceString(e))
        }

        return decryptedText
    }



}
