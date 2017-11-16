package com.fireflylearning.tasksummary;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fireflylearning.tasksummary.security.Encryption;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class KeyStoreTest {


    @Test
    public void EncryptVariableAndDecrypt() throws Exception {
        String textTest = "SieteCaballosVienenDeBonanza";
        Context context = InstrumentationRegistry.getTargetContext();
        String alias = "ChiquitoDeLaCalzada";
        String encryptedTest = Encryption.INSTANCE.encryptString(context, textTest, alias);
        String decryptedTest = Encryption.INSTANCE.decryptString(encryptedTest, alias);
        assertTrue(decryptedTest.equals(textTest));
    }
}
